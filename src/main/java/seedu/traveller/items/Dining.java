package seedu.traveller.items;


public class Dining extends Item {
    public Dining(String itemName, String details) {
        super(itemName, details);
        this.itemType = "D";
    }

    /**
     * Used to pretty print when the <code>ViewAllCommand</code> is called.
     * @return <code>String</code> of the contents of the <code>Dining</code> object.
     */
    @Override
    public String toString() {
        return "Dining at " + getItemName() + "\t\tDetails: " + getDetails();
    }
}
