package seedu.budgettracker.data.records;

import java.text.DecimalFormat;

public class Budget extends Record {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Budget(double amount, int month) {
        super(amount, month);
    }

    public void setAmount(double amount) {
        if (amount < 0.00) {
            System.out.println("Budget cannot be negative!!!!");
            System.out.println("Defaulting the amount to $0.00");
            this.amount = 0.00;
        } else {
            this.amount = amount;
        }
    }

    public void clearAmount() {
        amount = 0.00;
    }

    public String toString() {
        return (" $" + df.format(this.amount));
    }
}
