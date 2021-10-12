package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;


public class ExitCommand extends Command {
    public void execute(TripsList tripsList, Ui ui) {
        setExit();
        ui.printExit();
    }
}
