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
    private String newTripName;
    private String startCountry;
    private String endCountry;

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

    private void setStartCountry(Trip current, String startCountry) {
        if (startCountry.equals("")) {
            this.startCountry = current.getStartCountryCode();
        } else {
            this.startCountry = startCountry;
        }
    }

    private void setEndCountry(Trip current, String endCountry) {
        if (endCountry.equals("")) {
            this.endCountry = current.getEndCountryCode();
        } else {
            this.endCountry = endCountry;
        }
    }

    private void setNewTripName(Trip current, String newTripName) {
        if (newTripName.equals("")) {
            this.newTripName = current.getTripName();
        } else {
            this.newTripName = newTripName;
        }
    }

    private void editTripInfo(Trip current, List<Country> path, List<Double> time) {
        current.setTripName(this.newTripName);
        current.setStartCountryCode(this.startCountry);
        current.setEndCountryCode(this.endCountry);
        current.setPath(path);
        current.setTime(time);
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int errorFlag = 1;
        int tripIndex = tripsList.getTripIndex(this.tripName);
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }
        if (!isValidNewTripName(tripsList)) {
            throw new DuplicateTripException(this.newTripName);
        }
        assert tripIndex < tripsList.getSize() && tripIndex > -1 : "The trip index is out of bound.";

        Trip current = tripsList.getTrip(tripIndex);

        setAll(current, this.newTripName, this.startCountry, this.endCountry);
        assert !this.newTripName.equals("") && !this.startCountry.equals("") && !this.endCountry.equals("");

        MinCalcResult result = WorldMap.calcMinTime(this.startCountry, this.endCountry);
        if (result.getError() == errorFlag) {
            return;
        }
        List<Country> path = result.getPath();
        List<Double> time = result.getTime();
        editTripInfo(current, path, time);
        ui.printEdit(tripName);
    }

    private boolean isValidNewTripName(TripsList tripsList) throws TravellerException {
        int valid = tripsList.getTripIndex(this.newTripName);
        return valid == -1;
    }

    private void setAll(Trip current, String newTripName, String startCountry, String endCountry) {
        setNewTripName(current, newTripName);
        setStartCountry(current, startCountry);
        setEndCountry(current, endCountry);
    }

}
