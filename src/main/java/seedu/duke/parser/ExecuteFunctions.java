package seedu.duke.parser;

import seedu.duke.Trip;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.Person;
import seedu.duke.exceptions.ForceCancelException;
import seedu.duke.exceptions.InvalidAmountException;
import seedu.duke.exceptions.NoExpensesError;
import seedu.duke.exceptions.TripNotOpenException;
import seedu.duke.expense.Expense;

import java.util.ArrayList;

public abstract class ExecuteFunctions {
    private static final int ATTRIBUTE_DATA = 1;
    private static final int EDIT_ATTRIBUTE = 0;
    private static final String EDIT_LOCATION = "-location";
    private static final String EDIT_DATE = "-date";
    private static final String EDIT_EXRATE = "-exchangerate";
    private static final String EDIT_FORCUR = "-forcur";
    private static final String EDIT_HOMECUR = "-homecur";
    private static final int NEW_TRIP_ATTRIBUTES_COUNT = 6;


    /**
     * Creates a new instance of {@link Trip} and adds it to the <code>listOfTrips</code>.
     *
     * @param attributesInString attributes of the trip to be added (in a single {@link String}), before being parsed.
     */
    protected static void executeCreateTrip(String attributesInString)
            throws ForceCancelException, IndexOutOfBoundsException {
        String[] newTripInfo = attributesInString.split("/", NEW_TRIP_ATTRIBUTES_COUNT);
        if (newTripInfo.length < NEW_TRIP_ATTRIBUTES_COUNT) {
            throw new IndexOutOfBoundsException();
        }
        Trip newTrip = new Trip(newTripInfo);
        Storage.getListOfTrips().add(newTrip);
        Ui.newTripSuccessfullyCreated(newTrip);
        Storage.setLastTrip(newTrip);
    }


    /**
     * Gets the trip to be edited and edits the specified attributes of the trip.
     *
     * @param inputDescription - user input of trip index and trip attributes to edit.
     *
     * @see Parser#editTripWithIndex(String)
     * @see Parser#editTripPerAttribute(Trip, String)
     */
    protected static void executeEditTrip(String inputDescription) throws ForceCancelException {
        String[] tripToEditInfo = inputDescription.split(" ", 2);
        assert tripToEditInfo[1] != null;
        String attributesToEdit = tripToEditInfo[1];
        Trip tripToEdit;
        if (tripToEditInfo[0].equals("last")) {
            tripToEdit = Storage.getLastTrip();
            if (tripToEdit == null) {
                Ui.printNoLastTripError();
                return;
            }
        } else {
            tripToEdit = editTripWithIndex(tripToEditInfo[0].strip());
        }
        editTripPerAttribute(tripToEdit, attributesToEdit);
    }

    /**
     * Sets the user-specified trip as opened. Requires that the {@code listOfTrips} has at least one open trip.
     *
     * @param indexAsString index of trip to open, as a {@link String} to be parsed.
     */
    protected static void executeOpen(String indexAsString) {
        //assumes that listOfTrips have at least 1 trip
        int indexToGet = Integer.parseInt(indexAsString) - 1;
        if (Storage.checkOpenTrip()) {
            Ui.printTripClosed(Storage.getOpenTrip());
        }
        Storage.setOpenTrip(indexToGet);
        Ui.printOpenTripMessage(Storage.getOpenTrip());
    }

    protected static void executeSummary(String inputParams) {
        Trip currentTrip = Storage.getOpenTrip();
        if (inputParams == null) {
            //list everybody's expense summary
            for (Person p : currentTrip.getListOfPersons()) {
                currentTrip.getIndividualExpenseSummary(p);
                System.out.println();
            }
        } else {
            //list only 1 person, if exists
            try {
                //returns null if no such person
                Person personToView = getValidPersonInTripFromString(inputParams, currentTrip);
                if (personToView != null) {
                    currentTrip.getIndividualExpenseSummary(personToView);
                } else {
                    Ui.printNoPersonFound(inputParams);
                    Ui.printSummaryFormatError();
                }

            } catch (IndexOutOfBoundsException e) {
                Ui.printNoExpensesError();
            }
        }
    }

