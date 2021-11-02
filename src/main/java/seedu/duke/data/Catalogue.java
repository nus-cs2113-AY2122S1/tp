package seedu.duke.data;

import seedu.duke.common.LibmgrException;

import java.util.ArrayList;

import static seedu.duke.common.Messages.ADD_DUPLICATE_ID;

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
     * Checks whether ID of new item is a duplicate of an existing item.
     *
     * @param newItem Item to be added
     * @throws LibmgrException when item ID is duplicate
     */
    public void checkDuplicateID(Item newItem) throws LibmgrException {
        boolean isDuplicate = false;
        for (Item current : itemsArrayList) {
            if (newItem.getID().equals(current.getID())) {
                isDuplicate = true;
                break;
            }
        }
        if (isDuplicate) {
            throw new LibmgrException(ADD_DUPLICATE_ID);
        }
    }

    /**
     * Checks whether ID is duplicate, if not adds item to ArrayList.
     * @param newItem Item to be added
     * @throws LibmgrException when item ID is a duplicate
     */
    public void add(Item newItem) throws LibmgrException {
        checkDuplicateID(newItem);
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
