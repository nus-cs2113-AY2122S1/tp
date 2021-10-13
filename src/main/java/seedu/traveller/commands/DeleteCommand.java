package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DeleteCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    private final String tripName;

    public DeleteCommand(String tripName) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        logger.log(Level.INFO, "Created a delete command: \n" + this);
    }

    @Override
    public String toString() {
        return "Delete command:"
                + "\n\ttripName: " + tripName;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(tripName);
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        tripsList.deleteTrip(tripIndex);
        ui.printDelete(tripName);
    }
}
