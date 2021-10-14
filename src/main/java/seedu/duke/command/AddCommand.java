package seedu.duke.command;

import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.task.Task;

import seedu.duke.task.TaskManager;
import seedu.duke.task.factory.TaskFactory;

public class AddCommand extends Command {
    private static String TASK_CREATED_MESSAGE = "Task Created";

    public AddCommand(HashMap<String, String> commandArguments) {
        super(commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        Task createdTask;
        String message;
        try {
            createdTask = TaskFactory.getTask(commandArguments);
            TaskManager.addTask(createdTask);
            message = TASK_CREATED_MESSAGE;
        } catch (GetTaskFailedException gtfe) {
            message = gtfe.getMessage();
        }
        return new CommandResult(message, true, false);
    }
}
