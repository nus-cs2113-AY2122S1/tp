package seedu.duke;

import java.util.ArrayList;

public class Parser {

    /**
     * Parses the user-entered command and additional information/flags.
     *
     * @param userInput the {@link String} containing the user input
     * @return whether the program should continue running after processing the given user input
     */
    public static boolean parseUserInput(String userInput) {
        String[] userInputSplit = new String[2];
        String[] rawInput =userInput.split(" ", 2);

        if (rawInput.length == 2){
            userInputSplit[0] = rawInput[0];
            userInputSplit[1] = rawInput[1];
        }
        else{
            userInputSplit[0] = rawInput[0];
        }


        String inputCommand = userInputSplit[0].toLowerCase();

        if (inputCommand.equals("quit")) {
            Ui.goodBye();
            return false;
        } else if (Storage.listOfTrips.isEmpty() && !inputCommand.equals("create")) {
            Ui.printNoTripError();
            return true;
        } else if (userInputSplit[0].equals("close")) {
            Storage.closeTrip();
            return true;
        } else if (!checkValidCommand(inputCommand)) {
            Ui.printUnknownCommandError();
            return true;
        }

        switch (inputCommand) {
        case "create":
            try {
                executeCreate(userInputSplit[1]);
            } catch (IndexOutOfBoundsException e) {
                Ui.printCreateFormatError();
            }
            break;

        case "edit":
            executeEdit(userInputSplit[1]);
            break;

        case "open":
            executeOpen(userInputSplit[1]);
            break;

        case "summary":
            executeSummary();
            break;

        case "view":
            executeView();
            break;

        case "delete":
            executeDelete(userInputSplit[1]);
            break;

        case "list":
            executeList();
            break;

        case "expense":
            try {
                executeExpense(userInputSplit[1]);
            } catch (IndexOutOfBoundsException e) {
                Ui.printExpenseFormatError();
            }
            break;

        default:
            Ui.printUnknownCommandError();
        }
        return true;
    }

    private static void executeExpense(String inputDescription) {
        String[] expenseInfo = inputDescription.split(" ", 3);
        Double expenseAmount = Double.parseDouble(expenseInfo[0]);
        String expenseCategory = expenseInfo[1].toLowerCase();
        ArrayList<Person> listOfPersonsIncluded = checkValidPersons(Storage.getOpenTrip(), expenseInfo[2]);
        String expenseDescription = getDescription(expenseInfo[2]);
        Storage.getOpenTrip().addExpense(
                new Expense(expenseAmount, expenseCategory, listOfPersonsIncluded, expenseDescription));
        Ui.printExpenseAddedSuccess();

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

    private static void executeDelete(String inputDescription) {
        int tripIndex = Integer.parseInt(inputDescription) - 1;
        deleteTrip(tripIndex);
    }

    private static void executeView() {
        try {
            Storage.getOpenTrip().viewAllExpenses();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There is no matching trip index. Please try again.");
        }
    }

    private static void executeSummary() {
        try {
            Ui.printExpensesSummary(Storage.getOpenTrip());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("There is no matching trip index. Please try again.");
        }
    }

    //assumes that listOfTrips have at least 1 trip
    private static void executeOpen(String indexInString) {
        if (indexInString == null){
            Ui.emptyArgForOpenCommand();
        }
        else {
            try {
                int indexToGet = Integer.parseInt(indexInString) - 1;
                Storage.setOpenTrip(Storage.listOfTrips.get(indexToGet));
                Ui.printOpenTripMessage(Storage.listOfTrips.get(indexToGet));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                Ui.printSingleUnknownTripIndexError();
                System.out.println();
            }
        }
    }

    private static void executeCreate(String inputDescription) {
        try {
            String[] newTripInfo = inputDescription.split(" ", 5);
            Trip newTrip = new Trip(newTripInfo);
            Storage.listOfTrips.add(newTrip);
            System.out.println("Your trip to " + newTrip.getLocation() + " on "
                    + newTrip.getDateOfTripString() + " has been successfully added!");
        } catch (IndexOutOfBoundsException e) {
            Ui.printCreateFormatError();
        }
    }


    private static void executeEdit(String inputDescription) {
        String[] tripToEditInfo = inputDescription.split(" ", 2);
        try {
            int indexToEdit = Integer.parseInt(tripToEditInfo[0]) - 1;
            String attributesToEdit = tripToEditInfo[1];
            Trip tripToEdit = Storage.listOfTrips.get(indexToEdit);
            editTripPerAttribute(tripToEdit, attributesToEdit);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Sorry, no such trip number exists. Please check your trip number and try again.");
        }
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

    private static void deleteTrip(int tripIndex) {
        try {
            String tripLocation = Storage.listOfTrips.get(tripIndex).getLocation();
            String tripDate = Storage.listOfTrips.get(tripIndex).getDateOfTripString();
            Storage.listOfTrips.remove(tripIndex);
            Ui.printDeleteTripSuccessful(tripLocation, tripDate);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.printUnknownTripIndexError();
        }
    }


}
