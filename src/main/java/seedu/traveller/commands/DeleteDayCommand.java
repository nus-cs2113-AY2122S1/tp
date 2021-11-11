package seedu.traveller.commands;

import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;
//@@author Cuiminjing

public class DeleteDayCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteDayCommand.class.getName());
    private final String tripName;
    private final int index;

    public DeleteDayCommand(String tripName, int index) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.index = index;
        logger.log(Level.INFO, "Created a delete-day command: \n" + this);
    }


    @Override
    public String toString() {
        return "Delete-day command:"
                + "\n\ttripName: " + tripName + "\n\tindex: " + index;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(tripName);
        assert tripIndex < tripsList.getSize() : "The trip index is out of bound.";
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        Trip trip = tripsList.getTrip(tripName);
        trip.deleteDay(index);
        ui.printDeleteDay(tripName,index);
    }
}
