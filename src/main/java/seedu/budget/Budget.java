package seedu.budget;

import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Budget {
    protected ExpenseCategory category;
    protected String name;
    protected double limit;

    public ExpenseCategory getCategory() {
        return this.category;
    }

    public String getName() {
        return this.name;
    }

    public void setLimit(double amount) {
        this.limit = amount;
    }

    public double getLimit() {
        return this.limit;
    }

    public double calAmount(ArrayList<Expense> entries) {
        double amount = 0;
        for (Expense expense : entries) {
            if ((expense.getCategory() == this.category) & (expense.getDate().getMonth() == LocalDate.now().getMonth())){
                amount += expense.getValue();
            }
        }
        assert amount >= 0;
        return amount;
    }
}
