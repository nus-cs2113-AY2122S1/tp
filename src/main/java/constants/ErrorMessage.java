package constants;

public final class ErrorMessage {

    private ErrorMessage() {
    }

    public static final String addExpenseErrorMsg = "Error with adding expense. Ensure you keyed "
            + "in both an expense name and a value and try again!";
    public static final String deleteExpenseErrorMsg = "Error with deleting expense. Ensure you "
            + "keyed in the correct id or expense name and try again!";
    public static final String addBudgetErrorMsg = "Error with adding budget. Ensure you keyed "
            + "the budget value and try again!";
    public static final String deleteBudgetErrorMsg = "Error with deleting budget.";

}
