package seedu.budgettracker.data.records;

/**
 * Superclass of budget. Each record instance has a month and amount
 * attribute which allows it to be placed into the corresponding MonthList.
 */
public abstract class Record {
    protected double amount;
    protected int month;

    public Record(double amount, int month) {
        if (amount < 0.00) {
            this.amount = 0.00;
        } else {
            this.amount = amount;
        }

        this.month = month;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0.00) {
            this.amount = 0.00;
        } else {
            this.amount = amount;
        }
    }

    public int getMonth() {
        return this.month;
    }
}
