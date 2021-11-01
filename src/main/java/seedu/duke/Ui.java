package seedu.duke;

import java.util.ArrayList;

public class Ui {

    public static void printPendingCommand() {
        System.out.print("Enter your command: ");
    }

    public static void printWelcome() {
        String logo = System.lineSeparator()
                + "    ____              __  ___     ____             __  " + System.lineSeparator()
                + "   / __ \\____ ___  __/  |/  ___  / __ )____ ______/ /__" + System.lineSeparator()
                + "  / /_/ / __ `/ / / / /|_/ / _ \\/ __  / __ `/ ___/ //_/" + System.lineSeparator()
                + " / ____/ /_/ / /_/ / /  / /  __/ /_/ / /_/ / /__/ ,<   " + System.lineSeparator()
                + "/_/    \\__,_/\\__, /_/  /_/\\___/_____/\\__,_/\\___/_/|_|  " + System.lineSeparator()
                + "            /____/                                     " + System.lineSeparator();
        System.out.println("Welcome to" + logo);
    }

    public static void goodBye() {
        System.out.println("Exiting the program now. Goodbye!");
    }

    public static void newTripSuccessfullyCreated(Trip newTrip) {
        System.out.println("Your trip to " + newTrip.getLocation() + " on "
                + newTrip.getDateOfTripString() + " has been successfully added!");
    }

    //@@author joshualeeky
    public static String stringForeignMoney(double val) {
        return Storage.getOpenTrip().getForeignCurrency() + " "
                //+ Storage.getOpenTrip().getForeignCurrencySymbol()
                + String.format(Storage.getOpenTrip().getForeignCurrencyFormat(), val);
    }

    public static String stringRepaymentMoney(double val) {
        return Storage.getOpenTrip().getRepaymentCurrency() + " "
                //+ Storage.getOpenTrip().getRepaymentCurrencySymbol()
                + String.format(Storage.getOpenTrip().getRepaymentCurrencyFormat(),
                val / Storage.getOpenTrip().getExchangeRate());
    }

    public static void askAutoAssignRemainder(Person person, double remainder) {
        System.out.print("Assign the remaining " + stringForeignMoney(remainder)
                + " to " + person.getName() + "? (y/n): ");
    }

    //@@author

    public static void printCancelExpenseCreation() {
        System.out.println("Your expense creation has been cancelled.");
    }

    public static void printListOfPeople(ArrayList<Person> people) {
        for (Person person : people) {
            System.out.println("\t" + person.getName());
        }
    }

    public static void printExpenseDetails(Expense e) {
        System.out.println(e);
    }

    /*public static void printExpensesSummary(Trip t) {
        System.out.println("This is the summary for your " + t.getLocation() + " trip " + t.getDateOfTripString());
        *//*System.out.println("Total budget for this trip: " + stringMoney(t.getBudget()));
        System.out.println("Total expenditure so far: " + stringMoney(t.getTotalExpenses()));
        System.out.println("Current budget left for this trip: " + stringMoney(t.getBudgetLeft()));*//*
    }*/

    public static void printFilteredExpenses(Expense e, int index) {
        System.out.println((index + 1) + ". " + e);
    }

    public static void printExpenseAddedSuccess() {
        System.out.println("Your expense has been added successfully");
    }

    public static void printExpensesInList(ArrayList<Expense> listOfExpenses) {
        if (listOfExpenses.isEmpty()) {
            printNoExpensesError();
            return;
        }
        System.out.println("List of Expenses: ");
        for (int i = 0; i < listOfExpenses.size(); i++) {
            System.out.print("\t");
            System.out.println(i + 1 + ". "
                    + listOfExpenses.get(i).getDescription() + " | "
                    + listOfExpenses.get(i).getStringDate());
        }
    }

    public static void printOpenTripMessage(Trip trip) {
        System.out.println("You have opened the following trip: "
                + System.lineSeparator()
                + trip.getLocation() + " | " + trip.getDateOfTripString());
        System.out.println();
    }

