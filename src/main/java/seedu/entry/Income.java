package seedu.entry;

public class Income extends Entry {

    public Income(String description, double value) {
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[I] " + description + " - $" + valueTwoDecimalPoint;
    }
}
