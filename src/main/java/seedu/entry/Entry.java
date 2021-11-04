package seedu.entry;

import java.time.LocalDate;


public abstract class Entry {
    protected String description;
    protected double value;
    protected LocalDate date;
    protected static final String DATE_FORMAT = "dd/MM/yyyy";

    public String getDescription() {
        return this.description;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double newValue) {
        this.value = newValue;
    }

    public LocalDate getDate() {
        return this.date;
    }
    
    public abstract Enum getCategory();

    public abstract String toString();
}
