package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AddTripDayCommand extends Command{
    private static final Logger logger = Logger.getLogger(NewCommand.class.getName());
    private final String tripName;

    public AddTripDayCommand(String tripName) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        logger.log(Level.INFO, "Created an addTripDay command: \n" + this);
    }

    public String getTripName() {
        return this.tripName;
    }

    @Override
    public String toString() {
        return "AddTripDay command:"
                + "\n\ttripName: " + tripName;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        Trip trip = tripsList.getTrip(tripName);
        trip.addDay();
        ui.printAddDayToTrip(tripName);
    }
}
