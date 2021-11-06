package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.ErrorCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.Parser;
import seedu.duke.common.LibmgrException;
import seedu.duke.data.Catalogue;
import seedu.duke.storage.Storage;
import seedu.duke.ui.TextUI;

import java.util.NoSuchElementException;

/**
 * Main class of the application.
 */
public class Duke {
    private static TextUI ui;
    private static Catalogue catalogue;
    private static Parser parser;
    private static Storage storage;

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) throws LibmgrException {
        ui = new TextUI();
        catalogue = new Catalogue();
        parser = new Parser();
        storage = new Storage(ui);
        // Restore previous state of catalogue
        storage.read(catalogue);

        // Continue to read, parse and execute commands until exit command is issued by user
        while (true) {
            // Gets one line of user input
            String userInput;
            Command userCommand;
            try {
                userInput = ui.read();
                userCommand = parser.parse(userInput);
            } catch (NoSuchElementException e) {
                userCommand = new ExitCommand();
            }
            // Parse line to create relevant command object
            assert userCommand != null : "Parsed command returns null";
            // Executes logic of command
            userCommand.execute(ui, catalogue);
            // Check whether to exit program
            if (userCommand.isExit()) {
                break;
            }
            // Write current state of catalogue to data.json
            storage.write(catalogue);
        }
    }
}
