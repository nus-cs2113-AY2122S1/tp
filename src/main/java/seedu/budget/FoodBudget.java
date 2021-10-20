package seedu.budget;

import seedu.entry.ExpenseCategory;

public class FoodBudget extends Budget {

    public FoodBudget(double limit) {
        this.category = ExpenseCategory.FOOD;
        this.name = ExpenseCategory.FOOD.toString();
        this.limit = limit;
    }
}
