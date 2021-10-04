package service;

public class ExpenseManager {
    private static ExpenseManager expenseMgr;

    private ExpenseManager() {
    }

    public static ExpenseManager getExpenseManager() {
        if(expenseMgr == null)
            expenseMgr = new ExpenseManager();

        return expenseMgr;
    }
}
