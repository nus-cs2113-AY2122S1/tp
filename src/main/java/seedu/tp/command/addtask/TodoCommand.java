package seedu.tp.command.addtask;

import java.util.Map;

import seedu.tp.command.flags.TodoFlag;
import seedu.tp.parser.DateParser;
import seedu.tp.task.factory.TodoFactory;
import seedu.tp.task.taskmanager.TaskManager;

//@@author SeanRobertDH
/**
 * Class for TodoCommand. Extends {@link seedu.tp.command.addtask.TaskCommand}
 * and sets the respective TaskFactory {@link seedu.tp.task.factory.TodoFactory}.
 *  using {@link TodoCommand#setTaskFactory()}.
 */
public class TodoCommand extends TaskCommand {

    private static String USAGE = "-> Adding a todo: todo <description> [--%s %s]";

    /**
     * Constructs the TodoCommand with the program {@link #taskManager}
     * and the <code>commandArguments</code> specified in Command.
     *
     * @param taskManager the program's {@link seedu.tp.task.taskmanager.TaskManager}.
     * @param commandArguments a <code>Map&lt;String, String&gt;</code>
     *     of <code>flags</code> to <code>flag values</code>.
     */
    public TodoCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    @Override
    TodoFactory setTaskFactory() {
        return new TodoFactory(commandArguments);
    }

    @Override
    protected String getTaskUsage() {
        return String.format(USAGE, TodoFlag.DO_ON_DATE, DateParser.getDefaultDateFormat());
    }

}
