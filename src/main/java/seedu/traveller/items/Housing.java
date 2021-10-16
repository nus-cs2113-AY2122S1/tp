package seedu.traveller.items;


public class Housing extends Item {
    public Housing(String itemName, String details) {
        super(itemName, details);
        this.itemType = "H";
    }

    /**
     * Used to pretty print when the <code>ViewAllCommand</code> is called.
     * @return <code>String</code> of the contents of the <code>Housing</code> object.
     */
    @Override
    public String toString() {
        return "Staying at " + getItemName() + "\t\tDetails: " + getDetails();
    }
}
