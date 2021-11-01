package seedu.situs;

import seedu.situs.command.AlertExpiringSoonCommand;
import seedu.situs.command.AlertLowStockCommand;
import seedu.situs.exceptions.SitusException;
import seedu.situs.localtime.CurrentDate;
import seedu.situs.parser.Parser;
import seedu.situs.ui.UI;


import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Situs {

    private static UI ui;
    private static final Logger LOGGER = Logger.getLogger(Situs.class.getName());

    /**
     * Starts up the system by creating the UI.
     */
    public static void initialize() {
        CurrentDate.setCurrentDate();
        ui = new UI();

        try {
            UI.printCommandOutput(new AlertExpiringSoonCommand().run());
        } catch (SitusException e) {
            UI.printCommandOutput(e.getMessage());
        }
        try {
            UI.printCommandOutputWithoutTopDivider(new AlertLowStockCommand().run());
        } catch (SitusException e) {
            UI.printCommandOutputWithoutTopDivider(e.getMessage());
        }

        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);
    }


    /**
     * Prints the exit message, then closes the program.
     */
    public static void exit() {
        UI.printGoodbye();
        LOGGER.log(Level.INFO, "Program exited");
        System.exit(0);
    }


    /**
     * Runs the command parser and return the message.
     *
     * @param command user's input command
     * @return result message
     */
    public static String runCommand(String command) {
        LOGGER.log(Level.INFO, "Start to parse user command");
        String msg;

        try {
            msg = Parser.parse(command);
            return msg;
        } catch (SitusException e) {
            LOGGER.log(Level.WARNING, "Error in parsing user command");
            return e.getMessage();
        }
    }

    public static void run() {
        boolean isExit = false;
        String command;
        String resultMsg;

        while (!isExit) {
            command = ui.getUserCommand();
            resultMsg = runCommand(command);
            LOGGER.log(Level.INFO, "User Command Successfully Executed");
            isExit = Parser.isExit(command);

            if (!isExit) {
                UI.printCommandOutput(resultMsg);
            }
        }
    }

    public static void main(String[] args) {
        initialize();
        run();
        exit();
    }
}
