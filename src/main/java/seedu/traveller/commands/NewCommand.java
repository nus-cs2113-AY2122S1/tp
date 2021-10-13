package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.DuplicateTripException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldmap.Country;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;

import java.util.List;


public class NewCommand extends Command {
    private final String tripName;
    private final String startCountry;
    private final String endCountry;

    public NewCommand(String tripName, String startCountry, String endCountry) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    public String getTripName() {
        return this.tripName;
    }

    public String getStartCountry() {
        return this.startCountry;
    }

    public String getEndCountry() {
        return this.endCountry;
    }

    @Override
    public String toString() {
        return "new " + getTripName() + ": " + getStartCountry() + " to " + getEndCountry();
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        for (int i = 0; i < tripsList.getSize(); i++) {
            if (tripsList.getTrip(i).getTripName().equals(tripName)) {
                throw new DuplicateTripException(tripName);
            }
        }
        MinCalcResult result = WorldMap.calcMinDistance(this.startCountry, this.endCountry);
        List<Country> path = result.getPath();
        List<Double> distances = result.getDistances();
        Trip trip = new Trip(this.tripName, this.startCountry, this.endCountry, path, distances);
        tripsList.addTrip(trip);
        ui.printNewTripCreated(tripName);
    }
}