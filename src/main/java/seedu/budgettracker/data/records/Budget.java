package seedu.budgettracker.data.records;

public class Budget extends Record {

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
        return (" $" + this.amount);
    }

    public Double getRawValue() {
        return this.amount;
    }
}
