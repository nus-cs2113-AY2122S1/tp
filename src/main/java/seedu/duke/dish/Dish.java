//@@author jerrelllzw

package seedu.duke.dish;

public class Dish {

    private String name;
    private double price;
    private double discount;

    public Dish(String name, double price) {
        double roundedPrice = Math.round(price * 100.0) / 100.0;
        this.name = name;
        this.price = roundedPrice;
        this.discount = 0;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        double roundedPrice = Math.round(price * 100.0) / 100.0;
        this.price = roundedPrice;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscountedPrice() {
        double discountMultiplier = 1 - getDiscount() / 100;
        double discountedPrice = getPrice() * discountMultiplier;
        double roundedDiscountedPrice = Math.round(discountedPrice * 100.0) / 100.0;
        return roundedDiscountedPrice;
    }

    public String getDiscountedPriceString() {
        return "$" + getDiscountedPrice();
    }

    @Override
    public String toString() {
        return name + " - $" + getPrice();
    }

}
