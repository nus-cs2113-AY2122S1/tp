package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.DuplicateTripException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.Vertex;

import java.util.List;

public class NewCommand extends Command {
    private final String tripName;
    private String startCountry;
    private String endCountry;
    private List<Vertex> path;
    private String distance;



<<<<<<< HEAD
    public NewCommand(String tripName, String startCountry, String endCountry) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        Trip trip = new Trip(this.tripName, this.startCountry, this.endCountry);
=======
    public NewCommand(String tripName, String startCountry, String endCountry, List<Vertex> path) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.path = path;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        for (int i = 0; i < TripsList.getSize(); i++) {
            if (TripsList.getTrip(i).getTripName().equals(tripName)) {
                throw new DuplicateTripException(tripName);
            }
        }
        Trip trip = new Trip(this.tripName, this.startCountry, this.endCountry, this.path);
>>>>>>> 5b8b9f08baa07ae4f375579985de11036ae5a1e8
        tripsList.addTrip(trip);
        ui.printNewTripCreated(tripName);
    }
}