package seedu.tp.command.addtask;

import java.util.Map;

import seedu.tp.command.flags.DeadlineFlag;
import seedu.tp.parser.DateParser;
import seedu.tp.task.factory.DeadlineFactory;
import seedu.tp.task.taskmanager.TaskManager;

//@@author SeanRobertDH
/**
 * Class for DeadlineCommand. Extends {@link seedu.tp.command.addtask.TaskCommand}
 * and sets the respective TaskFactory {@link seedu.tp.task.factory.DeadlineFactory}
 * using {@link #setTaskFactory()}.
 */
public class DeadlineCommand extends TaskCommand {

    private static final String USAGE = "-> Adding a deadline: deadline <description> <--%s %s>";

    /**
     * Constructs the DeadlineCommand with the program {@link #taskManager}
     * and the <code>commandArguments</code> specified in Command.
     *
     * @param taskManager the program's {@link seedu.tp.task.taskmanager.TaskManager}.
     * @param commandArguments a <code>Map&lt;String, String&gt;</code>
     *     of <code>flags</code> to <code>flag values</code>.
     */
    public DeadlineCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    DeadlineFactory setTaskFactory() {
        return new DeadlineFactory(commandArguments);
    }

    @Override
    protected String getTaskUsage() {
        return String.format(USAGE, DeadlineFlag.DUE_DATE, DateParser.getDefaultDateFormat());
    }
}
