package seedu.reminder;

public class ExceededBudgetNearingOverallReminder extends DoubleReminder {

    public ExceededBudgetNearingOverallReminder(String month, String budgetName, double currBudgetAmount,
                                                double budgetLimit, double currOverallAmount,
                                                double overallLimit, double totalBudget) {
        this.month = month;
        this.budgetName = budgetName;
        this.currBudgetAmount = currBudgetAmount;
        this.budgetLimit = budgetLimit;
        this.currOverallAmount = currOverallAmount;
        this.overallLimit = overallLimit;
        this.totalBudget = totalBudget;
    }

    @Override
    public String toString() {
        double extendedBudget = overallLimit - totalBudget + budgetLimit;
        if (extendedBudget < currBudgetAmount) {
            return "Exceeded " + month + " " + budgetName + " budget ($"
                    + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit)
                    + ") and nearing " + month + " OVERALL budget ($" + roundTwoDecimalPlace(currOverallAmount)
                    + "/$" + roundTwoDecimalPlace(overallLimit) + ")."
                    + "\n Consider adjusting your OVERALL budget before adjusting your " + budgetName + " budget!"
                    + "\n Currently you cannot extend your " + budgetName
                    + " budget without first extending your OVERALL budget!";
        } else {
            return "Exceeded " + month + " " + budgetName + " budget ($"
                    + roundTwoDecimalPlace(currBudgetAmount) + "/$" + roundTwoDecimalPlace(budgetLimit)
                    + ") and nearing " + month + " OVERALL budget ($" + roundTwoDecimalPlace(currOverallAmount)
                    + "/$" + roundTwoDecimalPlace(overallLimit) + ")."
                    + "\n Consider adjusting your OVERALL budget before adjusting your " + budgetName + " budget!"
                    + "\n Currently you can extend your " + budgetName
                    + " budget up until $" + roundTwoDecimalPlace(extendedBudget) + "!";
        }
    }
}
