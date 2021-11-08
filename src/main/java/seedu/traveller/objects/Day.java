package seedu.traveller.objects;

import seedu.traveller.exceptions.TravellerException;

import java.util.ArrayList;

//@@author conradwee
/**
 * Class for all days in an DaysList.
 */
public class Day {
    protected ItemsList itemsList;

    public Day() {
        itemsList = new ItemsList();
    }

    public void addItem(Item item) throws TravellerException {
        itemsList.addItem(item);
    }

    public Item getItem(int itemNumber) throws TravellerException {
        return itemsList.getItem(itemNumber);
    }

    public void deleteItem(int itemNumber) throws TravellerException {
        itemsList.deleteItem(itemNumber);
    }

    public int getItemsListSize() {
        return itemsList.getSize();
    }

    @Override
    public String toString() {
        return itemsList.toString();
    }

    public void editItem(int index, Item item) {
        itemsList.editItem(index, item);
    }

    public ArrayList<Item> searchItem(String itemKey) {
        return itemsList.searchItem(itemKey);
    }
}