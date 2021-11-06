package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.command.ExitCommand;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.StorageException;
import seedu.duke.logger.LoggerUtil;
import seedu.duke.parser.CommandManager;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.Scanner;
import java.util.logging.Logger;

//@@author KishorKumar11

/**
 * GetJack'd is an application used to help motivated individuals to achieve their fitness goals.
 *
 * @author Ishaan Vyas
 * @author Joel Chiam
 * @author Jonathan Mui
 * @author Kishor Kumar
 * @author Qianqi Koh
 * @version 2.1
 * @since 2021-11-7
 */
public class GetJackd {
    private static final Logger LOGGER = Logger.getLogger(GetJackd.class.getName());
    private final WorkoutList workouts;
    private Ui ui;
    private Storage storage;

    public GetJackd() {
        workouts = new WorkoutList();
        ui = new Ui();
        try {
            storage = new Storage();
            storage.loadData(workouts);
        } catch (GetJackDException e) {
            ui.printErrorMessage(e);
        } catch (StorageException e) {
            ui.printErrorMessage(e);
            exit();
        }
    }

    private static void setupGetJackDLogger() {
        LoggerUtil.loggerUtilSetup();
        LoggerUtil.setupLogger(LOGGER);

        LOGGER.info("Start Application");
    }

    private static void exit() {
        System.exit(0);
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        setupGetJackDLogger();

        Ui.printWelcomeMessage();
        new GetJackd().run();
        exit();
    }

    /**
     * Runs the program until user enters the exit command (bye).
     */
    private void run() {
        boolean isExit = false;
        Scanner input = new Scanner(System.in);
        Command c;

        while (!isExit) {
            String userInput = input.nextLine();
            try {
                c = new CommandManager().generateCommand(userInput);
                CommandResult result = c.executeUserCommand(workouts, storage);
                ui = new Ui();
                ui.showResultToUser(result);
                isExit = ExitCommand.isExit(c);
            } catch (GetJackDException e) {
                ui.printErrorMessage(e);
            }
        }
    }
}

