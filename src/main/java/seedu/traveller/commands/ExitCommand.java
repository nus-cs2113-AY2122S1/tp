package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ExitCommand extends Command {
    private static final Logger logger = Logger.getLogger(ExitCommand.class.getName());

    public ExitCommand() {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created an exit command");
    }

    public void execute(TripsList tripsList, Ui ui) {
        setExit();
        ui.printExit();
    }
}