    //@@author leeyikai
    protected static void executeView(String inputParams) {
        Trip openTrip = Storage.getOpenTrip();
        if (inputParams == null) {
            openTrip.viewAllExpenses();
        } else {
            String[] paramString = inputParams.split(" ", 3);
            String secondCommand = paramString[0];
            String expenseCategory = null;
            String expenseAttribute = null;
            if (!isNumeric(secondCommand)) {
                expenseCategory = paramString[1];
                expenseAttribute = paramString[2];
            }
            if (secondCommand.equals("filter")) {
                try {
                    openTrip.getFilteredExpenses(expenseCategory, expenseAttribute);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printNoExpensesError();
                }
            } else if (isNumeric(secondCommand)) {
                try {
                    int index = Integer.parseInt(secondCommand);
                    System.out.println(openTrip.getExpenseAtIndex(index));
                } catch (IndexOutOfBoundsException | NumberFormatException e) {
                    Ui.printUnknownExpenseIndexError();
                }

            }
        }
    }

    /**
     * Checks whether to delete trip or delete expense (by determining if a trip is open),
     * and calls the appropriate method.
     *
     * @param inputParams attributes of trip to be deleted (if valid, this should be the trip/expense index)
     *
     * @see Parser#executeDeleteTrip(int)
     * @see Parser#executeDeleteExpense(int)
     */
    protected static void executeDelete(String inputParams) {
        int index = Integer.parseInt(inputParams) - 1;
        if (!Storage.checkOpenTrip()) {
            executeDeleteTrip(index);
        } else {
            executeDeleteExpense(index);
        }
    }

    protected static void executeList() throws TripNotOpenException {
        if (!Storage.checkOpenTrip()) {
            Ui.printAllTrips();
        } else {
            throw new TripNotOpenException();
        }
    }

    protected static void executeCreateExpense(String inputDescription)
            throws InvalidAmountException, ForceCancelException {
        Trip currTrip = Storage.getOpenTrip();
        assert Storage.checkOpenTrip();
        Expense newExpense = new Expense(inputDescription);
        currTrip.addExpense(newExpense);
        Storage.setLastExpense(newExpense);
        Ui.printExpenseAddedSuccess();
    }

    protected static void executeAmount(String inputParams) {
        Trip trip = Storage.getOpenTrip();
        Person toBeChecked = getValidPersonInTripFromString(inputParams, trip);
        if (toBeChecked == null) {
            Ui.invalidArgForAmount();
        } else {
            Ui.printAmount(toBeChecked, trip);
        }
    }

    protected static void executePeople() throws TripNotOpenException {
        if (Storage.checkOpenTrip()) {
            System.out.println("These are the people involved in this trip:");
            Ui.printListOfPeople(Storage.getOpenTrip().getListOfPersons());
        } else {
            throw new TripNotOpenException();
        }
    }

    private static void executeDeleteExpense(int expenseIndex) {
        try {
            Trip currentTrip = Storage.getOpenTrip();
            Expense expenseToDelete = currentTrip.getListOfExpenses().get(expenseIndex);
            Double expenseAmount = expenseToDelete.getAmountSpent();
            correctBalances(expenseToDelete);
            currentTrip.removeExpense(expenseIndex);
            Ui.printDeleteExpenseSuccessful(expenseAmount);
        } catch (IndexOutOfBoundsException e) {
            Ui.printUnknownExpenseIndexError();
        }
        Storage.setLastExpense(null);
    }

    protected static void executeOptimize() throws NoExpensesError {
        if (Storage.getOpenTrip().getListOfExpenses().size() > 0) {
            checkForOptimization();
        } else {
            throw new NoExpensesError();
        }
    }

