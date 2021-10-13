package seedu.duke.data.item;

import java.util.ArrayList;

/**
 * The Catalogue class stores the ArrayList of the items.
 */
public class Catalogue {
    private ArrayList<Item> itemsArrayList = new ArrayList<>();

    /**
     * Adds an item to the ArrayList of items.
     *
     * @param newItem Item to be added
     */
    public void add(Item newItem) {
        itemsArrayList.add(newItem);
    }
}
