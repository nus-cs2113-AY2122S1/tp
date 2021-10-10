package seedu.duke.data.records;

import java.time.LocalDate;

public class Budget extends Record {
    // Description not needed
    public String description;
    public int month;


    public Budget(double amount) {
        super(amount);
        //this.description = description;
    }

    /*
    getDescription not needed
     */
    @Override
    public String getDescription() {
        return description;
    }

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
