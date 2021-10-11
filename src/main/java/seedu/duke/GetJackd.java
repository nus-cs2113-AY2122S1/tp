package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.logger.LoggerUtil;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.Scanner;
import java.util.logging.Logger;

public class GetJackd {
    private Storage storage;
    private final WorkoutList workouts;
    private final Ui ui;
    private static final Logger LOGGER = Logger.getLogger(GetJackd.class.getName());

    public GetJackd() {
        workouts = new WorkoutList();
        ui = new Ui();
        try {
            storage = new Storage();
            storage.loadData(workouts);
        } catch (GetJackDException e) {
            Ui.printErrorMessage(e);
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        setupGetJackDLogger();
        Ui.printWelcomeMessage();
        new GetJackd().run();
        Ui.printByeMessage();
        exit();
    }

    private static void setupGetJackDLogger() {
        LoggerUtil.loggerUtilSetup();
        LoggerUtil.setupLogger(LOGGER);
        LOGGER.info("Start Application");
    }

    /**
     * Runs the program until user enters the exit command (bye).
     */
    private void run() {
        Scanner input = new Scanner(System.in);
        Command c;
        boolean isExit = false;
        while (!isExit) {
            String userInput = input.nextLine();
            try {
                c = new Parser().parseCommand(userInput);
                c.executeUserCommand(workouts, ui, storage);
                isExit = ExitCommand.isExit(c);
            } catch (GetJackDException e) {
                Ui.printErrorMessage(e);
            }
        }
    }

    private static void exit() {
        System.exit(0);
    }
}

