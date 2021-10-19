package seedu.budget;

import seedu.entry.ExpenseCategory;

public class MiscBudget extends Budget {
    public MiscBudget(int limit) {
        this.category = ExpenseCategory.MISC;
        this.name = ExpenseCategory.MISC.toString();
        this.limit = limit;
    }
}
