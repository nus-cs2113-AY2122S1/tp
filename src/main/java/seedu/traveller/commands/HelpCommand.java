package seedu.traveller.commands;

import seedu.traveller.objects.TripsList;
import seedu.traveller.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HelpCommand extends Command {
    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    public HelpCommand() {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created an Help command");
    }


    public void execute(TripsList tripsList, Ui ui) {
        ui.printHelp();
    }

    @Override
    public String toString() {
        return "Help command";
    }
}
