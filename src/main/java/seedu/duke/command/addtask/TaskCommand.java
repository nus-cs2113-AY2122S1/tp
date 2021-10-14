package seedu.duke.command.addtask;

import java.util.HashMap;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;

public abstract class TaskCommand extends Command {
    private static String TASK_CREATED_MESSAGE = "Task created!\n%s";

    public TaskCommand(HashMap<String, String> commandArguments) {
        super(commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message;
        try {
            Task task = createTask();
            TaskManager.addTask(task);
            message = String.format(TASK_CREATED_MESSAGE, task.getTaskEntryDescription());
        } catch (GetTaskFailedException gtfe) {
            message = gtfe.getMessage();
        }
        return new CommandResult(message, true, false);
    }

    abstract Task createTask() throws GetTaskFailedException;
}