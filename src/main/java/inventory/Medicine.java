package inventory;

import java.util.Date;

/**
 * Represents a Medicine object. A Medicine object is represented by stock_id, name, price, quantity, expiry,
 * description and max quantity.
 */

public class Medicine extends Stock {
    public static final String[] COLUMNS = {"ID", "NAME", "PRICE", "QUANTITY", "EXPIRY", "DESCRIPTION", "MAX_QUANTITY"};
    public static final int NO_OF_COLUMNS = 7;

    private static int stockCount = 0;
    protected int stockID;
    protected double price;
    protected Date expiry;
    protected String description;
    protected int maxQuantity;

    public Medicine(String name, int quantity, double price, Date expiry, String description, int maxQuantity) {
        super(name, quantity);
        stockCount++;
        this.stockID = stockCount;
        this.price = price;
        this.expiry = expiry;
        this.description = description;
        this.maxQuantity = maxQuantity;
    }

    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(int maxQuantity) {
        this.maxQuantity = maxQuantity;
    }
}
