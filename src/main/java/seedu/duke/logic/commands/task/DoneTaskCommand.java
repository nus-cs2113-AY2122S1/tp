package seedu.duke.logic.commands.task;

import java.io.IOException;

import seedu.duke.DukeException;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.exceptions.CommandException;
import seedu.duke.model.lesson.LessonList;
import seedu.duke.model.module.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.model.task.Task;
import seedu.duke.model.task.TaskList;
import seedu.duke.commons.core.Message;
import seedu.duke.ui.Ui;

//@@author rebchua39
public class DoneTaskCommand extends Command {
    private final int taskIndex;

    public DoneTaskCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList, LessonList lessonList, ModuleList moduleList)
            throws DukeException, IOException {
        Task task = taskList.getTask(taskIndex);
        if (task.isDone()) {
            throw new CommandException(Message.ERROR_TASK_IS_ALREADY_DONE);
        }

        taskList.markTaskAsDone(taskIndex);
        assert task.isDone() : "Task should have been marked as done.";
        storage.saveData(taskList);
        ui.printTaskMarkedAsDone(taskList, task);
    }
}