    public static void printCreateFormatError() {
        System.out.println("Please format your inputs as follows: "
                + System.lineSeparator()
                + "create [place] [date] [currency ISO] [exchange rate] [people].");
    }

    public static void printExpenseFormatError() {
        System.out.println("Please format your inputs as follows: "
                + System.lineSeparator()
                + "expense [amount] [category] [people] /[description].");
    }

    public static void printFilterFormatError() {
        System.out.println("Please format your inputs as follows: "
                + System.lineSeparator()
                + "view filter [category, payer, description, person] [search keyword]");
    }


    public static void printExchangeRateFormatError() {
        System.out.print("Please re-enter your exchange rate as a decimal number (e.g. 1.32): ");
    }

    public static void printDateTimeFormatError() {
        System.out.print("Please check that your date-time format is dd-MM-yyyy. "
                + "Please enter the date again: ");
    }

    public static void printIsoFormatError() {
        System.out.print("Please re-enter your currency ISO (e.g. JPY, USD): ");
    }

    public static void printUnknownCommandError() {
        System.out.println("Sorry, we didn't recognize your entry. Please try again, or enter help "
                + "to learn more.");
    }

    public static void printSingleUnknownTripIndexError() {
        System.out.println("Invalid trip number, try again");
        System.out.println("Syntax: open [trip number]");
        System.out.println("--------------------------");
        printAllTrips();
        System.out.println("--------------------------");
    }

    public static void printUnknownTripIndexError() {
        System.out.println("Sorry, no such trip number exists. Please check your trip number and try again.");
    }

    public static void printUnknownExpenseIndexError() {
        System.out.println("Sorry, no such expense number exists. Please check your expense number and try again.");
    }

    public static void printNoTripError() {
        System.out.println("You have not created a trip yet. Please create a trip using the keyword 'create'.");
    }

    public static void printDeleteTripSuccessful(Trip tripDeleted) {
        System.out.println("Your trip to " + tripDeleted.getLocation() + " on "
                + tripDeleted.getDateOfTripString() + " has been successfully removed.");
    }

    public static void printDeleteExpenseSuccessful(Double expenseAmount) {
        System.out.println("Your expense of " + stringForeignMoney(expenseAmount) + " has been successfully removed");
    }

    public static void printNoExpensesError() {
        System.out.println("There are no expenses in your trip, please add an expense using the keyword 'expense'.");
    }

    public static void printNoPersonFound(String string) {
        System.out.println("There are no persons with the name of [" + string + "] in this trip.");
    }

    public static void printSummaryFormatError() {
        System.out.println("Please format your inputs as follows: "
                + System.lineSeparator()
                + "\"summary\" or \"summary [person name]\".");
    }

    public static void printNoMatchingExpenseError() {
        System.out.println("No matching expenses found.");
    }

    public static void printNoOpenTripError() {
        System.out.println("You have not opened any trip yet. Please open a trip to proceed further.");
        printAllTrips();
    }

    public static void printAllTrips() {
        System.out.println("List of Trips: ");
        ArrayList<Trip> listOfTrips = Storage.getListOfTrips();
        for (int i = 0; i < listOfTrips.size(); i++) {
            System.out.print("\t");
            System.out.println(i + 1 + ". "
                    + listOfTrips.get(i).getLocation() + " | "
                    + listOfTrips.get(i).getDateOfTripString());
        }
    }

    public static void emptyArgForOpenCommand() {
        System.out.println();
        System.out.println("Which trip to open?");
        System.out.println("Syntax: open [trip number]");
        System.out.println("--------------------------");
        printAllTrips();
        System.out.println("--------------------------");

    }

    public static void argNotNumber() {
        System.out.println("Input is not a number");
    }

    public static void promptForTripIndex() {
        System.out.print("Please enter a valid trip number: ");
    }

    public static void emptyArgForDeleteCommand() {
        System.out.println();
        System.out.println("Which trip to delete?");
        System.out.println("Syntax: delete [trip number]");
        System.out.println("---------------------------");
        printAllTrips();
        System.out.println("---------------------------");
    }

