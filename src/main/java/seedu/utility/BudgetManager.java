package seedu.utility;

import seedu.budget.Budget;
import seedu.budget.FoodBudget;
import seedu.budget.OverallBudget;
import seedu.budget.TransportBudget;
import seedu.entry.Expense;

import java.util.ArrayList;

public class BudgetManager {
    private double threshold;
    OverallBudget overallBudget = new OverallBudget(500);
    FoodBudget foodBudget = new FoodBudget(2000);
    TransportBudget transportBudget = new TransportBudget(0);

    public BudgetManager() {
        this.threshold = 0.1;
    }

    public void handleBudget(Expense expense, ArrayList<Expense> expenses) {
        checkBudget(expense, expenses, overallBudget);

        switch (expense.getCategory()) {
        case "FOOD":
            checkBudget(expense, expenses, foodBudget);
            break;
        case "TRANSPORT":
            checkBudget(expense, expenses, transportBudget);
            break;
        }
    }

    private void checkBudget(Expense expense, ArrayList<Expense> expenses, Budget budget) {
        if (budget.getLimit() != 0) {
            int currAmount = budget.calAmount(expenses);
            int limit = budget.getLimit();
            int diff = limit - currAmount;
            if ((diff < threshold*limit) & (diff > 0)) {
                System.out.println("You are almost reaching the " + budget.getName() + " budget of $" + limit + "!");
                System.out.println("Would you like to readjust your " + budget.getName() + " budget?");
            } else if (diff < 0) {
                System.out.println("You have exceeded the " + budget.getName() + " budget of $" + limit + "!");
                System.out.println("Would you like to readjust your " + budget.getName() + " budget?");
            }
        }
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void setBudget(int amount, Budget budget) {
        budget.setLimit(amount);
    }
}
