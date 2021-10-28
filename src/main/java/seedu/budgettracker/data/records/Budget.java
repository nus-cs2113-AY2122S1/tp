package seedu.budgettracker.data.records;

public class Budget extends Record {

    public Budget(double amount, int month) {
        super(amount, month);
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void clearAmount() {
        amount = 0.00;
    }

    public String toString() {
        return (" $" + this.amount);
    }

    public Double getRawValue() {
        return this.amount;
    }
}
