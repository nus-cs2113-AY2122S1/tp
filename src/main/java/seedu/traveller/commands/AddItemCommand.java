package seedu.traveller.commands;

import seedu.traveller.exceptions.DuplicateItemException;
import seedu.traveller.objects.Day;
import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.objects.Item;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


//@@author Uxinnn
public class AddItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(NewCommand.class.getName());
    private final String tripName;
    private final int dayIndex;
    private final String itemName;
    private final String itemTime;

    public AddItemCommand(String tripName, int dayIndex, String itemTime, String itemName) {
        logger.setLevel(Level.INFO);
        assert !tripName.equals("all") : "'all' is an invalid tripName.";
        assert !tripName.equals("") : "'' is an invalid tripName.";
        this.tripName = tripName;
        assert dayIndex >= 0 : "Day index is negative.";
        this.dayIndex = dayIndex;
        this.itemTime = itemTime;
        this.itemName = itemName;
        logger.log(Level.INFO, "Created an addItem command: \n" + this);
    }

    public String getTripName() {
        return tripName;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemTime() {
        return itemTime;
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
                + "\n\titemTime: " + getItemTime()
                + "\n\titemName: " + getItemName();
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
        String tripName = getTripName();
        String itemName = getItemName();
        String itemTime = getItemTime();
        int dayIndex = getDayIndex();
        Trip trip = tripsList.getTrip(tripName);
        Day day = trip.getDay(dayIndex);

        Item newItem = new Item(itemTime, itemName);
        assert Objects.equals(newItem.getItemTime(), itemTime) :
                "Item time in created item and command do not match.";
        assert Objects.equals(newItem.getItemName(), itemName) :
                "Item name in created item and command do not match.";
        day.addItem(newItem);
        ui.printAddItemToDay(tripName, dayIndex);
    }
}