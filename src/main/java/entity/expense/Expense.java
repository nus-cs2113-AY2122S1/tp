package entity.expense;

public class Expense {
    private String description;
    private double value;
    private String date;

    public Expense(String description, double value, String date) {
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

    @Override
    public String toString() {
        return String.format("%-25s | %-10.2f | %s", description, value, date);
    }
}
