package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense extends Entry {
    private ExpenseCategory category;
    
    public Expense(String description, double value, ExpenseCategory category) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
        this.category = category;
    }

    public Expense(String description, double value, ExpenseCategory category, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    @Override
    public ExpenseCategory getCategory() {
        return category;
    }
    
    @Override
    public String toString() {
        String expenseDate = date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return String.format("[E] %s - $%.2f (%s)", description, value, expenseDate);
    }
}
