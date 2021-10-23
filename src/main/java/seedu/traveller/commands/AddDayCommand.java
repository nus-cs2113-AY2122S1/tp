package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AddDayCommand extends Command {
    private static final Logger logger = Logger.getLogger(NewCommand.class.getName());
    private final String tripName;
    private final int daysNumber;

    public AddDayCommand(String tripName, int daysNumber) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.daysNumber = daysNumber;
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
        for (int i = 0; i < daysNumber; i++) {
            trip.addDay();
        }
        ui.printAddDayToTrip(tripName, daysNumber);
    }
}
