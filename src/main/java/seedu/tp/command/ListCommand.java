package seedu.tp.command;

import java.util.Map;

import seedu.tp.exception.EmptyTasklistException;
import seedu.tp.exception.InvalidPriorityException;
import seedu.tp.exception.InvalidRecurrenceException;
import seedu.tp.exception.InvalidTaskIndexException;
import seedu.tp.exception.InvalidTaskTypeException;
import seedu.tp.exception.ListFormatException;
import seedu.tp.exception.MissingFilterArgumentException;
import seedu.tp.exception.TaskIsNonRecurringException;
import seedu.tp.task.taskmanager.TaskManager;

//@@author APZH
/**
 * Represents a command to list the tasks in a tasklist.
 * This command can filter the tasklist and display the recurrences of a task.
 */
public class ListCommand extends Command {

    private static final String USAGE = "-> Listing/Filtering your tasklist: list [--flag value] [--flag value] ...\n"
            + "-> Listing a recurrence of a task: list <index>";

    public ListCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    //@@author APZH
    /**
     * Executes the list command.
     * Stores the message to be displayed to the user into {@code message}
     * and to be passed as an input parameter of the {@code CommandResult} object to be returned.
     *
     * @return The command result of the execution.
     */
    @Override
    public CommandResult executeCommand() {
        String message = "";
        boolean containsMainArgument = commandArguments.containsKey(MAIN_ARGUMENT);

        try {
            taskManager.refreshListDates();
            if (!containsMainArgument || (containsMainArgument && commandArguments.get(MAIN_ARGUMENT).equals(""))) {
                message = taskManager.listTasklistWithFilter(commandArguments);
            } else if (containsMainArgument) {
                message = taskManager.listTaskRecurrence(commandArguments);
            }
        } catch (EmptyTasklistException ete) {
            message = ete.toString();
        } catch (ListFormatException lfe) {
            message = lfe.toString();
        } catch (MissingFilterArgumentException mfae) {
            message = mfae.toString();
        } catch (InvalidTaskIndexException itie) {
            message = itie.toString();
        } catch (InvalidTaskTypeException itte) {
            message = itte.toString();
        } catch (InvalidPriorityException ipe) {
            message = ipe.toString();
        } catch (InvalidRecurrenceException ire) {
            message = ire.toString();
        } catch (TaskIsNonRecurringException tnrx) {
            message = tnrx.toString();
        }
        return new CommandResult(message, false);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }
}
