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
        String[] userInputSplit = userInput.split(" ", 2);
        String inputCommand = userInputSplit[0].toLowerCase();
        String inputDescription = null;
        if (userInputSplit[0].equals("close")) {
            Storage.closeTrip();
            return true;
        }
        if (Storage.listOfTrips.isEmpty() && !inputCommand.equals("create") && !inputCommand.equals("quit")) {
            Ui.printNoTripError();
            return true;
        } else if (!checkValidCommand(inputCommand)) {
            Ui.printUnknownCommandError();
            return true;
        } else if (inputCommand.equals("list") && userInputSplit.length > 1
                && userInputSplit[1].equals("trips")) {
            inputDescription = userInputSplit[1];
        } else if (!(inputCommand.equals("view") || inputCommand.equals("summary")
                || inputCommand.equals("quit") || inputCommand.equals("list"))) {
            inputDescription = userInputSplit[1];
        }

        switch (inputCommand) {
        case "create":
            executeCreate(inputDescription);
            break;

        case "edit":
            executeEdit(inputDescription);
            break;

        case "open":
            executeOpen(inputDescription);
            break;

        case "summary":
            executeSummary();
            break;

        case "view":
            executeView();
            break;

        case "delete":
            executeDelete(inputDescription);
            break;

        case "list":
            executeList(inputDescription);
            break;

        case "expense":
            executeExpense(inputDescription);
            break;

        case "quit":
            Ui.goodBye();
            return false;

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

    private static void executeList(String inputDescription) {
        int index = 1;
        if (inputDescription != null) {
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

    private static void executeOpen(String inputDescription) {
        try {
            int indexToGet = Integer.parseInt(inputDescription) - 1;
            Storage.setOpenTrip(Storage.listOfTrips.get(indexToGet));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            Ui.printSingleUnknownTripIndexError();
        }
    }

    private static void executeCreate(String inputDescription) {
        String[] newTripInfo = inputDescription.split(" ", 5);
        Trip newTrip = new Trip(newTripInfo);
        Storage.listOfTrips.add(newTrip);
        System.out.println("Your trip to " + newTrip.getLocation() + " on "
                + newTrip.getDateOfTripString() + " has been successfully added!");
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
