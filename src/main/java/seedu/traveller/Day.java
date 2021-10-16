package seedu.traveller;

import seedu.traveller.items.Item;


public class Day {
    protected ItemsList itemsList;

    public Day() {
        itemsList = new ItemsList();
    }

    public void addItem(Item item) {
        itemsList.addItem(item);
    }

    public Item getItem(int i) {
        return itemsList.getItem(i);
    }

    public void deleteItem(int i) {
        itemsList.deleteItem(i);
    }

    @Override
    public String toString() {
        return itemsList.toString();
    }
}
