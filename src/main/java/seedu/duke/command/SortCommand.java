package seedu.duke.command;

import seedu.duke.exception.EmptySortCriteriaException;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.task.taskmanager.TaskManager;

import java.util.Map;

//@@author APZH
/**
 * Represents a command to sort the tasks in a tasklist.
 * This command can sort the tasklist by either task type, description, or priority.
 */
public class SortCommand extends Command {

    private static final String USAGE = "-> Sorting your tasklist: sort <--by> <index>";

    public SortCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    //@@author APZH
    /**
     * Executes the sort command.
     * Stores the message to be displayed to the user into {@code message}
     * and to be passed as an input parameter of the {@code CommandResult} object to be returned.
     *
     * @return The command result of the execution.
     */
    @Override
    public CommandResult executeCommand() throws Exception {
        String message = "";

        try {
            message = taskManager.sortTasklist(commandArguments);
        } catch (EmptyTasklistException ete) {
            message = ete.toString();
        } catch (EmptySortCriteriaException esce) {
            message = esce.toString();
        }

        return new CommandResult(message, true, false);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

}
