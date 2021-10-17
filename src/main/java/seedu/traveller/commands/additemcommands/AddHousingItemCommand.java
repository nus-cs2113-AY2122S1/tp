package seedu.traveller.commands.additemcommands;

import seedu.traveller.items.Housing;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AddHousingItemCommand extends AddItemCommand {
    private static final Logger logger = Logger.getLogger(AddHousingItemCommand.class.getName());

    public AddHousingItemCommand(String tripName, int dayIndex, String itemName, String details) {
        super(tripName, dayIndex, itemName, details);
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created a addHousingItem command: \n" + this);
    }

    /**
     * Creates a <code>Housing</code> item that will be added to the <code>ItemsList</code>
     * by the <code>execute</code> method.
     */
    protected void createItem() {
        item = new Housing(getItemName(), getDetails());
    }
}
