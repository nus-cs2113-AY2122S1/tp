package seedu.reminder;

public abstract class BudgetReminder {
    protected String month;
    protected String budgetName;
    protected double currBudgetAmount;
    protected double budgetLimit;

    protected double roundTwoDecimalPlace(double value) {
        double intermediateValue = Math.round(value * 100);
        double roundedValue = intermediateValue / 100;
        return roundedValue;
    }

    @Override
    public abstract String toString();
}
