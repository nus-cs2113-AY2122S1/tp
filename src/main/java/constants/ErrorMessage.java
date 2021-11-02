package constants;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    // expense error messages
    public static final String addExpenseErrorMsg = "Error with adding expense. Ensure you keyed "
            + "in both an expense name and a value and try again!";

    public static final String deleteExpenseErrorMsg = "Error with deleting expense. Ensure you "
            + "keyed in the correct id or expense name and try again!";

    public static final String updateExpenseErrorMsg = "Error with updating the expense. Ensure you "
            + "keyed in the correct id or expense name and try again!";

    public static final String expenseWarningErrorMsg = "Error with setting your warning limit. Ensure "
            + "you keyed in the correct value and try again!";

    public static final String incorrectExpenseValueMsg = "Your expense value cannot be $0 or any "
            + "negative amount. Check that you have entered the correct value and try again!";

    public static final String tooLongNameErrorMsg = "The expense name you entered is too long. "
            + "A maximum of 25 characters is allowed for the expense name. Please try again!";

    public static final String cannotFindSuchItemErrorMsg = "Could not find such an item. Ensure that "
            + "you have correctly entered the expense name and try again!";

    // income error messages
    public static final String addIncomeErrorMsg = "Error with adding income. Ensure you keyed "
            + "in both an income name and a value and try again!";

    public static final String deleteIncomeErrorMsg = "Error with deleting income. Ensure you "
            + "keyed in the correct id or income name and try again!";

    public static final String updateIncomeErrorMsg = "Error with updating income. Ensure you keyed "
            + "the income name and try again!";

    // budget error messages
    public static final String addBudgetErrorMsg = "Error with adding budget. Ensure you keyed "
            + "the budget value and try again!";

    public static final String deleteBudgetErrorMsg = "Error with deleting budget. Currently you have no "
            + "budget stored in the system!";

    public static final String updateBudgetErrorMsg = "Error with updating budget. Ensure you keyed "
            + "the budget value and try again!";

    public static final String invalidIndexMsg = "Invalid index";
}
