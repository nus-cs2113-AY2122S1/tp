package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

public class NewCommand extends Command {
    private final String tripName;
    private String startCountry;
    private String endCountry;
    private String distance;



    public NewCommand(String tripName, String startCountry, String endCountry) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        Trip trip = new Trip(this.tripName, this.startCountry, this.endCountry);
        tripsList.addTrip(trip);
        ui.printNewTripCreated(tripName);
    }
}