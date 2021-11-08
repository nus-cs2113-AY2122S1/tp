package seedu.tp.command.addtask;

import java.util.Map;

import seedu.tp.command.Command;
import seedu.tp.command.CommandResult;
import seedu.tp.exception.GetTaskFailedException;
import seedu.tp.parser.TaskUsageParser;
import seedu.tp.task.Task;
import seedu.tp.task.taskmanager.TaskManager;
import seedu.tp.task.factory.TaskFactory;

//@@author SeanRobertDH

/**
 * Class for TaskCommand. that is extended by Task Commands superclasses.
 */
public abstract class TaskCommand extends Command {

    private static String TASK_CREATED_MESSAGE = "Task created!\n%s";

    private static final String OPTIONAL_ARGUMENT_FORMAT = "[--%s]";

    /**
     * Constructs the TaskCommand with the program {@link #taskManager}
     * and the <code>commandArguments</code> specified in Command.
     *
     * @param taskManager      the program's {@link seedu.tp.task.taskmanager.TaskManager}.
     * @param commandArguments a <code>Map&lt;String, String&gt;</code>
     *                         of <code>flags</code> to <code>flag values</code>.
     */
    public TaskCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    /**
     * passes the <code>commandArguments</code> to the respective superclasses of
     * {@link seedu.tp.task.factory.TaskFactory} defined
     * in superclasses of Task Commands and adds the created
     * Task to the {@link #taskManager}.
     *
     * @return The message from the command in CommandResult.
     * @throws java.lang.Exception Any uncaught Exceptions.
     */
    @Override
    public CommandResult executeCommand() throws Exception {
        String message;
        try {
            String mainArgument = getMainArgument();
            if (mainArgument == null || mainArgument.equals("")) {
                throw new NullPointerException();
            }
            Task task = addCreatedTask();
            message = String.format(TASK_CREATED_MESSAGE, task.getTaskEntryDescription());
        } catch (NullPointerException npe) {
            message = getUsageMessage();
        } catch (GetTaskFailedException gtfe) {
            message = gtfe.getMessage();
        }
        return new CommandResult(message, false);
    }

    private Task addCreatedTask() throws GetTaskFailedException {
        TaskFactory taskFactory = setTaskFactory();
        Task task = taskFactory.getTask();
        taskManager.addTask(task);
        return task;
    }

    @Override
    protected String getUsage() {
        return getTaskUsage() + ' '
            + TaskUsageParser.getOptionalTaskArguments(OPTIONAL_ARGUMENT_FORMAT);
    }

    abstract String getTaskUsage();

    abstract TaskFactory setTaskFactory();
}