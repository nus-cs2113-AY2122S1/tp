package constants;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    // expense error messages
    public static final String addExpenseErrorMsg = "Error with adding expense. Ensure you keyed "
            + "in both an expense name and a value and try again!";

    public static final String deleteExpenseErrorMsg = "Error with deleting expense. Ensure you "
            + "keyed in the correct id or expense name and try again!";

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
}
