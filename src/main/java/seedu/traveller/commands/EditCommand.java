package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;
import seedu.traveller.worldMap.Country;
import seedu.traveller.worldMap.MinCalcResult;
import seedu.traveller.worldMap.WorldMap;

import java.util.List;


public class EditCommand extends Command {
    private final String tripName;
    private final String startCountry;
    private final String endCountry;

    public EditCommand(String tripName, String startCountry, String endCountry) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    @Override
    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(this.tripName);
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        tripsList.deleteTrip(tripIndex);
        MinCalcResult result = WorldMap.calcMinDistance(this.startCountry, this.endCountry);
        List<Country> path = result.getPath();
        List<Double> distances = result.getDistances();
        Trip trip = new Trip(this.tripName, this.startCountry, this.endCountry, path, distances);
        tripsList.addTrip(trip);
        ui.printEdit(tripName);
    }
}