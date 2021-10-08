package seedu.duke.data.records;

import java.time.LocalDate;

public class Budget extends Record {
    public String description;
    public double spendingLimit;

    public Budget(String description, double spendingLimit, int month) {
        super(spendingLimit, month);
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getAmount() {
        return spendingLimit;
    }

    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }

    public String toString() {
        return (this.description + " $" + this.spendingLimit + " Month: " + this.month);
    }

    @Override
    public String getType() {
        return "Budget";
    }
}
