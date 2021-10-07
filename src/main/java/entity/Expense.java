package entity;

public class Expense {
    private String name;
    private double value;
    private String date;

    public Expense(String name, double value, String date) {
        this.name = name;
        this.value = value;
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return (name + "\t| " + value + "\t| " + date);
    }
}
