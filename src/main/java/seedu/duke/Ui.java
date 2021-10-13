package seedu.duke;

public class Ui {

    public static void printPendingCommand() {
        System.out.print("Enter your command: ");
    }

    public static void printWelcome() {
        System.out.println("Hello!");
    }

    public static void goodBye() {
        System.out.println("Goodbye!");
    }

    /**
     * Prints a line for each person who owes the user money.
     *
     * @param person a person in the given trip
     */
    public static void printWhoOwesMe(Person person) {
        System.out.println(person.getName() + " | " + person.getAmtOwedToUser());
    }

    public static void printMoney(double val) {
        String money = String.format("%.02f", val);
        System.out.println(money);
    }

    public static void printExpenseDetails(Expense e) {
        System.out.println(e);
    }

    public static void printExpensesSummary(Trip t) {
        System.out.println("This is the summary for your " + t.getLocation() + " trip " + t.getDateOfTripString());
        System.out.print("Total budget for this trip: ");
        printMoney(t.getBudget());
        System.out.print("Total expenditure so far: ");
        printMoney(t.getTotalExpenses());
        System.out.print("Current budget left for this trip: ");
        printMoney(t.getBudgetLeft());
    }

    public static void printExpenseAddedSuccess() {
        System.out.println("Your expense has been added successfully");
    }

    public static void printExpensesInList(Expense expense, int index) {
        System.out.print(index + ". " + expense.getDescription() + " | Cost: ");
        printMoney(expense.getAmountSpent());
    }

    public static void printOpenTripMessage(Trip trip) {
        System.out.println("You have opened the following trip: "
                + System.lineSeparator()
                + trip.getLocation() + " | " + trip.getDateOfTripString());
    }

    public static void printTripsInList(Trip trip, int index) {
        System.out.println(index + ". " + trip.getLocation() + " | " + trip.getDateOfTripString());
    }

    public static void printCreateFormatError() {
        System.out.println("Please format your inputs as follows: "
                + System.lineSeparator()
                + "create [place] [date] [exchange rate] [budget] [people].");
    }

    public static void printExpenseFormatError() {
        System.out.println("Please format your inputs as follows: "
                + System.lineSeparator()
                + "expense [amount] [category] [people] /[description].");
    }

    public static void printBudgetFormatError() {
        System.out.print("Please re-enter your budget as a decimal number (e.g. 2000.00): ");
    }

    public static void printExchangeRateFormatError() {
        System.out.print("Please re-enter your exchange rate as a decimal number (e.g. 1.32): ");
    }

    public static void printDateTimeFormatError() {
        System.out.print("Please check that your date-time format is dd-MM-yyyy. "
                + "Please enter the date again: ");
    }

    public static void printUnknownCommandError() {
        System.out.println("Sorry, we didn't recognize your entry. Please try again, or enter -help "
                + "to learn more.");
    }

    public static void printSingleUnknownTripIndexError() {
        System.out.println("Please re-enter your trip number. You should only enter a single trip number at a time");
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

    public static void printDeleteTripSuccessful(String tripLocation, String tripDate) {
        System.out.println("Your trip to " + tripLocation + " on "
                + tripDate + " has been successfully removed");
    }

    public static void printDeleteExpenseSuccessful(Double expenseAmount) {
        System.out.println("Your expense of " + expenseAmount + " has been successfully removed");
    }

    public static void printNoExpensesError() {
        System.out.println("There are no expenses in your trip, please add an expense using the keyword 'expense'.");
    }

    public static void printNoOpenTripError() {
        System.out.println("You have not opened any trip yet. Please open a trip to edit expenses within the trip.");
        printAllTrips();
        System.out.print("Please enter the trip you would like to open: ");
    }

    public static void printAllTrips() {
        System.out.println("List of Trips: ");
        for (int i = 0; i < Storage.listOfTrips.size(); i++) {
            System.out.print("\t");
            System.out.println(i + 1 + ". "
                    + Storage.listOfTrips.get(i).getLocation() + " "
                    + Storage.listOfTrips.get(i).getDateOfTripString());

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

    public static void emptyArgForDeleteCommand() {
        System.out.println();
        System.out.println("Which trip to delete?");
        System.out.println("Syntax: delete [trip number]");
        System.out.println("---------------------------");
        printAllTrips();
        System.out.println("---------------------------");
    }

    public static void printInvalidDeleteFormatError() {
        System.out.println("Your current format is wrong. Please follow the proper format of 'delete type index'.");
    }
}
