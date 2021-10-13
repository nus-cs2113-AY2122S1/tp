package seedu.duke.data;

import java.util.ArrayList;

/**
 * The Catalogue class stores the ArrayList of the items.
 */
public class Catalogue {
    private final ArrayList<Item> allItems;

    /**
     * Creates an empty catalogue.
     */
    public Catalogue() {
        allItems = new ArrayList<Item>();
    }

    /**
     * Contructs a catalogue with the given data.
     *
     * @param items external changes to this will not affect this catalogue
     */
    public Catalogue(ArrayList<Item> items) {
        this.allItems = new ArrayList<Item>(items);
    }

    public ArrayList<Item> getAllItems() {
        return new ArrayList<Item>(allItems);
    }
}
