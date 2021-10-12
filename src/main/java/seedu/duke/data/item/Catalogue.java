package seedu.duke.data.item;

import java.util.ArrayList;

/**
 * The Catalogue class stores the ArrayList of the items.
 */
public class Catalogue {
    ArrayList<Item> itemsArrayList = new ArrayList<>();

    public Item removeItem(String ID) {
        Item selected = null;
        for (Item current : itemsArrayList) {
            if (current.getID().equals(ID)) {
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
