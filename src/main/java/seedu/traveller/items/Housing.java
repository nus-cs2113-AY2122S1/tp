package seedu.traveller.items;

public class Housing extends Item{
    public Housing(String itemName, String details) {
        super(itemName, details);
        this.itemType = "H";
    }

    @Override
    public String toString() {
        return "Staying at " + itemName + "\t\tDetails: " + details;
    }
}
