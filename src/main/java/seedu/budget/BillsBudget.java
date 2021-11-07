package seedu.budget;

import seedu.entry.ExpenseCategory;

public class BillsBudget extends Budget {
    public BillsBudget(double limit) {
        this.category = ExpenseCategory.BILLS;
        this.name = ExpenseCategory.BILLS.toString();
        this.limit = limit;
    }
}
