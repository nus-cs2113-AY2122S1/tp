package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList, Storage storage) throws DukeException {
        try {
            taskList.markTaskAsDone(taskIndex);
            ui.printDoneTask(taskList, taskIndex);
            storage.saveData(taskList, lessonList);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }
}