    /**
     * Deletes a trip from the <code>listOfTrips</code>.
     *
     * @param tripIndex index of Trip to be applied to <code>listOfTrips</code>
     */
    private static void executeDeleteTrip(int tripIndex) {
        ArrayList<Trip> listOfTrips = Storage.getListOfTrips();
        try {
            Trip tripToDelete = listOfTrips.get(tripIndex);
            listOfTrips.remove(tripIndex);
            Ui.printDeleteTripSuccessful(tripToDelete);
            Storage.setLastTrip(null);
        } catch (IndexOutOfBoundsException e) {
            Ui.printUnknownTripIndexError();
        }
    }


    /**
     * Parses the user input to determine which attributes to edit,
     * and calls the relevant setters to edit those attributes.
     *
     * @param tripToEdit user-specified trip to be edited
     * @param attributeToEdit String of all attributes to be added and their new values
     */
    private static void editTripPerAttribute(Trip tripToEdit, String attributeToEdit) throws ForceCancelException {
        String[] splitCommandAndData = attributeToEdit.split(" ");
        String data = splitCommandAndData[ATTRIBUTE_DATA];
        switch (splitCommandAndData[EDIT_ATTRIBUTE]) {
        case EDIT_LOCATION:
            String originalLocation = tripToEdit.getLocation();
            tripToEdit.setLocation(data);
            Ui.changeLocationSuccessful(tripToEdit, originalLocation);
            break;
        case EDIT_DATE:
            String originalDate = tripToEdit.getDateOfTripString();
            tripToEdit.setDateOfTrip(data);
            Ui.changeDateSuccessful(tripToEdit, originalDate);
            break;
        case EDIT_EXRATE:
            double originalExRate = tripToEdit.getExchangeRate();
            tripToEdit.setExchangeRate(data);
            Ui.changeExchangeRateSuccessful(tripToEdit, originalExRate);
            break;
        case EDIT_HOMECUR:
            String originalHomeCurrency = tripToEdit.getRepaymentCurrency();
            tripToEdit.setRepaymentCurrency(data);
            Ui.changeHomeCurrencySuccessful(tripToEdit, originalHomeCurrency);
            break;
        case EDIT_FORCUR:
            String originalForeignCurrency = tripToEdit.getForeignCurrency();
            tripToEdit.setForeignCurrency(data);
            Ui.changeForeignCurrencySuccessful(tripToEdit, originalForeignCurrency);
            break;
        default:
            System.out.println(splitCommandAndData[EDIT_ATTRIBUTE] + " was not recognised. "
                    + "Please try again after this process is complete");
        }
    }

    /**
     * Gets the trip to be edited from the user-entered index.
     *
     * @param tripIndexInString index of trip to be edited, as a {@link String} to be parsed.
     * @return the {@link Trip} object to be edited.
     */
    private static Trip editTripWithIndex(String tripIndexInString) {
        int indexToEdit = Integer.parseInt(tripIndexInString) - 1;
        Trip tripToEdit = Storage.getListOfTrips().get(indexToEdit);
        Storage.setLastTrip(tripToEdit);
        return tripToEdit;
    }


    public static boolean isNumeric(String secondCommand) {
        try {
            int i = Integer.parseInt(secondCommand);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void correctBalances(Expense expense) {
        Person payer = expense.getPayer();
        for (Person person : expense.getPersonsList()) {
            if (person == payer) {
                payer.setMoneyOwed(payer, -expense.getAmountSplit().get(person.getName()));
                continue;
            }
            payer.setMoneyOwed(person, -expense.getAmountSplit().get(person.getName()));
            person.setMoneyOwed(payer, expense.getAmountSplit().get(person.getName()));
            person.setMoneyOwed(person, -expense.getAmountSplit().get(person.getName()));
        }
    }

    //@@author joshualeeky
    private static Person getValidPersonInTripFromString(String name, Trip trip) {
        for (Person person : trip.getListOfPersons()) {
            if (name.equalsIgnoreCase(person.getName())) {
                return person;
            }
        }
        return null;
    }
    //@@author


    private static void checkForOptimization() {
        Trip trip = Storage.getOpenTrip();
        trip.optimizePayments();
        Ui.printOptimizedAmounts();
    }

}
