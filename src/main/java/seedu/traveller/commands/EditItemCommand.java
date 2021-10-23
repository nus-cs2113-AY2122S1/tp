
package seedu.traveller.commands;

import seedu.traveller.Item;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class EditItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDayCommand.class.getName());
    private final String tripName;
    private final int itemIndex;
    private final String itemName;
    private final String itemTime;

    public EditItemCommand(String tripName, int itemIndex, String itemTime, String itemName) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.itemTime = itemTime;
        this.itemName = itemName;
        this.itemIndex = itemIndex;
        logger.log(Level.INFO, "Created an edit-item command: \n" + this);
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemTime() {
        return itemTime;
    }

    @Override
    public String toString() {
        return "Edit-item command:"
                + "\n\titemname: " + itemIndex
                + "\n\titemTime: " + getItemTime()
                + "\n\titemName: " + getItemName();
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(tripName);
        assert tripIndex < tripsList.getSize() : "The trip index is out of bound.";
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        ui.printEditItem(tripName, itemIndex, itemName, itemTime);
    }
}

