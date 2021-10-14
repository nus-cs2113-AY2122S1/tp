package seedu.entry;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Entry {
    protected String description;
    protected double value;
    protected String date;

    public String getDescription() {
        return this.description;
    }

    public double getValue() {
        return this.value;
    }
    
    public void setDate(String editDate) {
        LocalDate newDate = LocalDate.parse(editDate);
        this.date = newDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    public abstract String toString();
}
