package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Income extends Entry {

    public Income(String description, double value, String category) {
        this.description = description;
        this.value = value;
        this.date = LocalDate.now();
        this.category = category;
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[I] on " + date.format(DateTimeFormatter.ofPattern("dd MMM yyy")) + ": " 
                + description + " (" + category + ")" + " - $" + valueTwoDecimalPoint;
    }
}
