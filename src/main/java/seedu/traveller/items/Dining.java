package seedu.traveller.items;

public class Dining extends Item {
    public Dining(String itemName, String details) {
        super(itemName, details);
        this.itemType = "D";
    }

    @Override
    public String toString() {
        return "Dining at " + itemName + "\t\tDetails: " + details;
    }
}
