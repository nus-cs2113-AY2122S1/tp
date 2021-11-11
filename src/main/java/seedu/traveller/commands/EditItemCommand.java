
package seedu.traveller.commands;

import seedu.traveller.exceptions.DuplicateTripException;
import seedu.traveller.exceptions.TripNotFoundException;
import seedu.traveller.objects.Day;
import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.objects.Item;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author jach23
public class EditItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDayCommand.class.getName());
    private final String tripName;
    private final int itemIndex;
    private String itemName;
    private String itemTime;
    private final int dayIndex;

    public EditItemCommand(String tripName, int dayIndex, int itemIndex, String itemTime, String itemName) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.itemTime = itemTime;
        this.itemName = itemName;
        this.itemIndex = itemIndex;
        this.dayIndex = dayIndex;
        logger.log(Level.INFO, "Created an edit-item command: \n" + this);
    }

    public String getTripName() {
        return tripName;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public int getItemIndex() {
        return itemIndex;
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
                + "\n\ttripName: " + getTripName()
                + "\n\tdayIndex: " + getDayIndex()
                + "\n\titemIndex: " + getItemIndex()
                + "\n\titemTime: " + getItemTime()
                + "\n\titemName: " + getItemName();
    }

    private void setNewItemTime(Trip current, String itemTime) throws TravellerException {
        if (itemTime.equals("")) {
            this.itemTime = current.getDay(this.dayIndex).getItem(this.itemIndex).getItemTime();
        } else {
            this.itemTime = itemTime;
        }
    }

    private void setNewItemName(Trip current, String itemName) throws TravellerException {
        if (itemName.equals("")) {
            this.itemName = current.getDay(this.dayIndex).getItem(this.itemIndex).getItemName();
        } else {
            this.itemName = itemName;
        }
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(this.tripName);
        Trip trip = tripsList.getTrip(tripIndex);

        Day day = trip.getDay(getDayIndex());
        day.getItem(itemIndex);

        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }
        assert tripIndex < tripsList.getSize()
                && tripIndex > -1 : "The trip index is out of bound.";

        setAll(trip, this.itemTime, this.itemName);
        assert !this.itemTime.equals("") && !this.itemName.equals("");

        Item newItem = new Item(getItemTime(), getItemName());
        assert Objects.equals(newItem.getItemTime(), getItemTime()) :
                "Item time in created item and command do not match.";
        assert Objects.equals(newItem.getItemName(), getItemName()) :
                "Item name in created item and command do not match.";
        day.editItem(itemIndex, newItem);

        ui.printEditItem(tripName, dayIndex, itemName, itemTime, itemIndex);
    }

    private void setAll(Trip trip, String itemTime, String itemName) throws TravellerException {
        setNewItemTime(trip, itemTime);
        setNewItemName(trip, itemName);
    }
}

