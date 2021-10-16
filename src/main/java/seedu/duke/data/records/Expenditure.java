package seedu.duke.data.records;

import java.time.LocalDate;

public class Expenditure extends Record {
    protected String description;
    protected LocalDate date;

    public Expenditure(String description, double amount, LocalDate date) {
        super(amount, date.getMonthValue());
        this.date = date;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date.toString();
    }

    public String toString() {
        return (this.description + " | $" + this.amount + " | " + this.date.toString());
    }
}
