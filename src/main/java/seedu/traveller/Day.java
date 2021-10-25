package seedu.traveller;


//@@author Uxinnn
public class Day {
    protected ItemsList itemsList;

    public Day() {
        itemsList = new ItemsList();
    }

    public void addItem(Item item) {
        itemsList.addItem(item);
    }

    public Item getItem(int itemNumber) {
        return itemsList.getItem(itemNumber);
    }

    public void deleteItem(int itemNumber) {
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
}