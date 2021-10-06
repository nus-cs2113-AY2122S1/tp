package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import seedu.traveller.exceptions.TravellerException;

public class ExitCommand extends Command {
    public void execute(TripsList tripsList, Ui ui) throws TravellerException {
        setExit();
        ui.printExit();
    }
}
