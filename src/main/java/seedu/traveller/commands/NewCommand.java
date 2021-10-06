package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

public class NewCommand extends Command{
    private final String tripName;
    public NewCommand(String tripName) {
        this.tripName = tripName;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        Trip trip = new Trip(this.tripName);
        tripsList.addTrip(trip);
        ui.printNewTripCreated(tripName);
    }
}
