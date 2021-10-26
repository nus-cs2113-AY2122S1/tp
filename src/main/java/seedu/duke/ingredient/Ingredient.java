package seedu.duke.ingredient;

import java.time.LocalDate;

public class Ingredient {

    private String name;
    private String quantity;
    private String price;
    private LocalDate expiry;

    public Ingredient(String name, String quantity, String price, LocalDate expiry) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiry = expiry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    @Override
    public String toString() {
        return name + " [" + quantity + "]" + " [$" + price + "]" + " [" + expiry + "]";
    }
}
