package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;


//@@author conradwee
public class ExitCommand extends Command {
    private static final Logger logger = Logger.getLogger(ExitCommand.class.getName());

    public ExitCommand() {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created an exit command");
    }

    /**
     * Performs the action of exiting <code>Traveller</code>.
     * @param tripsList The <code>TripsList</code> that the command will be executed on.
     * @param ui The <code>Ui</code> that will be used to print out the outcome of the execution.
     */
    public void execute(TripsList tripsList, Ui ui) {
        setExit();
        ui.printExit();
    }

    @Override
    public String toString() {
        return "Exit command";
    }
}
