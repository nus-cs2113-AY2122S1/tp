package service;

public class BudgetManager {
    private static BudgetManager budgetMgr;

    private BudgetManager() {
    }

    public static BudgetManager getBudgetManager() {
        if (budgetMgr == null) {
            budgetMgr = new BudgetManager();
        }

        return budgetMgr;
    }
}
