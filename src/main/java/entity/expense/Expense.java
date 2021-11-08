package entity.expense;

public class Expense {
    private final String description;
    private double value;
    private final String date;
    private String category = "-";

    public Expense(String description, double value, String date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    public Expense(String description, double value, String date, String category) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date;
    }

    public String getCategory() {
        return this.category;
    }

    public double getValue() {
        return this.value;
    }

    public void updateValue(double value) {
        this.value = value;
    }

    public void updateCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("%-25s | %-10.2f | %-8s | %-10s", description, value, date, category);
    }
}
