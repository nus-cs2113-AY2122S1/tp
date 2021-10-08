package seedu.duke.data.records;

import java.time.LocalDate;

public class Expenditure extends Record {
    public static String description;
    public static double spending;
    public static LocalDate date;

    public Expenditure(String description, double spending, LocalDate date) {
        super(spending, date.getMonthValue());
        this.date = date;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getAmount() {
        return spending;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public int getMonth() {
        return date.getMonthValue();
    }


    public String toString() {
        return (this.description + " $" + this.spending + "Month: " + this.date);
    }

    @Override
    public String getType() {
        return "Expenditure";
    }
}
