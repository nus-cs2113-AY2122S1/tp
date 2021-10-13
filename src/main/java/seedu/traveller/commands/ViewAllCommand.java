package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;

import javax.swing.text.View;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewAllCommand extends Command {
    private static final Logger logger = Logger.getLogger(ViewAllCommand.class.getName());

    public ViewAllCommand() {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created a viewall command");
    }

    public void execute(TripsList tripsList, Ui ui) {
        ui.printAllTrips(tripsList);
    }
}