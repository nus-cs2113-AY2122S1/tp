package seedu.budget;

import seedu.entry.Expense;
import seedu.entry.ExpenseCategory;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Budget {
    protected ExpenseCategory category;
    protected String name;
    protected int limit;

    public ExpenseCategory getCategory() {
        return this.category;
    }

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
            if ((expense.getCategory() == this.category) & (expense.getDate().getMonth() == LocalDate.now().getMonth())){
                amount += expense.getValue();
            }
        }
        return amount;
    }
}
