package seedu.traveller.commands;

import seedu.traveller.exceptions.DuplicateTripException;
import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;
import seedu.traveller.worldmap.Country;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author gavienwz


public class EditCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditCommand.class.getName());
    private final String tripName;
    private final String newTripName;
    private final String startCountry;
    private final String endCountry;

    public EditCommand(String tripName, String newTripName, String startCountry, String endCountry) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.newTripName = newTripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        logger.log(Level.INFO, "Created an edit command: \n" + this);
    }

    @Override
    public String toString() {
        return "Edit command:"
                + "\n\ttripName: " + tripName
                + "\n\tnewTripName: " + newTripName
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry;
    }

    public void editTripInfo(Trip current, String newTripName, List<Country> path, List<Double> distances) {
        current.setTripName(this.newTripName);
        current.setStartCountryCode(this.startCountry);
        current.setEndCountryCode(this.endCountry);
        current.setPath(path);
        current.setDistances(distances);
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(this.tripName);
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }
        if (!isValidNewTripName(tripsList)) {
            throw new DuplicateTripException(this.newTripName);
        }
        assert tripIndex < tripsList.getSize() && tripIndex > -1 : "The trip index is out of bound.";

        MinCalcResult result = WorldMap.calcMinDistance(this.startCountry, this.endCountry);
        List<Country> path = result.getPath();
        List<Double> distances = result.getDistances();
        Trip current = tripsList.getTrip(tripIndex);
        editTripInfo(current, this.newTripName, path, distances);
        ui.printEdit(newTripName);
    }

    public boolean isValidNewTripName(TripsList tripsList) throws TravellerException {
        int valid = tripsList.getTripIndex(this.newTripName);
        if (valid == -1) {
            return true;
        }
        return false;
    }
}
