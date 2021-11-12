package seedu.data.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class Item {
    private final String itemName;
    private final LocalDate expiryDate;
    private int quantity;

    //@@author uosjapuelks
    /**
     * Comparator that compares Strings of description of items.
     */
    public static Comparator<Item> ItemNameComparator = new Comparator<Item>() {
        @Override
        public int compare(Item i1, Item i2) {

            String item1 = i1.getItemName().toLowerCase();
            String item2 = i2.getItemName().toLowerCase();

            //ascending order
            return item1.compareTo(item2);
        }
    };

    /**
     * Comparator that compares LocalDates of the items.
     */
    public static Comparator<Item> ItemExpiryComparator = new Comparator<Item>() {
        @Override
        public int compare(Item i1, Item i2) {

            LocalDate itemExpiry1 = i1.getExpiryDate();
            LocalDate itemExpiry2 = i2.getExpiryDate();

            //Ascending order
            return itemExpiry1.compareTo(itemExpiry2);
        }
    };
    //@@author

    //@@author BryanElmer
    /**
     * Constructor for Item.
     *
     * @param itemName   name of the item.
     * @param expiryDate date of expiry for item.
     */
    public Item(String itemName, LocalDate expiryDate) {
        this.itemName = itemName;
        this.expiryDate = expiryDate;
        this.quantity = 1;
    }

    //@@author alvynben
    /**
     * Overloads constructor to start quantity from more than 1.
     *
     * @param itemName   name of the item.
     * @param expiryDate date of expiry for item.
     * @param quantity   quantity to start from.
     */
    public Item(String itemName, LocalDate expiryDate, int quantity) {
        this.itemName = itemName;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    /**
     * Overloads constructor to ignore expiry date and start quantity from more than 1.
     *
     * @param itemName name of the item.
     * @param quantity quantity to start from.
     */
    public Item(String itemName, int quantity) {
        this.itemName = itemName;
        this.expiryDate = null;
        this.quantity = quantity;
    }

    //@@author alvynben
    /**
     * Adds a specified integer value to quantity of item.
     *
     * @param qty Amount of items to be added.
     */
    public void addQuantity(int qty) {
        long finalQty = quantity + qty;

        if (finalQty > Integer.MAX_VALUE) {
            return;
        }

        quantity = (int) finalQty;
    }

    //@@author BryanElmer
    /**
     * Subtracts a specified integer value to quantity of item.
     *
     * @param qty Amount of items to be subtracted.
     */
    public void removeQuantity(int qty) {
        quantity -= qty;
    }

    /**
     * Gets the Item's quantity.
     *
     * @return Item's quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    //@@author uosjapuelks
    public void setQuantity(int toQuantity) {
        this.quantity = toQuantity;
    }

    //@@author alvynben
    /**
     * String is represented as name + quantity + Expiry date that is colored depending on expiry status.
     *
     * @return Item name, quantity and Expiry that is colored.
     */
    @Override
    public String toString() {
        String expiryInString = expiryToString();
        return itemName + " | Qty: " + quantity + " | " + expiryInString;
    }
    //@@author

    //@@author BryanElmer
    /**
     * String is represented as name + quantity.
     *
     * @return Item name and quantity.
     */
    public String toShopFormat() {
        return itemName + " | Qty: " + quantity;
    }

    /**
     * String is represented as name + updated quantity.
     *
     * @param qtyBeforeAdding Quantity of item before adding.
     * @return Item name and updated quantity.
     */
    public String toAddExistingShopFormat(int qtyBeforeAdding) {
        return itemName + " | Qty: " + qtyBeforeAdding + "->" + (quantity + qtyBeforeAdding);
    }

    /**
     * String used when adding items already existing in the list.
     *
     * @param finalQty Total quantity of item in the list.
     * @param originalQty Original quantity of item in the list.
     * @return Item Name, change in quantity and Expiry that is colored.
     */
    public String addExistingToString(int finalQty, int originalQty) {
        assert finalQty > 1 : "Unable to perform this operation as quantity is less than or equal to 1";
        return itemName + " | Qty: " + (originalQty) + "->" + finalQty + " | " + expiryToString();
    }

    //@@author alvynben
    /**
     * Gets the Item's name.
     *
     * @return Item's name.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the Item's Expiry date.
     *
     * @return Item's Expiry date.
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    //@@author uosjapuelks
    /**
     * Gets the coloured expiry date.
     *
     * @return String containing coloured expiry date.
     */
    public String expiryToString() {
        assert expiryDate != null;
        String expiry = expiryDate.format((DateTimeFormatter.ofPattern("dd MMM yyyy")));
        return expiry;
    }

    //@@author
    /**
     * Check if Item nearing expiry by 7 DAYS.
     *
     * @return true if item is nearing expiry.
     */
    public boolean isNearExpiry() {
        LocalDate today = LocalDate.now();
        assert expiryDate != null;
        long daysRemaining = today.until(expiryDate, ChronoUnit.DAYS);
        return (daysRemaining < 7);
    }

    //@@author zonglun99
    /**
     * Check if Item is expired.
     *
     * @return true if item is expired
     */
    public boolean isExpired() {
        LocalDate today = LocalDate.now();
        assert expiryDate != null;
        long daysRemaining = today.until(expiryDate, ChronoUnit.DAYS);
        return (daysRemaining < 0);
    }

    //@@author uosjapuelks
    /**
     * Check if Item has already expired.
     *
     * @return true if item has expired.
     */
    public boolean isPastExpiry() {
        LocalDate today = LocalDate.now();
        assert expiryDate != null;
        return today.isAfter(expiryDate);
    }

    /**
     * Return format for saving into storage file.
     *
     * @return String with format for saving.
     */
    public String saveFormat() {
        return itemName + " | Qty: " + quantity + " | " + expiryDate;
    }

    //@@author alvynben
    /**
     * Return format for adding item.
     *
     * @return String with format for adding.
     */
    public String toAddFormat() {
        return itemName + " | " + expiryDate;
    }

    //@@author alvynben
    /**
     * Check if the items compared have the same name.
     *
     * @param item item in itemList.
     * @return true if the item names are the same.
     */
    public boolean hasSameNameAs(Item item) {
        return itemName.equalsIgnoreCase(item.getItemName());
    }

    //@@author alvynben
    /**
     * Check if the items have the same expiry.
     *
     * @param item item in itemList.
     * @return true if the item expiry is the same.
     */
    public boolean hasSameExpiryAs(Item item) {
        return expiryDate.isEqual(item.getExpiryDate());
    }
}
