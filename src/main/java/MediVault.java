import command.Command;
import command.CommandList;
import errors.InvalidCommandException;
import inventory.Medicine;
import utilities.parser.CommandParser;
import utilities.parser.Mode;
import utilities.storage.Storage;
import utilities.ui.Ui;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static utilities.parser.Mode.ORDER;
import static utilities.parser.Mode.PRESCRIPTION;
import static utilities.parser.Mode.STOCK;

//@@author alvintan01

/**
 * Helps to start the application, and initialise all variables.
 * It will continuously prompt for input from the user until "EXIT" is received.
 */
public class MediVault {
    private static Logger logger = Logger.getLogger("MediVault");
    private Mode mode = Mode.STOCK;

    public MediVault() {
        ArrayList<Medicine> medicines = Medicine.getInstance();
        Storage storage = Storage.getInstance();
        medicines.addAll(storage.loadData());
        logger.log(Level.INFO, "All variables are initialised.");
    }

    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        logger.log(Level.INFO, "MediVault is starting up");
        new MediVault().run();
    }

    /**
     * Prompts input from user and processes it indefinitely until "EXIT" is received.
     */
    private void run() {
        Ui ui = Ui.getInstance();
        ui.printWelcomeMessage();
        CommandParser commandParser = new CommandParser();

        String userInput = "";

        // Loops till exit is received
        while (true) {
            System.out.print("[" + mode + "] > ");
            // Reads user input
            userInput = ui.getInput();
            try {
                String[] userCommand = commandParser.parseCommand(userInput);
                String commandString = userCommand[0];
                String commandParameters = userCommand[1];

                // Check is user is changing modes
                if (commandString.equalsIgnoreCase(STOCK.name()) || commandString.equalsIgnoreCase(PRESCRIPTION.name())
                        || commandString.equalsIgnoreCase(ORDER.name())) {
                    mode = commandParser.changeMode(ui, commandString, mode);
                    continue;
                }

                Command command = commandParser.processCommand(commandString, commandParameters, mode);
                command.execute();

                if (commandString.equals(CommandList.EXIT)) { // User entered exit
                    break;
                }
            } catch (InvalidCommandException e) {
                // Invalid Command
                ui.printInvalidCommandMessage();
                logger.log(Level.WARNING, "An invalid command was entered!");
            }
        }
        logger.log(Level.INFO, "MediVault is shutting down");
    }
}
