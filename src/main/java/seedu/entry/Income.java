package seedu.entry;

public class Income extends Entry {

    public Income(String description, double value) {
        this.description = description;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[I] " + description + " - $" + value;
    }
}
