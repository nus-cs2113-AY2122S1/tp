package seedu.duke.logic.commands.task;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.DeleteCommand;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
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
        if (isDeleteAll) {
            taskList.deleteAllTasks();
            assert taskList.isEmpty() : Ui.PADDING + "Task list should be empty";
            ui.printMessage("All your tasks have been successfully removed.");
        } else {
            Task deletedTask = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            ui.printTaskDeleted(deletedTask, taskList.getSize());
        }
        storage.saveData(taskList, lessonList);
    }
}
