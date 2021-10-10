package seedu.duke.data.records;

import java.time.LocalDate;

public class Expenditure extends Record {
    public static String description;
    public static LocalDate date;

    public Expenditure(String description, double amount) {
        super(amount);
        //Expenditure.date = date;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public int getMonth() {
        return date.getMonthValue();
    }


    public String toString() {
        return (description + " $" + this.amount + " Month: " + date);
    }

    @Override
    public String getType() {
        return "Expenditure";
    }
}
