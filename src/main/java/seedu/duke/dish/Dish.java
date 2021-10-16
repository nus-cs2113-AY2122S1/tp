package seedu.duke.dish;

public class Dish {

    private String name;
    private double price;
    private double discount;

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
        this.discount = 100;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscountedPrice() {
        double discountMultiplier = getDiscount() / 100;
        return getPrice() * discountMultiplier;
    }

    public String getDiscountedPriceString() {
        return "$" + getDiscountedPrice();
    }

    @Override
    public String toString() {
            return name + " - $" + getPrice();
    }

}
