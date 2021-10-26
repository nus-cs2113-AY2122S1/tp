package seedu.duke.data.records;

import java.time.LocalDate;

public class Expenditure extends Record {
    protected String description;
    protected LocalDate date;
    protected Category category;

    public Expenditure(String description, double amount, LocalDate date, Category category) {
        super(amount, date.getMonthValue());
        this.date = date;
        this.description = description;
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date.toString();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category.toString();
    }

    public String toString() {
        return String.format("%-20.20s  %-20.20s %-20.20s %-20.20s",
                this.description, " | $" + this.amount, " | " + this.date.toString(), " | " + this.category.toString());
    }
}
