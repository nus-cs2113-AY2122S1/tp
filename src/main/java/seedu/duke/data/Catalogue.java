package seedu.duke.data;

import java.util.ArrayList;

/**
 * The Catalogue class stores the ArrayList of the items.
 */
public class Catalogue {
    private final ArrayList<Item> itemsArrayList;

    /**
     * Creates an empty catalogue.
     */
    public Catalogue() {
        itemsArrayList = new ArrayList<>();
    }

    /**
     * Constructs a catalogue with the given data.
     *
     * @param items external changes to this will not affect this catalogue
     */
    public Catalogue(ArrayList<Item> items) {
        this.itemsArrayList = new ArrayList<>(items);
    }

    public ArrayList<Item> getAllItems() {
        return new ArrayList<>(itemsArrayList);
    }

    /**
     * Adds an item to the ArrayList of items.
     *
     * @param newItem Item to be added
     */
    public void add(Item newItem) {
        itemsArrayList.add(newItem);
    }
}
