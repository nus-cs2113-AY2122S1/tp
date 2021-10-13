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
     * Searches for an item by the item ID.
     * @param id Identifier of item to match
     * @return Referenced item whose id matches the given input
     * @throws NullPointerException If id provided does not reference any item
     */
    public Item getItem(String id) throws NullPointerException {
        Item selected = null;
        for (Item current : itemsArrayList) {
            if (current.getID().equals(id)) {
                selected = current;
                break;
            }
        }
        if (selected == null) {
            throw new NullPointerException();
        }
        return selected;
    }

    /**
     * Adds an item to the ArrayList of items.
     *
     * @param newItem Item to be added
     */
    public void add(Item newItem) {
        itemsArrayList.add(newItem);
    }

    /**
     * Searches for referenced item and removes it from ArrayList of items.
     * @param id Identifier of item to be removed
     * @return Referenced item that has been removed
     * @throws NullPointerException If id provided does not reference any item
     */
    public Item removeItem(String id) throws NullPointerException {
        Item selected = null;
        for (Item current : itemsArrayList) {
            if (current.getID().equals(id)) {
                selected = current;
                break;
            }
        }
        if (selected == null) {
            throw new NullPointerException();
        } else {
            itemsArrayList.remove(selected);
        }
        return selected;
    }
}
