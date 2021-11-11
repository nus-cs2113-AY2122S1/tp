package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;

import java.util.logging.Level;
import java.util.logging.Logger;
//@@author Cuiminjing

public class DeleteCommand extends Command {
    private static final Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    private final String tripName;

    public DeleteCommand(String tripName) {
        logger.setLevel(Level.INFO);
        this.tripName = tripName;
        logger.log(Level.INFO, "Created a delete command: \n" + this);
    }

    /**
     * Used to pretty print to the logger.
     * @return <code>String</code> of the <code>DeleteCommand</code>'s contents.
     */
    @Override
    public String toString() {
        return "Delete command:"
                + "\n\ttripName: " + tripName;
    }

    /**
     * Performs the action of deleting a trip from the <code>TripsList</code>.
     * @param tripsList The <code>TripsList</code> that the command will be executed on.
     * @param ui The <code>Ui</code> that will be used to print out the outcome of the execution.
     * @throws TravellerException To be thrown if the <code>Trip</code> to be deleted cannot be found.
     */
    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(tripName);
        assert tripIndex < tripsList.getSize() : "The trip index is out of bound.";
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        tripsList.deleteTrip(tripIndex);
        ui.printDelete(tripName);
    }
}
