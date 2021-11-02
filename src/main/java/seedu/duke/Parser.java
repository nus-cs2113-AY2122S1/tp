package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class Parser {

    private static final int SPLIT_COMMAND_FROM_INFO_LENGTH = 2;
    private static final int INPUT_COMMAND = 0;
    private static final int INPUT_INFO = 1;

    private static final String QUIT_COMMAND = "quit";
    private static final String CLOSE_COMMAND = "close";

    /**
     * Parses the user-entered command and additional information/flags.
     *
     * @param userInput the {@link String} containing the user input
     * @return whether the program should continue running after processing the given user input
     */
    public static boolean parseUserInput(String userInput) {

        String[] rawInput = userInput.split(" ", SPLIT_COMMAND_FROM_INFO_LENGTH);
        String inputCommand = rawInput[INPUT_COMMAND].toLowerCase();
        String inputParams = null;

        if (rawInput.length == SPLIT_COMMAND_FROM_INFO_LENGTH) {
            inputParams = rawInput[INPUT_INFO];
        }

        if (inputCommand.equals(QUIT_COMMAND)) {
            Ui.goodBye();
            return false;
        } else if (inputCommand.equals(CLOSE_COMMAND)) {
            try {
                Storage.setLastExpense(null);
                Storage.closeTrip();
                return true;
            } catch (NullPointerException e) {
                Ui.printNoOpenTripError();
                return true;
            }
        } else if (!checkValidCommand(inputCommand)) {
            Storage.getLogger().log(Level.WARNING, "invalid user input");
            Ui.printUnknownCommandError();
            return true;
        } else if (Storage.getListOfTrips().isEmpty()
                && !inputCommand.equals("create")) {
            Storage.getLogger().log(Level.WARNING, "No trip created yet");
            Ui.printNoTripError();
            return true;
        }
        try {
            handleValidCommands(inputCommand, inputParams);
        } catch (ForceCancelException e) {
            Ui.printForceCancelled();
        }
        return true;
    }

    private static final String CREATE_COMMAND = "create";
    private static final String EDIT_COMMAND = "edit";
    private static final String OPEN_COMMAND = "open";
    private static final String SUMMARY_COMMAND = "summary";
    private static final String VIEW_COMMAND = "view";
    private static final String DELETE_COMMAND = "delete";
    private static final String LIST_COMMAND = "list";
    private static final String EXPENSE_COMMAND = "expense";
    private static final String EDIT_EXP_COMMAND = "edit-exp";
    private static final String AMOUNT_COMMAND = "amount";
    private static final String HELP_COMMAND = "help";


    /**
     * Handles commands entered by the user that are confirmed as valid, and redirects to the appropriate method
     * for further updates.
     *
     * @param inputCommand Valid command executed by the user.
     * @param inputParams  Additional information appended to the command by the user
     *                     (inputParams are not checked and may not be valid).
     *
     * @see Parser#parseUserInput(String)
     */
    private static void handleValidCommands(String inputCommand, String inputParams) throws ForceCancelException {

        switch (inputCommand) {
        case CREATE_COMMAND:
            handleCreateTrip(inputParams);
            break;

        case EDIT_COMMAND:
            handleEditTrip(inputParams);
            break;

        case OPEN_COMMAND:
            handleOpenTrip(inputParams);
            break;

        case SUMMARY_COMMAND:
            handleTripSummary(inputParams);
            break;

        case VIEW_COMMAND:
            handleViewTrip(inputParams);
            break;

        case DELETE_COMMAND:
            handleDelete(inputParams);
            break;

        case LIST_COMMAND:
            executeList();
            break;

        case EXPENSE_COMMAND:
            handleCreateExpense(inputParams);
            break;

        case EDIT_EXP_COMMAND:
            handleEditExpense(inputParams);
            break;

        case AMOUNT_COMMAND:
            handleAmount(inputParams);
            break;

        case HELP_COMMAND:
            Ui.displayHelp();
            break;

        default:
            Ui.printUnknownCommandError();
        }
    }

    /**
     * Confirms that the user had entered parameters for creating a new expense, and redirects to
     * {@link Parser#executeCreateExpense(String)} to create the expense.
     *
     * @param inputParams attributes of expense to be created.
     */
    private static void handleCreateExpense(String inputParams) {
        try {
            assert inputParams != null;
            executeCreateExpense(inputParams);
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printExpenseFormatError();
        } catch (CancelExpenseException e) {
            Ui.printCancelExpenseCreation();
        }
    }

    private static void handleEditExpense(String inputParams) {
        try {
            assert inputParams != null;
            executeEditExpense(inputParams);
        } catch (NullPointerException | IndexOutOfBoundsException | NumberFormatException e) {
            Ui.printExpenseFormatError();
        }
    }

    private static void handleAmount(String inputParams) {
        try {
            executeAmount(inputParams);
        } catch (NullPointerException e) {
            Ui.invalidArgForAmount();
        }
    }


    private static void handleViewTrip(String inputParams) {
        try {
            executeView(inputParams);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printFilterFormatError();
        }
    }

    private static void handleDelete(String inputParams) {
        try {
            assert inputParams != null;
            executeDelete(inputParams);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Ui.printUnknownTripIndexError();
        } catch (NullPointerException e) {
            Ui.emptyArgForDeleteCommand();
        }
    }

    private static void handleTripSummary(String inputParams) {
        try {
            executeSummary(inputParams);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printUnknownTripIndexError();
        }
    }

    private static void handleOpenTrip(String inputParams) {
        try {
            assert inputParams != null;
            executeOpen(inputParams);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.printSingleUnknownTripIndexError();
            System.out.println();
        } catch (NullPointerException e) {
            Ui.emptyArgForOpenCommand();
        }
    }

    private static void handleEditTrip(String inputParams) throws ForceCancelException {
        try {
            assert inputParams != null;
            executeEditTrip(inputParams);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            Ui.printUnknownTripIndexError();
        }
    }

    /**
     * Confirms that the user entered paramaters, and calls {@link Parser#executeCreateTrip(String)}.
     *
     * @param inputParams attributes of the trip to be created.
     */
    private static void handleCreateTrip(String inputParams) throws ForceCancelException {
        try {
            assert inputParams != null;
            executeCreateTrip(inputParams);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            Ui.printCreateFormatError();
        }
    }

    /**
     * Creates a new instance of {@link Trip} and adds it to the <code>listOfTrips</code>.
     *
     * @param attributesInString attributes of the trip to be added (in a single {@link String}), before being parsed.
     */
    private static void executeCreateTrip(String attributesInString) throws ForceCancelException {
        String[] newTripInfo = attributesInString.split(" ", 5);
        if (newTripInfo.length < 5) {
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
    private static void executeEditTrip(String inputDescription) throws ForceCancelException {
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


    /**
     * Sets the user-specified trip as opened. Requires that the {@code listOfTrips} has at least one open trip.
     *
     * @param indexAsString index of trip to open, as a {@link String} to be parsed.
     */
    private static void executeOpen(String indexAsString) {
        //assumes that listOfTrips have at least 1 trip
        int indexToGet = Integer.parseInt(indexAsString) - 1;
        if (Storage.checkOpenTrip()) {
            Ui.printTripClosed(Storage.getOpenTrip());
        }
        Storage.setOpenTrip(indexToGet);
        Ui.printOpenTripMessage(Storage.getOpenTrip());
    }

    private static void executeSummary(String inputParams) {
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
    private static void executeView(String inputParams) {
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

    public static boolean isNumeric(String secondCommand) {
        try {
            int i = Integer.parseInt(secondCommand);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //@@author

    /**
     * Checks whether to delete trip or delete expense (by determining if a trip is open),
     * and calls the appropriate method.
     *
     * @param inputParams attributes of trip to be deleted (if valid, this should be the trip/expense index)
     *
     * @see Parser#executeDeleteTrip(int)
     * @see Parser#executeDeleteExpense(int)
     */
    private static void executeDelete(String inputParams) {
        int index = Integer.parseInt(inputParams) - 1;
        if (!Storage.checkOpenTrip()) {
            executeDeleteTrip(index);
        } else {
            executeDeleteExpense(index);
        }
    }

    //@@author leeyikai
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
    //@@author

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

    private static void executeList() {
        if (!Storage.checkOpenTrip()) {
            Ui.printAllTrips();
        } else {
            Ui.printExpensesInList(Storage.getOpenTrip().getListOfExpenses());
        }
    }

    private static void executeCreateExpense(String inputDescription) throws CancelExpenseException {
        Trip currTrip = Storage.getOpenTrip();
        assert Storage.checkOpenTrip();
        Expense newExpense = new Expense(inputDescription);
        currTrip.addExpense(newExpense);
        Storage.setLastExpense(newExpense);
        Ui.printExpenseAddedSuccess();
    }

    private static void executeEditExpense(String inputDescription) {
        //TODO: add edit expense code (for override of exchange rate using manual local currency)
    }


    //@@author joshualeeky
    private static void executeAmount(String inputParams) {
        Trip trip = Storage.getOpenTrip();
        Person toBeChecked = getValidPersonInTripFromString(inputParams, trip);
        if (toBeChecked == null) {
            Ui.invalidArgForAmount();
        } else {
            Ui.printAmount(toBeChecked, trip);
        }
    }

    private static Person getValidPersonInTripFromString(String name, Trip trip) {
        for (Person person : trip.getListOfPersons()) {
            if (name.equalsIgnoreCase(person.getName())) {
                return person;
            }
        }
        return null;
    }

    //@@author lixiyuan416
    private static boolean manageLog(boolean isLogDisplayed) {
        if (!isLogDisplayed) {
            Storage.getLogger().log(Level.INFO, "Some people were allocated 0 for this expense split.");
            Ui.autoAssignIndividualSpending();
            isLogDisplayed = true;
        }
        return isLogDisplayed;
    }

    public static boolean getUserToConfirm() {
        boolean isValidInput = false;
        boolean doesUserAgree = false;
        while (!isValidInput) {
            String userReply = Storage.getScanner().nextLine();
            if (userReply.equalsIgnoreCase("y")) {
                isValidInput = true;
                doesUserAgree = true;
            } else if (userReply.equalsIgnoreCase("n")) {
                isValidInput = true;
            } else {
                System.out.println("Enter y/n");
            }
        }
        return doesUserAgree;
    }
    //@@author


    private static boolean checkValidCommand(String inputCommand) {
        return Storage.getValidCommands().contains(inputCommand);
    }

    private static final int ATTRIBUTE_DATA = 1;
    private static final int EDIT_ATTRIBUTE = 0;
    private static final String EDIT_LOCATION = "-location";
    private static final String EDIT_DATE = "-date";
    private static final String EDIT_EXRATE = "-exchangerate";
    private static final String EDIT_FORCUR = "-forcur";
    private static final String EDIT_HOMECUR = "-homecur";


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
        /*case "budget":
            tripToEdit.setBudget(data);
            break;*/
        case EDIT_LOCATION:
            String originalLoocation = tripToEdit.getLocation();
            tripToEdit.setLocation(data);
            Ui.changeLocationSuccessful(tripToEdit, originalLoocation);
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
        //case "person":
            //break;
        default:
            System.out.println(splitCommandAndData[EDIT_ATTRIBUTE] + " was not recognised. "
                    + "Please try again after this process is complete");
        }

    }

    public static boolean doesUserWantToForceCancel(String userInput) {
        return userInput.equals("-cancel");
    }

    private static final int DAYDD = 0;
    private static final int MONTHMM = 1;
    private static final int YEARYYYY = 2;

    /**
     * Checks if the user-entered date is a date that actually exists.
     *
     * @param dateInString the entire dd-mm-yyyy date as a single string.
     * @return true if the date actually exists.
     */
    public static boolean doesDateReallyExist(String dateInString) {
        String[] dateSplitUp = dateInString.split("-");
        int day, month, year;
        try {
            day = Integer.parseInt(dateSplitUp[DAYDD]);
            month = Integer.parseInt(dateSplitUp[MONTHMM]);
            year = Integer.parseInt(dateSplitUp[YEARYYYY]);
        } catch (NumberFormatException e) {
            return false;
        }

        //definitely an invalid date
        if (day < 0 || day > 31 || month < 0 || month > 12) {
            return false;
        }
        //for months with 30 days
        if ((month < 7 && month % 2 == 0) || (month >= 8 && month % 2 == 1)) {
            if (day > 30) {
                return false;
            }
        }
        //leap year checks
        if (month == 2) {
            LocalDate leapYearCheck = LocalDate.of(year, 1, 1);
            if (!leapYearCheck.isLeapYear() && day > 28) {
                return false;
            } else return (!leapYearCheck.isLeapYear() || day <= 29);
        }

        return true;

    }

}
