package seedu.traveller.objects;

import seedu.traveller.exceptions.DuplicateItemException;
import seedu.traveller.exceptions.ItemNotFoundException;
import seedu.traveller.exceptions.MaxNumberOfItemsAllowedExceededException;
import seedu.traveller.exceptions.TravellerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


//@@author Uxinnn
/**
 * Represents a list of items that will be done in a particular day of a trip.
 * Each <code>Day</code> will have 1 <code>ItemsList</code>.
 */
public class ItemsList {
    private static final Logger logger = Logger.getLogger(TripsList.class.getName());
    private final ArrayList<Item> items;
    private final int maxItemsAllowed = 50;

    public ItemsList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a items list");
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) throws TravellerException {
        if (getSize() >= getMaxItemsAllowed()) {
            throw new MaxNumberOfItemsAllowedExceededException();
        }
        String itemTime = item.getItemTime();
        String itemName = item.getItemName();
        if (isInList(itemTime, itemName)) {
            logger.log(Level.WARNING, "A duplicate item cannot be added.");
            throw new DuplicateItemException(itemTime, itemName);
        }
        logger.log(Level.INFO, "Added an item to itemsList");
        items.add(item);
    }

    //@@author jach23
    public void editItem(int index, Item item) {
        logger.log(Level.INFO, "Editing an item to itemsList");
        items.add(item);
        Collections.swap(items, index, getSize() - 1);
        items.remove(getSize() - 1);
    }

    //@@author jach23
    public ArrayList<Item> searchItem(String keyword) {
        ArrayList<Item> keywordString = new ArrayList<>();
        logger.log(Level.INFO, "Searching for keyword in itemsList");
        for (Item i: items) {
            if (i.getItemName().contains(keyword) || i.getItemTime().contains(keyword)) {
                keywordString.add(i);
            }
        }
        return keywordString;
    }

    //@@author Uxinnn
    public boolean isInList(String itemTime, String itemName) throws TravellerException {
        for (int i = 0; i < getSize(); i++) {
            Item item = getItem(i);
            if (item.getItemTime().equals(itemTime) && item.getItemName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

    public Item getItem(int itemNumber) throws TravellerException {
        Item item;
        try {
            item = items.get(itemNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException(itemNumber);
        }
        return item;
    }

    //@@author Cuiminjing
    public void deleteItem(int itemNumber) throws TravellerException {
        logger.log(Level.INFO, "Deleted an item of index " + itemNumber + " from itemsList");
        try {
            items.remove(itemNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException(itemNumber);
        }
    }

    //@@author
    public int getMaxItemsAllowed() {
        return maxItemsAllowed;
    }

    public int getSize() {
        return items.size();
    }

    /**
     * Used by the <code>Ui</code> to print out an <code>ItemsList</code>.
     * @return <code>String</code> of the <code>ItemsList</code> and its <code>Item</code>s' contents.
     */
    @Override
    public String toString() {
        StringBuilder itemsListString = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            String itemEntry = "\n\t\t\t\t\t" + i + ":  ";
            itemsListString.append(itemEntry);
            Item item = items.get(i);
            itemsListString.append(item.toString());
        }
        return itemsListString.toString();
    }

    //@@author gavienwz
    public String getItemInfo(int itemIndex) throws TravellerException {
        Item current = getItem(itemIndex);
        return " /time " + current.getItemTime() + " /name " + current.getItemName() + "\n";
    }
}