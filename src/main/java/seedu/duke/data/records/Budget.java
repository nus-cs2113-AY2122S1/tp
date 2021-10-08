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

    public void printBudgetDetails() {
        System.out.println(this.description + " $" + this.spendingLimit + " Month: " + this.month);
    }
    @Override
    public String getType(){
        return "Budget";
    }
}
