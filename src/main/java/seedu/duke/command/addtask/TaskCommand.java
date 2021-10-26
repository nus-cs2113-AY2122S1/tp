package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.Command;
import seedu.duke.command.CommandEnum;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.local.DataManager;
import seedu.duke.local.TasktoLineConverter;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;

//@@author SeanRobertDH
public abstract class TaskCommand extends Command {
    private static String TASK_CREATED_MESSAGE = "Task created!\n%s";

    public TaskCommand(CommandEnum command, Map<String, String> commandArguments) {
        super(command, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message;
        try {
            if (getMainArgument() == null) {
                throw new NullPointerException();
            }
            Task task = createTask();
            TaskManager.addTask(task);
            DataManager.addTaskLine(TasktoLineConverter.convertTaskToLine(task));
            message = String.format(TASK_CREATED_MESSAGE, task.getTaskEntryDescription());
        } catch (NullPointerException npe) {
            message = getUsage();
        } catch (GetTaskFailedException gtfe) {
            message = gtfe.getMessage();
        }
        return new CommandResult(message, true, false);
    }

    abstract Task createTask() throws GetTaskFailedException;
}