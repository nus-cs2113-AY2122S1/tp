
package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.objects.Day;
import seedu.traveller.objects.Trip;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.objects.Item;
import seedu.traveller.exceptions.TripNotFoundException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SearchItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDayCommand.class.getName());
    private final String tripName;
    private final String itemKey;
    private final int dayIndex;

    public SearchItemCommand(String tripName, int dayIndex, String itemKey) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.itemKey = itemKey;
        this.dayIndex = dayIndex;

        logger.log(Level.INFO, "Created a search-item command: \n" + this);
    }

    public String getTripName() {
        return tripName;
    }

    public int getDayIndex() {
        return dayIndex;
    }

    public String getItemKey() {
        return itemKey;
    }

    @Override
    public String toString() {
        return "Search-item command:"
                + "\n\ttripName: " + getTripName()
                + "\n\tdayIndex: " + getDayIndex()
                + "\n\titemTime: " + getItemKey();
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(tripName);
        assert tripIndex < tripsList.getSize() : "The trip index is out of bound.";
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        Trip trip = tripsList.getTrip(tripName);
        Day day = trip.getDay(dayIndex);

        ArrayList<Item> keyString;
        keyString = day.searchItem(itemKey);

        ui.printSearchItem(tripName, dayIndex, itemKey, keyString);
    }
}

