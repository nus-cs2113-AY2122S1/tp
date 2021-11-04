package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.parser.DateParser;
import seedu.duke.task.factory.DeadlineFactory;
import seedu.duke.task.factory.TaskFactory;
import seedu.duke.task.taskmanager.TaskManager;

//@@author SeanRobertDH
/**
 * Class for DeadlineCommand. Extends {@link seedu.duke.command.addtask.TaskCommand}
 * and sets the respective TaskFactory {@link seedu.duke.task.factory.DeadlineFactory}
 * using {@link #setTaskFactory()}.
 */
public class DeadlineCommand extends TaskCommand {

    private static final String USAGE = "-> Adding a deadline: deadline <description> <--%s %s>";

    /**
     * Constructs the DeadlineCommand with the program {@link #taskManager}
     * and the <code>commandArguments</code> specified in Command.
     *
     * @param taskManager the program's {@link seedu.duke.task.taskmanager.TaskManager}.
     * @param commandArguments a <code>Map&lt;String, String&gt;</code>
     *     of <code>flags</code> to <code>flag values</code>.
     */
    public DeadlineCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    TaskFactory setTaskFactory() {
        return new DeadlineFactory(commandArguments);
    }

    @Override
    protected String getTaskUsage() {
        return String.format(USAGE, DeadlineFlag.DUE_DATE, DateParser.getDefaultDateFormat());
    }
}
