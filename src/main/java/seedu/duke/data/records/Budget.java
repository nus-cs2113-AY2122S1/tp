package seedu.duke.data.records;

import java.time.LocalDate;

public class Budget extends Record {
    // Description and month not needed
    // public String description;
    // public int month;


    public Budget(double amount) {
        super(amount);
        //this.description = description;
    }

    /*
    Not used.
    @Override
    public String getDescription() {
        return description;
    }
    */

    @Override
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void clearAmount() {
        amount = 0.00;
    }

    public void printBudgetDetails() {
        System.out.println(" $" + this.amount);
    }

    /*
    Not used/ needed.
    @Override
    public int getMonth() {
        return month;
    }

    @Override
    public LocalDate getDate() {
        return null;
    }
    */

    public String toString() {
        return (" $" + this.amount);
    }

    @Override
    public String getType() {
        return "Budget";
    }
}
