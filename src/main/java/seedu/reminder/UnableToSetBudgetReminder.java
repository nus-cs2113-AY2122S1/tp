package seedu.reminder;

public class UnableToSetBudgetReminder extends BudgetReminder {
    private double overallLimit;
    private double newTotalBudget;

    public UnableToSetBudgetReminder(String budgetName, double currBudgetAmount, double overallLimit,
                                     double newTotalBudget) {
        this.month = null;
        this.budgetName = budgetName;
        this.currBudgetAmount = currBudgetAmount;
        this.budgetLimit = 0;
        this.overallLimit = overallLimit;
        this.newTotalBudget = newTotalBudget;
    }

    @Override
    public String toString() {
        return budgetName + " budget must be greater than current budget spending ($"
                + roundTwoDecimalPlace(currBudgetAmount)
                + ") and total of all sub-budgets must be smaller than OVERALL budget ($"
                + roundTwoDecimalPlace(newTotalBudget) + "/$ " + roundTwoDecimalPlace(overallLimit) + ")";
    }
}
