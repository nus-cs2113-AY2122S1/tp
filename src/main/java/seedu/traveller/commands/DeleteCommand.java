package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;


public class DeleteCommand extends Command {
    private final String tripName;

    public DeleteCommand(String tripName) {
        this.tripName = tripName;
    }

    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        int tripIndex = tripsList.getTripIndex(this.tripName);
        if (tripIndex == -1) {
            throw new TripNotFoundException();
        }

        tripsList.deleteTrip(tripIndex);
        ui.printDelete(tripName);
    }
}
