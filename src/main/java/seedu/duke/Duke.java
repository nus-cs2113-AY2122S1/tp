package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandNames;
import seedu.duke.exceptions.FoodoramaException;
import seedu.duke.logger.LoggerManager;
import seedu.duke.storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Duke.class.getName());
        LoggerManager.loggerFileSetup();
        LoggerManager.setupLogger(logger);
        logger.log(Level.INFO, "Food-o-rama has begun running");
        InputParser parser = new InputParser();
        Storage.load();
        Scanner input = new Scanner(System.in);
        Ui ui =  new Ui();

        ui.printWelcomeMsg();

        String userInput = input.nextLine().toLowerCase();
        while (!userInput.equals("bye")) {
            try {
                //Get command name and parameters
                CommandNames userCommandName = parser.getCommandName(userInput);
                ArrayList<String> parameters = parser.getParameters(userInput, userCommandName);

                //Switch to appropriate callback function and call function
                Command userCommand = userCommandName.getCallbackCommand();
                userCommand.execute(parameters);
                Storage.write("ingredient");
                Storage.write("dish");

            } catch (FoodoramaException exception) {
                //ui.printInvalidCommandMsg();
                System.out.println(ui.getLineDivider());
                System.out.println(exception.getMessage());
                System.out.println(ui.getLineDivider());
            }
            userInput = input.nextLine();
        }
        ui.printExitMsg();
    }
}