    public static void invalidArgForAmount() {
        System.out.println("The person you entered is not in the opened trip, or syntax is invalid. "
                + System.lineSeparator()
                + "Please format as follows: "
                + "amount [person].");
        System.out.println("These are the people involved in this trip:");
        Ui.printListOfPeople(Storage.getOpenTrip().getListOfPersons());
        System.out.println();
    }

    public static void printInvalidDeleteFormatError() {
        System.out.println("Your current format is wrong. Please follow the proper format of 'delete type index'.");
    }

    public static void printGetPersonPaid() {
        System.out.print("Who paid for the expense?: ");
    }

    public static void printHowMuchDidPersonSpend(String name, double amountRemaining) {
        System.out.print("There is " + stringForeignMoney(amountRemaining) + " left to be assigned."
                + " How much did " + name + " spend?: ");
    }

    public static void printPersonNotInExpense() {
        System.out.println("The person you entered is not in the expense, please try again.");
    }


    public static void printAmount(Person person, Trip trip) {
        System.out.println(person.getName() + " spent "
                + stringForeignMoney(person.getMoneyOwed().get(person.getName())) // Remove .getName()
                + " (" + stringRepaymentMoney(person.getMoneyOwed().get(person.getName())) + ") on the trip so far");
        for (Person otherPerson : trip.getListOfPersons()) {
            if (otherPerson != person) {
                if (person.getMoneyOwed().get(otherPerson.getName()) > 0) {
                    System.out.println(otherPerson.getName() + " owes "
                            + stringForeignMoney(person.getMoneyOwed().get(otherPerson.getName()))
                            + " (" + stringRepaymentMoney(person.getMoneyOwed().get(otherPerson.getName())) + ")"
                            + " to " + person.getName());
                } else if (person.getMoneyOwed().get(otherPerson.getName()) < 0) {
                    System.out.println(person.getName() + " owes "
                            + stringForeignMoney(-person.getMoneyOwed().get(otherPerson.getName()))
                            + " (" + stringRepaymentMoney(-person.getMoneyOwed().get(otherPerson.getName())) + ")"
                            + " to " + otherPerson.getName());
                } else {
                    System.out.println(person.getName() + " does not owe anything to " + otherPerson.getName());
                }
            }
        }
    }

    public static void printIncorrectAmount(double amount) {
        System.out.println("The amount you have entered is incorrect, it is either too high or low. The total "
                + "of the expense should equal " + stringForeignMoney(amount));
    }

    public static void printPeopleInvolved(ArrayList<Person> personArrayList) {
        System.out.println("These are the people who are involved in the expense: ");
        printListOfPeople(personArrayList);
    }

    //@@author lixiyuan416
    public static void displayHelp() {
        if (!Storage.checkOpenTrip()) {
            System.out.println("Type \"open [trip number]\" to open your trip");
            System.out.println("While a trip is open, type \"expense\" to create an expense for that trip");
            System.out.println("Type \"quit\" to exit");
            System.out.println();
        } else {
            System.out.println("You are inside a trip. Trip specific commands:");
            System.out.println("\texpense: creates an expense");
            System.out.println("\tview: list all expenses");
            System.out.println("\tview filter [options] [search keyword]: list filtered expenses.");
            System.out.println("\t\tfilter options: [category, description, payer, person, date]");
            System.out.println("\tview [index]");
            System.out.println("\tsummary: shows how much each person spent in total for this trip");
            System.out.println("\tamount [person]: for settling repayment at the end of the trip,"
                    + "shows how much this person owes to others, "
                    + "or how much others owe this person");
            System.out.println("\topen [trip num]: open another trip");
            System.out.println("\tquit: exit the program");
            System.out.println();
        }
    }
    //@@author

    public static void printInvalidFilterError() {
        System.out.println("Please filter using the following valid filter attributes: "
                + System.lineSeparator()
                + "[category], [description], [payer], [person]");
    }

    public static void printFileNotFoundError() {
        System.out.println("No preloaded data found! We have created a file for you.");
    }

