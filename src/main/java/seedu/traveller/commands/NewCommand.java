package seedu.traveller.commands;

import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.DuplicateTripException;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.worldmap.Country;
import seedu.traveller.worldmap.MinCalcResult;
import seedu.traveller.worldmap.WorldMap;
import seedu.traveller.worldmap.exceptions.EmptyVertexException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//@@author Uxinnn
public class NewCommand extends Command {
    private static final Logger logger = Logger.getLogger(NewCommand.class.getName());
    private final String tripName;
    private final String startCountry;
    private final String endCountry;

    public NewCommand(String tripName, String startCountry, String endCountry) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        logger.log(Level.INFO, "Created a new command: \n" + this);
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

    /**
     * Used to pretty print to the logger.
     * @return <code>String</code> of the <code>NewCommand</code>'s contents.
     */
    @Override
    public String toString() {
        return "New command:"
                + "\n\ttripName: " + getTripName()
                + "\n\tstartCountry: " + getStartCountry()
                + "\n\tendCountry: " + getEndCountry();
    }

    /**
     * Performs the action of creating a new trip for the <code>TripsList</code>.
     * @param tripsList The <code>TripsList</code> that the command will be executed on.
     * @param ui The <code>Ui</code> that will be used to print out the outcome of the execution.
     * @throws TravellerException To be thrown if there exists another trip with the specified <code>tripName</code>.
     */
    public void execute(TripsList tripsList, Ui ui) throws TravellerException, EmptyVertexException {
        for (int i = 0; i < tripsList.getSize(); i++) {
            if (tripsList.getTrip(i).getTripName().equals(getTripName())) {
                throw new DuplicateTripException(getTripName());
            }
        }
        MinCalcResult result = WorldMap.calcMinDistance(getStartCountry(), getEndCountry());
        if (result.getError() == 1) {
            return;
        }
        List<Country> path = result.getPath();
        List<Double> distances = result.getDistances();
        Trip trip = new Trip(getTripName(), getStartCountry(), getEndCountry(), path, distances);
        tripsList.addTrip(trip);
        ui.printNewTripCreated(getTripName());
    }
}