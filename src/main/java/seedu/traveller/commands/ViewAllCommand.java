package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewAllCommand extends Command {
    private static final Logger logger = Logger.getLogger(ViewAllCommand.class.getName());

    public ViewAllCommand() {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created a viewall command");
    }

    /**
     * Performs the action of printing all trips in the <code>TripsList</code> to the console.
     * @param tripsList The <code>TripsList</code> that the command will be executed on.
     * @param ui The <code>Ui</code> that will be used to print out the outcome of the execution.
     */
    public void execute(TripsList tripsList, Ui ui) {
        ui.printAllTrips(tripsList);
    }

    @Override
    public String toString() {
        return "Viewall command";
    }
}