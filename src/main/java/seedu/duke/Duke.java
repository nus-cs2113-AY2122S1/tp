package seedu.duke;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.logic.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.commons.core.Messages;
import seedu.duke.ui.Ui;

public class Duke {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;
    private LessonList lessonList;

    /**
     * The constructor method. Initializes ui and storage objects.
     * Fetches task and lesson data from a saved file if it exists,
     * otherwise creates a new task and lesson objects.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(TaskList.deserialize(storage.loadData()));
            lessonList = new LessonList(LessonList.deserialize(storage.loadData()));
            ui.printMessage(Messages.SUCCESS_RETRIEVING_DATA);
            LOGGER.info("Successfully retrieved data from the save file.");
        } catch (DukeException | IOException e) {
            LOGGER.warning("Failed to retrieve data from the save file.");
            ui.printMessage(e.getMessage());
            storage.createNewData(ui);
            taskList = new TaskList();
            lessonList = new LessonList();
        }
    }

    /**
     * The main routine of the program. Asks for user input, parses
     * to the corresponding command and executes it.
     */
    public void startProgram() {
        LOGGER.info("Executing main routine.");
        boolean isExit = false;
        while (!isExit) {
            try {
                String userResponse = ui.readUserResponse();
                Command command = Parser.parse(userResponse);
                command.execute(ui, storage, taskList, lessonList);
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                LOGGER.warning("Invalid command.");
                ui.printMessage(e.getMessage());
            }
        }
        LOGGER.info("Main routine has ended.");
    }

    public void run() {
        ui.printGreeting();
        this.startProgram();
        ui.printExit();
    }

    /** The main method. Creates an instance of Duke and run it. */
    public static void main(String[] args) {
        LOGGER.info("Initializing Duke.");
        Duke duke = new Duke();
        duke.run();
    }

    private void initializeLogger() {
        LogManager.getLogManager().reset();
        LOGGER.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGGER.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("duke.log");
            fh.setLevel(Level.FINE);
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, Messages.ERROR_FILE_LOGGER, e);
        }
    }
}
