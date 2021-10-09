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
    private Ui ui;
    private TaskList taskList;
    private LessonList lessonList;
    private Storage storage;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        try {
            taskList = new TaskList(TaskList.deserialize(storage.loadData()));
            lessonList = new LessonList(LessonList.deserialize(storage.loadData()));
            ui.printMessage(Message.DATA_RETRIEVED_SUCCESSFULLY);
        } catch (DukeException | IOException e) {
            ui.printMessage(e.toString());
            taskList = new TaskList();
            lessonList = new LessonList();
            storage.createNewData(ui);
        }
    }

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

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
