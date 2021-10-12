package seedu.duke;

import java.io.IOException;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

public class Duke {
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
            ui.printMessage(Message.DATA_RETRIEVED_SUCCESSFULLY);
        } catch (DukeException | IOException e) {
            ui.printMessage(e.toString());
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
        boolean isExit = false;
        while (!isExit) {
            try {
                String userResponse = ui.readUserResponse();
                Command command = Parser.parse(userResponse);
                command.execute(ui, taskList, lessonList, storage);
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.printMessage(e.toString());
            }
        }
    }

    public void run() {
        ui.printGreeting();
        this.startProgram();
        ui.printExit();
    }

    /** The main method. Creates an instance of Duke and run it. */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
