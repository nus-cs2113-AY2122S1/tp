package seedu.traveller.items;

import seedu.traveller.TripsList;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Parent abstract class for all items,
 * Implements basic getters for all items.
 */
public abstract class Item {
    private static final Logger logger = Logger.getLogger(Item.class.getName());
    protected String itemName;
    protected String itemType;
    protected  String details;

    public Item(String itemName, String details) {
        logger.setLevel(Level.INFO);
        this.itemName = itemName;
        this.details = details;
        logger.log(Level.INFO, "Item created with details: \n" + this);
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getDetails() {
        return details;
    }
}
