package seedu.traveller.objects;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class for all items in an ItemsList.
 */
public class Item {
    private static final Logger logger = Logger.getLogger(Item.class.getName());
    protected String itemName;
    protected  String itemTime;

    public Item(String itemTime, String itemName) {
        logger.setLevel(Level.INFO);
        this.itemTime = itemTime;
        this.itemName = itemName;
        logger.log(Level.INFO, "Item created with details: \n" + this);
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemTime() {
        return itemTime;
    }

    /**
     * Used to pretty print when the <code>ViewCommand</code> is called.
     * @return <code>String</code> of the contents of the <code>Item</code> object.
     */
    @Override
    public String toString() {
        return getItemTime() + "  " + getItemName();
    }
}