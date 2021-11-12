package seedu.budgettracker.data.records;

import java.text.DecimalFormat;

public class Budget extends Record {

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Budget(double amount, int month) {
        super(amount, month);
    }

    public void clearAmount() {
        amount = 0.00;
    }

    public String toString() {
        return (" $" + df.format(this.amount));
    }
}
