package entity;

public class Income {
    private String description;
    private double value;
    private String date;

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

    @Override
    public String toString() {
        return (description + "\t\t\t| " + value + "\t| " + date);
    }
}
