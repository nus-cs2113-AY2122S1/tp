package seedu.duke.data;

import seedu.duke.data.budget.Budget;

import java.util.ArrayList;

public class BudgetList {
    public static int numberOfBudget = 0;
    public static ArrayList<Budget> budgetArrayList = new ArrayList<>();

    public void addBudgetList(String description, double spendingLimit, int month) {
        budgetArrayList.add(new Budget(description, spendingLimit, month));
        numberOfBudget += 1;
    }

    public void printBudgetList(int indexOfExpenditure) {
        budgetArrayList.get(indexOfExpenditure).printBudgetDetails();
    }
}
