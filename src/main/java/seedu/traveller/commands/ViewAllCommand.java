package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;


public class ViewAllCommand extends Command {
    public void execute(TripsList tripsList, Ui ui) {
        ui.printAllTrips(tripsList);
    }
}