//@@author jerrelllzw

package seedu.duke.dish;

/**
 * Represents a user's dish. Contains the name, price and discount of the dish.
 */
public class Dish {

    private String name;
    private double price;
    private double discount;

    /**
     * Constructs an instance of Dish.
     *
     * @param name Name of the dish.
     * @param price Price of the dish.
     */
    public Dish(String name, double price) {
        double roundedPrice = Math.round(price * 100.0) / 100.0;
        this.name = name;
        this.price = roundedPrice;
        this.discount = 0;
    }

    /**
     * Gets the name of the dish.
     *
     * @return Name of the dish in String format.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the price of the dish.
     *
     * @return Price of the dish in Double format.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Sets the price of the dish.
     *
     * @param price New price of the dish.
     */
    public void setPrice(double price) {
        double roundedPrice = Math.round(price * 100.0) / 100.0;
        this.price = roundedPrice;
    }

    /**
     * Gets the discount of the dish.
     *
     * @return Discount of the dish in Double format.
     */
    public double getDiscount() {
        return this.discount;
    }

    /**
     * Sets the discount of the dish.
     *
     * @param discount New discount of the dish.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Gets the discounted price of the dish.
     *
     * @return Discounted price of the dish in Double format.
     */
    public double getDiscountedPrice() {
        double discountMultiplier = 1 - getDiscount() / 100;
        double discountedPrice = getPrice() * discountMultiplier;
        double roundedDiscountedPrice = Math.round(discountedPrice * 100.0) / 100.0;
        return roundedDiscountedPrice;
    }

    /**
     * Gets the discounted price of the dish.
     *
     * @return Discounted price of the dish in String format.
     */
    public String getDiscountedPriceString() {
        return "$" + getDiscountedPrice();
    }

    @Override
    public String toString() {
        return name + " - $" + getPrice();
    }

}
