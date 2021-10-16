package seedu.traveller.commands.additemcommands;

import seedu.traveller.items.Dining;


public class AddDiningItemCommand extends AddItemCommand {
    public AddDiningItemCommand(String tripName, int dayIndex, String itemName, String details) {
        super(tripName, dayIndex, itemName, details);
    }

    protected void createItem() {
        item = new Dining(getItemName(), getDetails());
    }
}
