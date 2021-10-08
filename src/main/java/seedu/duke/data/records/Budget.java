package seedu.duke.data.records;

import java.time.LocalDate;

public class Budget extends Record {
    public String description;
    public int month;


    public Budget(double amount, int month) {
        super(amount, month);
//      this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    public void printBudgetDetails() {
        System.out.println(this.description + " $" + this.amount + " Month: " + this.month);
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
        return (this.description + " $" + this.amount + " Month: " + this.month);
    }

    @Override
    public String getType() {
        return "Budget";
    }
}
