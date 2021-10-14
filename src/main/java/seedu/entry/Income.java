package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Income extends Entry {

    public Income(String description, double value) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyy"));
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[I] on " + date + ": " + description + " - $" + valueTwoDecimalPoint;
    }
}
