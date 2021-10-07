package seedu.duke.data.records;

import java.util.ArrayList;

public class Budget extends Record {
    public String description;
    public double spendingLimit;
    public int month;

    public Budget(String description, double spendingLimit, int month) {
        super(spendingLimit, month);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public double getSpendingLimit() {
        return spendingLimit;
    }

    public int getMonth() {
        return month;
    }

    public void printBudgetDetails() {
        System.out.println(this.description + " $" + this.spendingLimit + " Month: " + this.month);
    }
}
