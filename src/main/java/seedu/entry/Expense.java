package seedu.entry;

import java.time.LocalDate;

public class Expense extends Entry {

    public Expense(String description, double value) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
        this.category = category;
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[E] " + description + " - $" + valueTwoDecimalPoint;
    }
}
