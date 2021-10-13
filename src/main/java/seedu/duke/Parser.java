package seedu.duke;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Parser {

    /**
     * Parses the user-entered command and additional information/flags.
     *
     * @param userInput the {@link String} containing the user input
     * @return whether the program should continue running after processing the given user input
     */
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
            Ui.printNoTripError();
            return true;
        } else if (inputCommand.equals("close")) {
            Storage.closeTrip();
            return true;
        } else if (!checkValidCommand(inputCommand)) {
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
            executeView();
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

        default:
            Ui.printUnknownCommandError();
        }
        return true;
    }

    private static void executeCreate(String indexAsString) {
        String[] newTripInfo = indexAsString.split(" ", 5);
        Trip newTrip = new Trip(newTripInfo);
        Storage.listOfTrips.add(newTrip);
        System.out.println("Your trip to " + newTrip.getLocation() + " on "
                + newTrip.getDateOfTripString() + " has been successfully added!");
    }

    private static void executeEdit(String inputDescription) {
        String[] tripToEditInfo = inputDescription.split(" ", 2);
        int indexToEdit = Integer.parseInt(tripToEditInfo[0]) - 1;
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

    private static void executeView() {
        Storage.getOpenTrip().viewAllExpenses();
    }

    private static void executeDelete(String inputParams) {
        String[] splitInputParams = inputParams.split(" ", 2);
        String type = splitInputParams[0];
        Integer index = Integer.parseInt(splitInputParams[1]) - 1;
        if (type.equals("trip")) {
            executeDeleteTrip(index);
        } else if (type.equals("expense")) {
            executeDeleteExpense(index);
        } else {
            Ui.printInvalidDeleteFormatError();
        }

    }

    private static void executeDeleteExpense(Integer expenseIndex) {
        try {
            Trip currentTrip = Storage.getOpenTrip();
            Expense expenseToDelete = currentTrip.getListOfExpenses().get(expenseIndex);
            Double expenseAmount = expenseToDelete.getAmountSpent();
            currentTrip.removeExpense(expenseIndex);
            Ui.printDeleteExpenseSuccessful(expenseAmount);
        } catch (IndexOutOfBoundsException e) {
            Ui.printUnknownExpenseIndexError();
        }
    }

    private static void executeDeleteTrip(Integer tripIndex) {
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
        String[] expenseInfo = inputDescription.split(" ", 3);
        Double expenseAmount = Double.parseDouble(expenseInfo[0]);
        String expenseCategory = expenseInfo[1].toLowerCase();
        ArrayList<Person> listOfPersonsIncluded = checkValidPersons(Storage.getOpenTrip(), expenseInfo[2]);
        String expenseDescription = getDescription(expenseInfo[2]);
        currTrip.addExpense(new Expense(expenseAmount, expenseCategory, listOfPersonsIncluded, expenseDescription));
        Ui.printExpenseAddedSuccess();
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
            case "budget":
                tripToEdit.setBudget(data);
                break;
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
