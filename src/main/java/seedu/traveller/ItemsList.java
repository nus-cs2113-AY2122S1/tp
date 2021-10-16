package seedu.traveller;

import seedu.traveller.items.Item;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ItemsList {
    private static final Logger logger = Logger.getLogger(TripsList.class.getName());
    private final ArrayList<Item> items;

    public ItemsList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a items list");
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItem(int i) {
        return items.get(i);
    }

    public void deleteItem(int i) {
        items.remove(i);
    }

    public int getSize() {
        return items.size();
    }

    @Override
    public String toString() {
        StringBuilder itemsListString = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            String itemEntry = "\n\t\t\t\t\t" + i + ": " + getItem(i).toString();
            itemsListString.append(itemEntry);
        }
        return itemsListString.toString();
    }
}
