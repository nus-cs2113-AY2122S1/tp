package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList) throws DukeException {
        try {
            taskList.markTaskAsDone(taskIndex);
            ui.printDoneTask(taskList, taskIndex);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        }
    }
}
