package seedu.traveller.items;

public abstract class Item {
    protected String itemName;
    protected String itemType;
    protected  String details;

    public Item(String itemName, String details) {
        this.itemName = itemName;
        this.details = details;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }
}
