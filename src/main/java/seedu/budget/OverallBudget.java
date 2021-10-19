package seedu.budget;

import seedu.entry.Expense;

import java.util.ArrayList;

public class OverallBudget extends Budget {

    public OverallBudget(int limit) {
        this.category = null;
        this.name = "OVERALL";
        this.limit = limit;
    }

    @Override
    public int calAmount(ArrayList<Expense> entries) {
        int amount = 0;
        for (Expense expense : entries) {
            amount += expense.getValue();
        }
        return amount;
    }
}
