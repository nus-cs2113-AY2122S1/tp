package seedu.budget;

import seedu.entry.Expense;

import java.util.ArrayList;

public class OverallBudget extends Budget {

    public OverallBudget(double limit) {
        this.category = null;
        this.name = "OVERALL";
        this.limit = limit;
    }

    @Override
    public double calAmount(ArrayList<Expense> entries) {
        double amount = 0;
        for (Expense expense : entries) {
            amount += expense.getValue();
        }
        assert amount >= 0;
        return amount;
    }
}
