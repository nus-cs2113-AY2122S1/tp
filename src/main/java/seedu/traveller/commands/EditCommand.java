package seedu.traveller.commands;

import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;
import seedu.traveller.worldmap.Country;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author gavienwz


public class EditCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditCommand.class.getName());
    private final String tripName;
    private final String startCountry;
    private final String endCountry;

    public EditCommand(String tripName, String startCountry, String endCountry) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        logger.log(Level.INFO, "Created an edit command: \n" + this);
    }

    @Override
    public String toString() {
        return "Edit command:"
                + "\n\ttripName: " + tripName
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry;
    }

    public void editTripInfo(Trip current, List<Country> path, List<Double> distances) {
        current.setStartCountryCode(this.startCountry);
        current.setEndCountryCode(this.endCountry);
        current.setPath(path);
        current.setDistances(distances);
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException, EmptyVertexException {
        int tripIndex = tripsList.getTripIndex(this.tripName);
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }
        assert tripIndex < tripsList.getSize() && tripIndex > -1 : "The trip index is out of bound.";

        MinCalcResult result = WorldMap.calcMinDistance(this.startCountry, this.endCountry);
        List<Country> path = result.getPath();
        List<Double> distances = result.getDistances();
        Trip current = tripsList.getTrip(tripIndex);
        editTripInfo(current, path, distances);
        ui.printEdit(tripName);
    }
}
