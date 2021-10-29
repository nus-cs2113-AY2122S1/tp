package entity.budget;

public class Budget {
    private double value;

    public Budget(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format(" %-10.2f |", value);
    }
}
