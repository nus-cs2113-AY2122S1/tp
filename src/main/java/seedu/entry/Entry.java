package seedu.entry;

import java.time.LocalDate;


public abstract class Entry {
    protected String description;
    protected double value;
    protected LocalDate date;

    public String getDescription() {
        return this.description;
    }

    public double getValue() {
        return this.value;
    }

    public LocalDate getDate() {
        return this.date;
    }
    
    public abstract Enum getCategory();

    public abstract String toString();
}