    public static void printJsonParseError() {
        //todo not sure what this should be
        System.out.println("We couldn't read your save file. It may be corrupted, "
                + "or may have been wrongly modified outside the program.");
        System.out.println("If you would like to overwrite your current save file and"
                + "start with a new save file, please enter 'y'. "
                + "Otherwise, please enter 'n' to exit the program.");
        System.out.println("IMPORTANT: if you choose to start with a new save file, your previous save file"
                + "will no longer be recoverable. This operation is irreversible.");
    }

    public static void printJsonParseUserInputPrompt() {
        System.out.print("Would you like to overwrite your save file? (y/n): ");
    }

    public static void printNoLastTripError() {
        System.out.println("You may have deleted the most recently modified trip. "
                + "Please try again with the trip number of the trip you wish to edit.");
    }

    public static void printCreateFileFailure() {
        System.out.println("The save file could not be created. Exiting the program now...");
    }

    public static void newFileSuccessfullyCreated() {
        System.out.println("A new save file has been created!");
    }

    public static void printEmptyFileWarning() {
        System.out.println("A save file was found, but it is empty.");
        System.out.println("If you wish to recover the contents of your save file, please exit the program now.");
        System.out.println("Otherwise, you may continue to use the program.");
    }

    public static void printInvalidPerson(String name) {
        System.out.println(name + " is not part of the trip. "
                + "Please enter the names of the people who are involved in this expense again, separated by a comma.");
        System.out.println("These are the names of the people who are part of the trip:");
        printListOfPeople(Storage.getOpenTrip().getListOfPersons());
    }

    public static void printTripClosed(Trip trip) {
        System.out.println("You have closed the following trip:"
                + System.lineSeparator()
                + trip.getLocation() + " | " + trip.getDateOfTripString());
    }

    //@@author lixiyuan416
    public static void equalSplitPrompt() {
        System.out.println("Enter \"equal\" if expense is to be evenly split, enter individual spending otherwise");
    }

    public static void autoAssignIndividualSpending() {
        System.out.println("Finished allocating expense amount. There are people involved that did not need to pay.");
        System.out.println();
    }

    public static void askUserToConfirm() {
        System.out.print("There will be people involved that don't need to pay, are you sure? (y/n): ");
    }

    public static void expenseDateInvalid() {
        System.out.println("\tPlease enter date as DD-MM-YYYY, or enter nothing to use today's date");
    }

    public static void expensePromptDate() {
        System.out.println("Enter date of expense:");
        System.out.println("\tPress enter to use today's date");
    }

    public static void viewFilterDateInvalid() {
        System.out.println("\tPlease enter date as DD-MM-YYYY");
    }
    //@@author

    public static void changeForeignCurrencySuccessful(Trip tripToEdit, String original) {
        System.out.println("Your foreign spending currency has been changed from "
                + original + " to " + tripToEdit.getForeignCurrency());
    }

    public static void changeHomeCurrencySuccessful(Trip tripToEdit, String original) {
        System.out.println("Your home currency has been changed from "
                + original + " to " + tripToEdit.getRepaymentCurrency());
    }

    public static void changeExchangeRateSuccessful(Trip tripToEdit, double original) {
        System.out.println("The exchange rate has been changed from "
                + original + " to " + tripToEdit.getExchangeRate());
    }

    public static void changeDateSuccessful(Trip tripToEdit, String original) {
        System.out.println("The date of your trip has been changed from "
                + original + " to " + tripToEdit.getDateOfTripString());
    }

    public static void changeLocationSuccessful(Trip tripToEdit, String original) {
        System.out.println("The location of your trip has been changed from "
                + original + " to " + tripToEdit.getLocation());
    }

    public static void printCouldNotSaveMessage() {
        System.out.println("Sorry, there was an error saving your data. We'll try to save your data again"
                + "the next time you enter a command.");
    }

    public static void printFileLoadedSuccessfully() {
        System.out.println();
        System.out.println("Your saved data was successfully loaded!");
        System.out.println();
    }

}
