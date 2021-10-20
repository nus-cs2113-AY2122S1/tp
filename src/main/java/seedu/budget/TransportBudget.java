package seedu.budget;

import seedu.entry.ExpenseCategory;

public class TransportBudget extends Budget {

    public TransportBudget(double limit) {
        this.category = ExpenseCategory.TRANSPORT;
        this.name = ExpenseCategory.TRANSPORT.toString();
        this.limit = limit;
    }
}
