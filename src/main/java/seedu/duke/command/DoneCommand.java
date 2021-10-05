package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Ui;

public class DoneCommand extends Command {
    int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    public void execute(Ui ui, TaskList tasklist, LessonList lessonList) throws DukeException {
        tasklist.markTaskAsDone(taskIndex);
    }
}
