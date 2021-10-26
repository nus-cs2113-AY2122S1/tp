package seedu.traveller.commands;

import seedu.traveller.objects.Trip;
import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

import java.util.logging.Level;
import java.util.logging.Logger;


//@@author Uxinnn
public class AddDayCommand extends Command {
    private static final Logger logger = Logger.getLogger(NewCommand.class.getName());
    private final String tripName;
    private final int numberOfDays;

    public AddDayCommand(String tripName, int numberOfDays) {
        logger.setLevel(Level.INFO);
        assert !tripName.equals("all") : "'all' is an invalid tripName.";
        assert !tripName.equals("") : "'' is an invalid tripName.";
        this.tripName = tripName;
        assert numberOfDays >= 0 : "Number of days is negative.";
        this.numberOfDays = numberOfDays;
        logger.log(Level.INFO, "Created an addDay command: \n" + this);
    }

    public String getTripName() {
        return tripName;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    @Override
    public String toString() {
        return "AddTripDay command:"
                + "\n\ttripName: " + getTripName();
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        Trip trip = tripsList.getTrip(getTripName());
        int originalNumberOfDays = trip.getDaySize();
        for (int i = 0; i < getNumberOfDays(); i++) {
            trip.addDay();
        }
        int finalNumberOfDays = trip.getDaySize();
        assert originalNumberOfDays + getNumberOfDays() == finalNumberOfDays : "Days are added incorrectly.";
        ui.printAddDayToTrip(getTripName(), getNumberOfDays());
    }
}
