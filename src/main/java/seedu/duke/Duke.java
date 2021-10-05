package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.parser.Parser;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class Duke {
    private final Ui ui;
    private TaskList taskList;
    private LessonList lessonList;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        lessonList = new LessonList();
        taskList = new TaskList();
        parser = new Parser();
    }

    public void startProgram() {
        boolean isExit = false;
        // try {
        while (!isExit) {
            String userResponse = ui.readUserResponse();
            try {
                Command command = parser.parse(userResponse);
            } catch (DukeException e) {
                e.getMessage(); // error while parsing input
            }
            // Command command = Parser.parse(userResponse);
            // command.execute(ui, taskList, lessonList);
            // isExit = command.isExit();
            isExit = true;
        }
        // } catch (DukeException e) {
        //     ui.printMessage(e);
        // }
    }

    public void run() {
        ui.printGreeting();
        this.startProgram();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
