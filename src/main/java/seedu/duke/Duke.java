package seedu.duke;

import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.exceptions.LotsException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static Logger log = Logger.getLogger("LOTS");

    private static PeopleManager manager = new PeopleManager();

    public static void main(String[] args) {
        manager = Storage.initialiseFile();
        Ui.printWelcome();
        runDuke();
        Ui.printGoodbye();
    }

    /**
     * The main function that duke runs on.
     */
    private static void runDuke() {
        Command currentCommand;
        boolean isEnd = false;
        while (!isEnd) {
            try {
                String userInput = Ui.readInput();
                currentCommand = Parser.getCommand(userInput);
                assert currentCommand != null : "Command returned cannot be null!";
                currentCommand.setData(manager);
                currentCommand.execute();
                Storage.updateFile(manager);
                if (currentCommand instanceof ByeCommand) {
                    isEnd = true;
                }
            } catch (LotsException e) {
                Ui.printWithBorder(e.getMessage());
            } catch (Exception x) {
                Ui.printWithBorder("Oops! Unknown error. Please try again.");
                log.log(Level.SEVERE, x.getMessage());
            }
        }
    }
}