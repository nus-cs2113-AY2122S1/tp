package seedu.duke;

public class Ingredient {
    protected String description;
    protected double price;

    public Ingredient(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
