package seedu.traveller.commands;

import seedu.traveller.objects.Day;
import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;
//@@author Cuiminjing

public class DeleteItemCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDayCommand.class.getName());
    private final String tripName;
    private final int dayIndex;
    private final int itemIndex;

    public DeleteItemCommand(String tripName, int dayIndex, int itemIndex) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.itemIndex = itemIndex;
        this.dayIndex = dayIndex;
        logger.log(Level.INFO, "Created a delete-item command: \n" + this);
    }


    @Override
    public String toString() {
        return "Delete-item command:"
                + "\n\ttripName: " + tripName + "\n\tdayIndex: " + dayIndex + "\n\titemname: " + itemIndex;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(tripName);
        assert tripIndex < tripsList.getSize() : "The trip index is out of bound.";
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        Trip trip = tripsList.getTrip(tripName);
        Day day = trip.getDay(dayIndex);
        day.deleteItem(itemIndex);
        ui.printDeleteItem(tripName,dayIndex,itemIndex);
    }
}
