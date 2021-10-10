package seedu.traveller.commands;

import seedu.traveller.Trip;
import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;


public class ViewAllCommand extends Command {
    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        ui.printAllTrips(tripsList);
    }
}