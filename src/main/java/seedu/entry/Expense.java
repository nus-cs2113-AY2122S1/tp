package seedu.entry;

public class Expense extends Entry {

    public Expense(String description, double value) {
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[E] " + description + " - $" + valueTwoDecimalPoint;
    }
}
