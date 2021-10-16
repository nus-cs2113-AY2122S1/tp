package seedu.traveller.commands.additemcommands;

import seedu.traveller.items.Dining;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AddDiningItemCommand extends AddItemCommand {
    private static final Logger logger = Logger.getLogger(AddDiningItemCommand.class.getName());

    public AddDiningItemCommand(String tripName, int dayIndex, String itemName, String details) {
        super(tripName, dayIndex, itemName, details);
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created a addDiningItem command: \n" + this);
    }

    /**
     * Creates a <code>Dining</code> item that will be added to the <code>ItemsList</code>
     * by the <code>execute</code> method.
     */
    protected void createItem() {
        item = new Dining(getItemName(), getDetails());
    }
}
