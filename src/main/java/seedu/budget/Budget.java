package seedu.budget;

import seedu.entry.Expense;
import java.util.ArrayList;

public abstract class Budget {
    protected String name;
    protected int limit;

    public String getName() {
        return this.name;
    }

    public void setLimit(int amount) {
        this.limit = amount;
    }

    public int getLimit() {
        return this.limit;
    }

    public int calAmount(ArrayList<Expense> entries) {
        int amount = 0;
        for (Expense expense : entries) {
            if (expense.getCategory().equals(name)) {
                amount += expense.getValue();
            }
        }
        return amount;
    }
}
