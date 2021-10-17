package seedu.traveller.commands.additemcommands;

import seedu.traveller.Day;
import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.commands.Command;
import seedu.traveller.commands.NewCommand;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.items.Item;

import java.util.logging.Level;
import java.util.logging.Logger;


public abstract class AddItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(NewCommand.class.getName());
    private final String tripName;
    private final int dayIndex;
    private final String itemName;
    private final String details;
    protected Item item;

    protected abstract void createItem();

    public AddItemCommand(String tripName, int dayIndex, String itemName, String details) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.dayIndex = dayIndex;
        this.itemName = itemName;
        this.details = details;
        createItem();
        logger.log(Level.INFO, "Created an addItem command: \n" + this);
    }

    public String getTripName() {
        return this.tripName;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDetails() {
        return details;
    }

    public Item getItem() {
        return item;
    }

    /**
     * Used to pretty print an <code>ItemCommand</code> to the logger.
     * @return <code>String</code> representing the contents of an <code>ItemCommand</code>.
     */
    @Override
    public String toString() {
        return "AddItem command:"
                + "\n\ttripName: " + getTripName()
                + "\n\tdayIndex: " + getDayIndex()
                + "\n\titemType: " + getItem().getItemType()
                + "\n\titemName: " + getItemName()
                + "\n\tdetails: " + getDetails();
    }

    /**
     * Performs the action of adding an <code>Item</code> to a <code>Day</code>
     * in a <code>Trip</code> of the <code>TripsList</code>.
     * @param tripsList The <code>TripsList</code> that the command will be executed on.
     * @param ui The <code>Ui</code> that will be used to print out the outcome of the execution.
     * @throws TravellerException thrown if the <code>Item</code> cannot be added to the
     * <code>ItemsList</code> of a <code>Day</code>.
     */
    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        Trip trip = tripsList.getTrip(getTripName());
        Day day = trip.getDay(getDayIndex());
        day.addItem(getItem());
        ui.printAddItemToDay(getTripName(), getDayIndex(), getItemName());
    }
}
