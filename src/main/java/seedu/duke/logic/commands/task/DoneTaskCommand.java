package seedu.duke.logic.commands.task;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.exceptions.CommandException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.commons.core.Messages;
import seedu.duke.ui.Ui;

import java.io.IOException;

public class DoneTaskCommand extends Command {
    private final int taskIndex;

    public DoneTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList)
            throws DukeException, IOException {
        Task task = taskList.getTask(taskIndex);
        if (task.isDone()) {
            throw new CommandException(Messages.ERROR_TASK_IS_ALREADY_DONE);
        }

        taskList.markTaskAsDone(taskIndex);
        assert task.isDone() : "Task should have been marked as done.";
        storage.saveData(taskList, lessonList, "tasks.txt");
        ui.printTaskMarkedAsDone(taskList, task);
    }
}
