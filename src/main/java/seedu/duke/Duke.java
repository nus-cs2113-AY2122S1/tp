package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private LessonList lessonList;
    private Storage storage;

    public Duke() {
        // TODO: Implement loading from a save file
        ui = new Ui();
        taskList = new TaskList();
        lessonList = new LessonList();
        storage = new Storage();
        storage.createNewData(ui);
    }

    public void startProgram() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String userResponse = ui.readUserResponse();
                Command command = Parser.parse(userResponse);
                command.execute(ui, taskList, lessonList);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.printMessage(e.toString());
            }
        }
    }

    public void run() {
        ui.printGreeting();
        this.startProgram();
        ui.printExit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
