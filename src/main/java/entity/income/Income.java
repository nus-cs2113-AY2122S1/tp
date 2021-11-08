package entity.income;

public class Income {
    private final String description;
    private double value;
    private final String date;

    public Income(String description, double value, String date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public double getValue() {
        return this.value;
    }

    public void updateValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("%-25s | %-10.2f | %-8s", description, value, date);
    }
}
