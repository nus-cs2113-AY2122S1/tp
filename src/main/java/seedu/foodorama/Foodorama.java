package seedu.foodorama;

import seedu.foodorama.command.Command;
import seedu.foodorama.command.CommandNames;
import seedu.foodorama.exceptions.FoodoramaException;
import seedu.foodorama.logger.LoggerManager;
import seedu.foodorama.storage.Storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//TODO add limit function


public class Foodorama {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Foodorama.class.getName());
        LoggerManager.loggerFileSetup();
        LoggerManager.setupLogger(logger);
        logger.log(Level.INFO, "Food-o-rama has begun running");
        InputParser parser = new InputParser();
        Storage.load();
        Scanner input = new Scanner(System.in);
        Ui ui =  new Ui();

        // Start interfacing with user
        ClearScreen.clearConsole();
        ui.printWelcomeMsg();

        // Start program
        String userInput = input.nextLine().toLowerCase();
        while (!userInput.equals("bye")) {
            try {
                ui.clearTerminalAndPrintNewPage();
                //Get command name and parameters
                CommandNames userCommandName = parser.getCommandName(userInput);
                ArrayList<String> parameters = parser.getParameters(userInput, userCommandName);

                //Switch to appropriate callback function and call function
                Command userCommand = userCommandName.getCallbackCommand();
                userCommand.execute(parameters);
                Storage.write("ingredient");
                Storage.write("dish");

            } catch (FoodoramaException exception) {
                System.out.println(exception.getMessage());
            }
            userInput = input.nextLine().toLowerCase().trim();
        }
        // Print exit message and close program
        ClearScreen.clearConsole();
        ui.printExitMsg();
    }
}
