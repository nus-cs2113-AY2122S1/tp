package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Expense extends Entry {

    public Expense(String description, double value, String category) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
        this.category = category;
    }

    public Expense(String description, double value, String category, LocalDate date) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[E] on " + date.format(DateTimeFormatter.ofPattern("dd MMM yyy")) + ": " 
                + description + " (" + category + ")" + " - $" + valueTwoDecimalPoint;
    }
}
