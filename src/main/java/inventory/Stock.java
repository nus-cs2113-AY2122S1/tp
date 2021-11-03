package inventory;

import utilities.parser.DateParser;

import java.util.Date;

/**
 * Represents a Medicine object. A Medicine object is represented by stock_id, name, price, quantity, expiry,
 * description and max quantity.
 */
public class Stock extends Medicine {
    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String PRICE = "PRICE";
    public static final String QUANTITY = "QUANTITY";
    public static final String EXPIRY_DATE = "EXPIRY_DATE";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String MAX_QUANTITY = "MAX_QUANTITY";

    // Used for sorting
    public static final String ID_LOWERCASE = "id";
    public static final String NAME_LOWERCASE = "name";
    public static final String PRICE_LOWERCASE = "price";
    public static final String QUANTITY_LOWERCASE = "quantity";
    public static final String EXPIRY_DATE_LOWERCASE = "expiry";
    public static final String DESCRIPTION_LOWERCASE = "description";
    public static final String MAX_QUANTITY_LOWERCASE = "max_quantity";

    public static final String[] COLUMNS = {ID, NAME, PRICE, QUANTITY, EXPIRY_DATE, DESCRIPTION, MAX_QUANTITY};

    private static int stockCount = 0;
    protected int stockId;
    protected double price;
    protected Date expiry;
    protected String description;
    protected int maxQuantity;
    protected boolean isDeleted = false;

    public Stock(String name, double price, int quantity, Date expiry, String description, int maxQuantity) {
        super(name, quantity);
        stockCount++;
        this.stockId = stockCount;
        this.price = price;
        this.expiry = expiry;
        this.description = description;
        this.maxQuantity = maxQuantity;
    }

    public static int getStockCount() {
        return stockCount;
    }

    public static void setStockCount(int stockCount) {
        Stock.stockCount = stockCount;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String toFileFormat() {
        String fileFormat = getStockId() + "|" + getMedicineName() + "|" + getPrice() + "|" + getQuantity() + "|"
                + DateParser.dateToString(getExpiry()) + "|" + getDescription() + "|" + getMaxQuantity() + "|"
                + isDeleted();
        return fileFormat;
    }

    // Dummy method since Stock does not use archive.
    public String toArchiveFormat() {
        String archiveFormat = "";
        return archiveFormat;
    }
}
