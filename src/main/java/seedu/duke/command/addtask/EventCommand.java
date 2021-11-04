package seedu.duke.command.addtask;

import java.util.Map;

import seedu.duke.command.flags.EventFlag;
import seedu.duke.parser.DateParser;
import seedu.duke.task.factory.EventFactory;
import seedu.duke.task.factory.TaskFactory;
import seedu.duke.task.taskmanager.TaskManager;

//@@author SeanRobertDH
/**
 * Class for EventCommand. Extends {@link seedu.duke.command.addtask.TaskCommand}
 * and sets the respective TaskFactory {@link seedu.duke.task.factory.EventFactory}.
 *  using {@link EventCommand#setTaskFactory()}.
 */
public class EventCommand extends TaskCommand {

    private static final String USAGE = "-> Adding an event: event <description> <--%s %s> <--%s %s>";

    /**
     * Constructs the EventCommand with the program {@link #taskManager}
     * and the <code>commandArguments</code> specified in Command.
     *
     * @param taskManager the program's {@link seedu.duke.task.taskmanager.TaskManager}.
     * @param commandArguments a <code>Map&lt;String, String&gt;</code>
     *     of <code>flags</code> to <code>flag values</code>.
     */
    public EventCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    TaskFactory setTaskFactory() {
        return new EventFactory(commandArguments);
    }

    @Override
    protected String getTaskUsage() {
        return String.format(USAGE,
            EventFlag.START_DATE, DateParser.getDefaultDateFormat(),
            EventFlag.END_DATE, DateParser.getDefaultDateFormat());
    }
}