package seedu.traveller.objects;

import seedu.traveller.exceptions.ItemNotFoundException;
import seedu.traveller.exceptions.TravellerException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a list of items that will be done in a particular day of a trip.
 * Each <code>Day</code> will have 1 <code>ItemsList</code>.
 */
public class ItemsList {
    private static final Logger logger = Logger.getLogger(TripsList.class.getName());
    private final ArrayList<Item> items;

    public ItemsList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a items list");
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        logger.log(Level.INFO, "Added an item to itemsList");
        items.add(item);
    }

    public void editItem(int index, Item item) {
        logger.log(Level.INFO, "Editing an item to itemsList");
        items.add(item);
        Collections.swap(items, index, getSize() - 1);
        items.remove(getSize() - 1);
    }

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

    public Item getItem(int itemNumber) throws TravellerException {
        Item item;
        try {
            item = items.get(itemNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException(itemNumber);
        }
        return item;
    }

    public void deleteItem(int itemNumber) throws TravellerException {
        logger.log(Level.INFO, "Deleted an item of index " + itemNumber + " from itemsList");
        try {
            items.remove(itemNumber);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException(itemNumber);
        }
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
            String itemEntry = "\n\t\t\t\t\t" + i + ":\t";
            itemsListString.append(itemEntry);
            Item item = items.get(i);
            itemsListString.append(item.toString());
        }
        return itemsListString.toString();
    }

    public String getItemInfo(int itemIndex) throws TravellerException {
        Item current = getItem(itemIndex);
        return " /time " + current.getItemTime() + " /name " + current.getItemName() + "\n";
    }
}
