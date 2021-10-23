package seedu.traveller;


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

    public int getItemsListSize() {
        return itemsList.getSize();
    }

    @Override
    public String toString() {
        return itemsList.toString();
    }
}