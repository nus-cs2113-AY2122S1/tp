package seedu.duke;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class Parser {

    /**
     * Parses the user-entered command and additional information/flags.
     *
     * @param userInput the {@link String} containing the user input
     * @return whether the program should continue running after processing the given user input
     */
    //TODO: this method needs refactoring and extraction - currently about 100 lines
    public static boolean parseUserInput(String userInput) {
        String[] rawInput = userInput.split(" ", 2);
        String inputCommand = rawInput[0].toLowerCase();
        String inputParams = null;

        if (rawInput.length == 2) {
            inputParams = rawInput[1];
        }

        if (inputCommand.equals("quit")) {
            Ui.goodBye();
            return false;
        } else if (Storage.listOfTrips.isEmpty() && !inputCommand.equals("create")) {
            Storage.getLogger().log(Level.WARNING, "No trip created yet");
            Ui.printNoTripError();
            return true;
        } else if (inputCommand.equals("close")) {
            Storage.closeTrip();
            return true;
        } else if (!checkValidCommand(inputCommand)) {
            Storage.getLogger().log(Level.WARNING, "invalid user input");
            Ui.printUnknownCommandError();
            return true;
        }

        switch (inputCommand) {
        case "create":
            try {
                assert inputParams != null;
                executeCreate(inputParams);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                Ui.printCreateFormatError();
            }
            break;

        case "edit":
            try {
                assert inputParams != null;
                executeEdit(inputParams);
            } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
                Ui.printUnknownTripIndexError();
            }
            break;

        case "open":
            try {
                assert inputParams != null;
                executeOpen(inputParams);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                Ui.printSingleUnknownTripIndexError();
                System.out.println();
            } catch (NullPointerException e) {
                Ui.emptyArgForOpenCommand();
            }
            break;

        case "summary":
            try {
                assert inputParams != null;
                executeSummary();
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printUnknownTripIndexError();
            }
            break;

        case "view":
            try {
                executeView(inputParams);
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printFilterFormatError();
            }

            break;

        case "delete":
            try {
                assert inputParams != null;
                executeDelete(inputParams);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Ui.printUnknownTripIndexError();
            } catch (NullPointerException e) {
                Ui.emptyArgForDeleteCommand();
            }
            break;

        case "list":
            executeList();
            break;

        case "expense":
            try {
                assert inputParams != null;
                executeExpense(inputParams);
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                Ui.printExpenseFormatError();
            }
            break;

        case "amount":
            executeAmount(inputParams);
            break;

        case "help":
            Ui.displayHelp();
            break;

        default:
            Ui.printUnknownCommandError();
        }
        return true;
    }

    private static void executeCreate(String indexAsString) {
        String[] newTripInfo = indexAsString.split(" ", 4);
        Trip newTrip = new Trip(newTripInfo);
        Storage.listOfTrips.add(newTrip);
        System.out.println("Your trip to " + newTrip.getLocation() + " on "
                + newTrip.getDateOfTripString() + " has been successfully added!");
    }

    private static void executeEdit(String inputDescription) {
        String[] tripToEditInfo = inputDescription.split(" ", 2);
        int indexToEdit = Integer.parseInt(tripToEditInfo[0]) - 1;
        assert tripToEditInfo[1] != null;
        String attributesToEdit = tripToEditInfo[1];
        Trip tripToEdit = Storage.listOfTrips.get(indexToEdit);
        editTripPerAttribute(tripToEdit, attributesToEdit);
    }

    //assumes that listOfTrips have at least 1 trip
    private static void executeOpen(String indexAsString) {
        int indexToGet = Integer.parseInt(indexAsString) - 1;
        Storage.setOpenTrip(Storage.listOfTrips.get(indexToGet));
        Ui.printOpenTripMessage(Storage.listOfTrips.get(indexToGet));
    }

    private static void executeSummary() {
        Ui.printExpensesSummary(Storage.getOpenTrip());
    }

    private static void executeView(String inputParams) {
        if (inputParams == null) {
            Storage.getOpenTrip().viewAllExpenses();

        } else {
            String[] paramString = inputParams.split(" ", 3);
            String secondCommand = paramString[0];
            String expenseCategory = paramString[1];
            String expenseAttribute = paramString[2];
            if (secondCommand.equals("filter")) {
                try {
                    Trip.getFilteredExpenses(expenseCategory, expenseAttribute);
                } catch (IndexOutOfBoundsException e) {
                    Ui.printNoExpensesError();
                }

            }
        }
    }

    private static void executeDelete(String inputParams) {
        String[] splitInputParams = inputParams.split(" ", 2);
        String type = splitInputParams[0];
        int index = Integer.parseInt(splitInputParams[1]) - 1;
        if (type.equals("trip")) {
            executeDeleteTrip(index);
        } else if (type.equals("expense")) {
            executeDeleteExpense(index);
        } else {
            Ui.printInvalidDeleteFormatError();
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
    }

    private static void correctBalances(Expense expense) {
        Person payer = expense.getPayer();
        for (Person person : expense.getPersonsList()) {
            if (person == payer) {
                payer.setMoneyOwed(payer, -expense.getAmountSplit().get(person));
                continue;
            }
            payer.setMoneyOwed(person, -expense.getAmountSplit().get(person));
            person.setMoneyOwed(payer, expense.getAmountSplit().get(person));
            person.setMoneyOwed(person, -expense.getAmountSplit().get(person));
        }
    }

    private static void executeDeleteTrip(int tripIndex) {
        try {
            String tripLocation = Storage.listOfTrips.get(tripIndex).getLocation();
            String tripDate = Storage.listOfTrips.get(tripIndex).getDateOfTripString();
            Storage.listOfTrips.remove(tripIndex);
            Ui.printDeleteTripSuccessful(tripLocation, tripDate);
        } catch (IndexOutOfBoundsException e) {
            Ui.printUnknownTripIndexError();
        }

    }

    private static void executeList() {
        int index = 1;
        if (!Storage.checkOpenTrip()) {
            for (Trip trip : Storage.listOfTrips) {
                Ui.printTripsInList(trip, index);
                index++;
            }
        } else {
            for (Expense expense : Storage.getOpenTrip().getListOfExpenses()) {
                Ui.printExpensesInList(expense, index);
                index++;
            }
            if (index == 1) {
                Ui.printNoExpensesError();
            }
        }
    }

    private static void executeExpense(String inputDescription) {
        Trip currTrip = Storage.getOpenTrip();
        assert Storage.checkOpenTrip();
        String[] expenseInfo = inputDescription.split(" ", 3);
        Double expenseAmount = Double.parseDouble(expenseInfo[0]);
        String expenseCategory = expenseInfo[1].toLowerCase();
        ArrayList<Person> listOfPersonsIncluded = checkValidPersons(currTrip, expenseInfo[2]);
        String expenseDescription = getDescription(expenseInfo[2]);
        Expense newExpense = new Expense(expenseAmount, expenseCategory, listOfPersonsIncluded, expenseDescription);
        newExpense.setDate(newExpense.prompDate());
        currTrip.addExpense(newExpense);
        updateIndividualSpending(newExpense);
        Ui.printExpenseAddedSuccess();
    }

    private static void updateIndividualSpending(Expense expense) {
        Ui.printGetPersonPaid();
        String input = Storage.getScanner().nextLine().strip();
        Person payer = checkValidPersonInExpense(input, expense);
        if (payer != null) {
            expense.setPayer(payer);
            HashMap<Person, Double> amountBeingPaid = new HashMap<>();
            double total = 0.0;
            for (Person person : expense.getPersonsList()) {
                Ui.printHowMuchDidPersonSpend(person.getName());
                String amountString = Storage.getScanner().nextLine().strip();
                if (amountString.equalsIgnoreCase("equal") && amountBeingPaid.isEmpty()) {
                    assignEqualAmounts(payer, expense, amountBeingPaid);
                    return;
                } else {
                    try {
                        double amount = Double.parseDouble(amountString);
                        total += amount;
                        if (total > expense.getAmountSpent()) {
                            Ui.printIncorrectAmount(expense.getAmountSpent());
                            updateIndividualSpending(expense);
                            return;
                        } else {
                            amountBeingPaid.put(person, amount);
                        }
                    } catch (NumberFormatException e) {
                        Ui.argNotNumber();
                        updateIndividualSpending(expense);
                        return;
                    }
                }
            }
            if (total < expense.getAmountSpent()) {
                Ui.printIncorrectAmount(expense.getAmountSpent());
                updateIndividualSpending(expense);
            } else {
                assignAmounts(payer, expense, amountBeingPaid);
            }
        } else {
            Ui.printPersonNotInExpense();
            Ui.printPeopleInvolved(expense.getPersonsList());
            updateIndividualSpending(expense);
        }
    }

    private static Person checkValidPersonInExpense(String name, Expense expense) {
        for (Person person : expense.getPersonsList()) {
            if (name.equalsIgnoreCase(person.getName())) {
                return person;
            }
        }
        return null;
    }

    private static void assignEqualAmounts(Person payer, Expense expense, HashMap<Person, Double> amountBeingPaid) {
        double total = 0.0;
        double amount = Double.parseDouble(String.format("%.02f",
                (expense.getAmountSpent() / expense.getPersonsList().size())));
        for (Person people : expense.getPersonsList()) {
            amountBeingPaid.put(people, amount);
            total += amount;
        }
        //This will cause payer to bear the deficit or surplus
        if (total != expense.getAmountSpent()) {
            double payerAmount = amountBeingPaid.get(payer) + (expense.getAmountSpent() - total);
            amountBeingPaid.put(payer, payerAmount);
        }
        assignAmounts(payer, expense, amountBeingPaid);
    }

    private static void assignAmounts(Person payer, Expense expense, HashMap<Person, Double> amountBeingPaid) {
        for (Person person : expense.getPersonsList()) {
            if (person == payer) {
                person.setMoneyOwed(person, amountBeingPaid.get(person));
                expense.setAmountSplit(person, amountBeingPaid.get(person));
                continue;
            }
            payer.setMoneyOwed(person, amountBeingPaid.get(person));
            person.setMoneyOwed(payer, -amountBeingPaid.get(person));
            person.setMoneyOwed(person, amountBeingPaid.get(person));
            expense.setAmountSplit(person, amountBeingPaid.get(person));
        }
    }

    private static void executeAmount(String name) {
        Trip trip = Storage.getOpenTrip();
        Person toBeChecked = checkValidPersonInTrip(name, trip);
        if (toBeChecked == null) {
            Ui.printPersonNotInTrip();
        } else {
            Ui.printAmount(toBeChecked, trip);
        }
    }

    private static Person checkValidPersonInTrip(String name, Trip trip) {
        for (Person person : trip.getListOfPersons()) {
            if (name.equalsIgnoreCase(person.getName())) {
                return person;
            }
        }
        return null;
    }

    private static boolean checkValidCommand(String inputCommand) {
        return Storage.getValidCommands().contains(inputCommand);
    }

    /**
     * Obtains a list of Person objects from array of names of people.
     *
     * @param userInput the input of the user
     * @return listOfPersons ArrayList containing Person objects included in the expense
     */
    private static ArrayList<Person> checkValidPersons(Trip currentTrip, String userInput) {
        String[] listOfPeople = userInput.split("/")[0].split(",");
        ArrayList<Person> validListOfPeople = new ArrayList<>();
        Storage.getLogger().log(Level.INFO, "Checking if names are valid");
        for (String name : listOfPeople) {
            for (Person person : currentTrip.getListOfPersons()) {
                if (name.trim().equalsIgnoreCase(person.getName())) {
                    validListOfPeople.add(person);
                    break;
                }
            }
        }
        return validListOfPeople;
    }

    private static String getDescription(String userInput) {
        return userInput.split("/")[1].trim();
    }

    private static void editTripPerAttribute(Trip tripToEdit, String attributesToEdit) {
        String[] attributesToEditSplit = attributesToEdit.split("-");
        for (String attributeToEdit : attributesToEditSplit) {
            String[] splitCommandAndData = attributeToEdit.split(" ");
            String data = splitCommandAndData[1];
            switch (splitCommandAndData[0]) {
            /*case "budget":
                tripToEdit.setBudget(data);
                break;*/
            case "location":
                tripToEdit.setLocation(data);
                break;
            case "date":
                tripToEdit.setDateOfTrip(data);
                break;
            case "exchangerate":
                tripToEdit.setExchangeRate(data);
                break;
            //TODO: confirm syntax for input
            case "basecur":
                tripToEdit.setRepaymentCurrency(data);
                break;
            //TODO: confirm syntax for input
            case "paycur":
                tripToEdit.setForeignCurrency(data);
                break;
            case "person":
                //TODO: add edit persons branch
                break;
            default:
                System.out.println(splitCommandAndData[0] + "was not recognised. "
                        + "Please try again after this process is complete");
            }
        }
    }
}
