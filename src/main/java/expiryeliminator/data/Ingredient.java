package expiryeliminator.data;

import java.time.LocalDate;

/**
 * Represents an ingredient.
 */
public class Ingredient {
    private final String name;
    private int quantity;
    private final LocalDate expiryDate;

    /**
     * Initialises an ingredient.
     *
     * @param name The name of the ingredient.
     * @param quantity The quantity of the ingredient.
     * @param expiryDate The expiry date of the ingredient.
     */
    public Ingredient(String name, int quantity, LocalDate expiryDate) {
        assert name != null && !name.isBlank();
        assert quantity >= 0;
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    /**
     * Returns the name of the ingredient.
     *
     * @return The name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity of the ingredient.
     *
     * @return The quantity of the ingredient.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ingredient.
     *
     * @param quantity Quantity of the ingredient.
     */
    public void setQuantity(int quantity) {
        assert quantity >= 0;
        this.quantity = quantity;
    }

    /**
     * Returns the expiry date of the ingredient.
     *
     * @return The expiry date of the ingredient.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return name + " (qty: " + quantity + ") (expiry: " + expiryDate + ")";
    }
}
