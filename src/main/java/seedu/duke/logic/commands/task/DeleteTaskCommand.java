package seedu.duke.logic.commands.task;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DeleteTaskCommand extends Command {
    private final int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList)
            throws DukeException, IOException {
        Task deletedTask = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);
        storage.saveData(taskList, lessonList, "tasks.txt");
        ui.printTaskDeleted(deletedTask, taskList.getSize());
    }
}
