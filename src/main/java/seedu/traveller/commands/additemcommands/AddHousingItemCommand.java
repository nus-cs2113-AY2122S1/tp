package seedu.traveller.commands.additemcommands;

import seedu.traveller.items.Housing;


public class AddHousingItemCommand extends AddItemCommand {
    public AddHousingItemCommand(String tripName, int dayIndex, String itemName, String details) {
        super(tripName, dayIndex, itemName, details);
    }

    protected void createItem() {
        item = new Housing(getItemName(), getDetails());
    }
}
