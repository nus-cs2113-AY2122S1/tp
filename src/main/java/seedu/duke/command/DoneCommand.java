package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    //index of task to mark as done
    private int taskIndex;

    /**
     * Creates a new DoneCommand for a task
     * @param taskIndex index of the task to mark as done
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, LessonList lessonList, Storage storage) throws DukeException {
        try {
            Task task = taskList.getTask(taskIndex);
            if (task.isDone()) {
                throw new DukeException(Message.INFO_TASK_COMPLETED);
            }
            taskList.markTaskAsDone(taskIndex);
            ui.printDoneTask(taskList, task);
            storage.saveData(taskList, lessonList);
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException(Message.ERROR_INDEX_OUT_OF_BOUNDS);
        } catch (IOException e) {
            throw new DukeException(e.toString());
        }
    }
}
