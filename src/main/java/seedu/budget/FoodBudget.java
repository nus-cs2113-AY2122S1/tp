package seedu.budget;

import seedu.entry.ExpenseCategory;

public class FoodBudget extends Budget {

    public FoodBudget(int limit) {
        this.category = ExpenseCategory.FOOD;
        this.name = ExpenseCategory.FOOD.toString();
        this.limit = limit;
    }
}
