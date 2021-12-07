package seedu.duke;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

import seedu.duke.exceptions.ClickException;
import seedu.duke.logger.ClickLogger;
import seedu.duke.exceptions.ExceptionHandler;

import seedu.duke.ui.Ui;
import seedu.duke.commands.Command;
import seedu.duke.storage.Storage;
import seedu.duke.parser.Parser;

//@@author nvbinh15

public class Click {

    public static final String RUNNING_CLICK_LOG_MESSAGE = "running click";
    private static Ui ui = new Ui();
    private static ExceptionHandler exceptionHandler = new ExceptionHandler();
    private static Storage storage;
    private static Logger logger;
    private static Parser parser = new Parser();

    static {
        try {
            storage = new Storage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and executes commands from user inputs.
     */
    private static void run() {
        logger = ClickLogger.getNewLogger();
        logger.info(RUNNING_CLICK_LOG_MESSAGE);

        ui.printGreeting();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userInput = in.hasNextLine() ? in.nextLine()  : null;
            try {
                Command c = parser.parseCommand(userInput);
                c.execute(ui, storage);
            } catch (ClickException e) {
                exceptionHandler.handleClickExceptions(e, userInput);
            } catch (Exception  e) {
                exceptionHandler.handleOtherExceptions(e);
            }
        }
    }

    /**
     * Main entry method to run Duke.
     *
     * @param args Unused params.
     */
    public static void main(String[] args) {
        new Click();
        Click.run();
    }
}
