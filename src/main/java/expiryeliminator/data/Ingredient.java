package expiryeliminator.data;

public class Ingredient {
    private final String name;
    private final int quantity;
    private final String expiryDate;

    public Ingredient(String name, int quantity, String expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public String toString() {
        return name + " (qty: " + quantity + ") (expiry: " + expiryDate + ")";
    }
}
