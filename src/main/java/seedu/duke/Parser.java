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
        if (!checkValidCommand(inputCommand) || (userInputSplit.length == 1 && !inputCommand.equals("quit"))) {
            Ui.printUnknownCommandError();
            return true;
        } else if (!inputCommand.equals("quit")) {
            inputDescription = userInputSplit[1];
        }

        switch (inputCommand) {
        case "create":
            String[] newTripInfo = inputDescription.split(" ", 4);
            Trip newTrip = new Trip(newTripInfo);
            Storage.listOfTrips.add(newTrip);
            System.out.println("Your trip to " + newTrip.getLocation() + " on "
                    + newTrip.getDateOfTripString() + " has been successfully added!");
            break;
        case "edit":
            String[] tripToEditInfo = inputDescription.split(" ", 2);
            try {
                int indexToEdit = Integer.parseInt(tripToEditInfo[0]) - 1;
                String attributesToEdit = tripToEditInfo[1];
                Trip tripToEdit = Storage.listOfTrips.get(indexToEdit);
                editTripPerAttribute(tripToEdit, attributesToEdit);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Sorry, no such trip number exists. Please check your trip number and try again.");
            }
            break;
        case "summary":
            String[] tripToGetInfo = inputDescription.split(" ", 2);
            String tripNumber = tripToGetInfo[0];
            try {
                int indexToGet = Integer.parseInt(tripToGetInfo[0]) - 1;
                Trip tripToGet = Storage.listOfTrips.get(indexToGet);
                Ui.printExpensesSummary(tripToGet);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no matching trip index. Please try again.");
            }
            break;
        case "view":
            tripToGetInfo = inputDescription.split(" ", 2);
            try {
                int indexToGet = Integer.parseInt(tripToGetInfo[0]) - 1;
                Trip tripToGet = Storage.listOfTrips.get(indexToGet);
                tripToGet.viewAllExpenses();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no matching trip index. Please try again.");
            }
            break;
        case "delete":
            deleteTrip(Storage.listOfTrips, inputDescription);
            break;

        case "expense":
            String[] expenseInfo = inputDescription.split(" ", 4);
            int tripIndex = Integer.parseInt(expenseInfo[0]) - 1;
            Trip currentTrip = Storage.listOfTrips.get(tripIndex);
            Double expenseAmount = Double.parseDouble(expenseInfo[1]);
            String expenseCategory = expenseInfo[2].toLowerCase();
            ArrayList<Person> listOfPersonsIncluded = checkValidPersons(currentTrip, expenseInfo[3]);
            String expenseDescription = getDescription(expenseInfo[3]);
            currentTrip.addExpense(
                    new Expense(expenseAmount, expenseCategory, listOfPersonsIncluded, expenseDescription));
            Ui.printExpenseAddedSuccess();
            break;
        case "quit":
            Ui.goodBye();
            return false;
        default:
            Ui.printUnknownCommandError();
        }
        return true;
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
            for (Person person : currentTrip.getListOfPersons()){
                if (name.trim().equalsIgnoreCase(person.getName())){
                    validListOfPeople.add(person);
                    break;
                }
            }
        }
        return validListOfPeople;
    }

    private static String getDescription(String userInput){
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

    private static void deleteTrip(ArrayList<Trip> listOfTrips, String s) {
        try {
            int indexToDelete = Integer.parseInt(s);
            Trip tripToRemove = listOfTrips.get(indexToDelete - 1);
            listOfTrips.remove(indexToDelete - 1);
            System.out.println("Your trip to " + tripToRemove.getLocation() + " on "
                    + tripToRemove.getDateOfTripString() + " has been successfully removed");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Sorry, no such trip number exists. Please check your trip number and try again.");
        }
    }


}
