package seedu.entry;

public class Expense extends Entry {
    private static int size = 0;

    public Expense(String description, double value) {
        this.description = description;
        this.value = value;
        size++;
    }

    public static int getSize() {
        return size;
    }

    public static void decrementSize() {
        size--;
    }

    @Override
    public String toString() {
        String valueTwoDecimalPoint = String.format("%.2f",value);
        return "[E] " + description + " - $" + valueTwoDecimalPoint;
    }
}
