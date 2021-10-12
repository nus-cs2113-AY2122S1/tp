package seedu.duke.command;

import seedu.duke.exception.DukeException;
import seedu.duke.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.ui.Message;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends DeleteCommand {
    private int taskIndex;

    public DeleteTaskCommand() {
        this.isDeleteAll = true;
    }

    public DeleteTaskCommand(int taskIndex) {
        this.isDeleteAll = false;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList)
            throws DukeException, IOException {
        boolean isLessThanZero = taskIndex < 0;
        boolean isMoreThanMax = taskIndex >= taskList.getSize();
        boolean isTaskIndexValid = !isLessThanZero && !isMoreThanMax;
        if (!isTaskIndexValid) {
            throw new DukeException(Message.ERROR_INVALID_INDEX);
        }

        if (isDeleteAll) {
            taskList.deleteAllTasks();
            ui.printMessage("All your tasks have been successfully removed.");
        } else {
            Task deletedTask = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            ui.printTaskDeleted(deletedTask, taskList.getSize());
        }
        storage.saveData(taskList, lessonList);
    }
}
