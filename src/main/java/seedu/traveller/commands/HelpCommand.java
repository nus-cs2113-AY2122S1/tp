package seedu.traveller.commands;

import seedu.traveller.TripsList;
import seedu.traveller.Ui;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HelpCommand extends Command {
    private static final Logger logger = Logger.getLogger(HelpCommand.class.getName());

    public HelpCommand() {
        logger.setLevel(Level.INFO);
        logger.log(Level.INFO, "Created an Help command");
    }

    /**
     * Performs the action of exiting <code>Traveller</code>.
     * @param tripsList The <code>TripsList</code> that the command will be executed on.
     * @param ui The <code>Ui</code> that will be used to print out the outcome of the execution.
     */
    public void execute(TripsList tripsList, Ui ui) {
       System.out.println("Some of our basic commands are:\n" +
               "new FamilyTrip2021 /from SIN /to MLY : Adds a new trip called FamilyTrip2021 from Singapore (SIN) to Malaysia (MLY).\n" +
               "viewall : Views all your existing trips and their details.\n" +
               "delete FamilyTrip2021 : Deletes the trip called FamilyTrip2021.\n" +
               "exit : Exits the program.");
    }

    @Override
    public String toString() {
        return "Help command";
    }
}
