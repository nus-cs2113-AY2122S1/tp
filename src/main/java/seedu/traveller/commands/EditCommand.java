package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.DuplicateTripException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.mapper.Vertex;

import java.util.List;

public class EditCommand extends Command {
    private int tripIndex = -1;
    private final String tripName;
    private String startCountry;
    private String endCountry;
    private List<Vertex> path;

    public EditCommand(String tripName, String startCountry, String endCountry, List<Vertex> path) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.path = path;
    }

    @Override
    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        for (int i = 0; i < TripsList.getSize(); i++) {
            Trip trip = TripsList.getTrip(i);
            if (tripName.equals(trip.getTripName())) {
                tripIndex = i;
                break;
            }
        }

        if (tripIndex == -1) {
            System.out.println("This trip does not exist.");
            return;
        }
        TripsList.deleteTrip(tripIndex);
        Trip trip = new Trip(this.tripName, this.startCountry, this.endCountry, this.path);
        tripsList.addTrip(trip);
        ui.printEdit(tripName);
    }
}