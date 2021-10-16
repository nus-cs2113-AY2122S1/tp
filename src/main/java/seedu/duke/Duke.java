package seedu.duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.logic.parser.Parser;
import seedu.duke.model.module.FullModuleList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.TaskList;
import seedu.duke.commons.core.Messages;
import seedu.duke.ui.Ui;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;
    private LessonList lessonList;
    private ModuleList moduleList;
    private FullModuleList fullModuleList;

    /**
     * The constructor method. Initializes ui and storage objects.
     * Fetches task and lesson data from a saved file if it exists,
     * otherwise creates a new task and lesson objects.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            fullModuleList = new FullModuleList();
        } catch (DukeException | FileNotFoundException e) {
            ui.printMessage(e.getMessage());
        }
        try {
            taskList = new TaskList(TaskList.deserialize(storage.loadData()));
            lessonList = new LessonList(LessonList.deserialize(storage.loadData()));
            moduleList = new ModuleList(); // todo add module list deserialization
            ui.printMessage(Messages.SUCCESS_RETRIEVING_DATA);
        } catch (DukeException | IOException e) {
            ui.printMessage(e.getMessage());
            storage.createNewData(ui);
            taskList = new TaskList();
            lessonList = new LessonList();
            moduleList = new ModuleList();
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
                command.execute(ui, storage, taskList, lessonList);
                isExit = command.isExit();
            } catch (DukeException | IOException e) {
                ui.printMessage(e.getMessage());
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
        assert false : "dummy assertion set to fail";
        Duke duke = new Duke();
        duke.run();
    }
}
