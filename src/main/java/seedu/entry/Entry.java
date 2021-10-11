package seedu.entry;

public abstract class Entry {
    protected String description;
    protected double value;

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String toString() {
        return description + " - " + Double.toString(value);
    }
}
