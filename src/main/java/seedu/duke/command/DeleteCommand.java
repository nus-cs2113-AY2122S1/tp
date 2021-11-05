package seedu.duke.command;

import java.util.Map;
import java.util.TreeSet;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.InvalidTaskIndexException;
import seedu.duke.exception.ParseTaskIndexesFailedException;
import seedu.duke.exception.NoTasksSpecifiedException;
import seedu.duke.parser.CommandParser;
import seedu.duke.task.Task;
import seedu.duke.task.taskmanager.TaskManager;

//@@author SeanRobertDH
/**
 * Class for DeleteCommand. to be executed when removing tasks from {@link #taskManager}.
 */
public class DeleteCommand extends Command {

    private static final String TASK_DELETED = "Task(s) deleted:\n";
    private static final String USAGE = "-> Deleting a task: delete <index>";

    private static final String SPACE_REGEX = "[\\s|_]+";
    private static final String SEPARATOR = ",";
    private static final String LIST_NUMBERS = "-";

    /**
     * Constructs the DeleteCommand with the program {@link #taskManager}
     * and the <code>commandArguments</code> specified in Command.
     *
     * @param taskManager the program's {@link seedu.duke.task.taskmanager.TaskManager}.
     * @param commandArguments a <code>Map&lt;String, String&gt;</code>
     *     of <code>flags</code> to <code>flag values</code>.
     */
    public DeleteCommand(TaskManager taskManager, Map<String, String> commandArguments) {
        super(taskManager, commandArguments);
    }

    /**
     * Parses and deletes the tasks specified in {@link #getMainArgument()}.
     *
     * @return The message from the command in CommandResult.
     * @throws java.lang.Exception Any uncaught Exceptions.
     */
    @Override
    public CommandResult executeCommand() throws Exception {
        String message = TASK_DELETED;
        try {
            String mainArgument = getMainArgument();
            if (mainArgument == null || mainArgument.equals("")) {
                throw new NullPointerException();
            }
            if (taskManager.isEmpty()) {
                throw new EmptyTasklistException();
            }
            String[] taskIndexStrings = splitIndexesString(mainArgument);
            TreeSet<Integer> tasksToDelete = getTasksToDelete(taskIndexStrings);
            if (tasksToDelete.isEmpty()) {
                throw new NoTasksSpecifiedException(mainArgument);
            }
            message += deleteTasks(tasksToDelete);
        } catch (NullPointerException npe) {
            message = getUsageMessage();
        } catch (EmptyTasklistException etle) {
            message = etle.getMessage();
        } catch (NoTasksSpecifiedException ntse) {
            message = ntse.getMessage();
        } catch (NumberFormatException nfe) {
            message = nfe.getMessage();
        } catch (ParseTaskIndexesFailedException nie) {
            message = String.format(nie.getMessage(), getMainArgument());
        } catch (InvalidTaskIndexException itie) {
            message = itie.getMessage();
        }
        return new CommandResult(message, false);
    }

    @Override
    protected String getUsage() {
        return USAGE;
    }

    /**
     * Returns a <code>String[]</code> split by {@link #SEPARATOR} after having
     * its whitespace removed.
     *
     * @param argument <code>String</code> to split.
     * @return <code>String[]</code> split by {@link #SEPARATOR} With whitespace removed.
     */
    private String[] splitIndexesString(String argument) {
        String parsedArgument = argument.replaceAll(SPACE_REGEX, "");
        return parsedArgument.split(SEPARATOR);
    }

    private TreeSet<Integer> getTasksToDelete(String[] indexStrings) throws NumberFormatException,
            ParseTaskIndexesFailedException {
        TreeSet<Integer> indexes = new TreeSet<>();
        for (String indexString : indexStrings) {
            if (indexString.contains(LIST_NUMBERS)) {
                addListIndexes(indexes, indexString);
            } else {
                indexes.add(CommandParser.parseTaskIndex(indexString));
            }
        }
        return indexes;
    }

    /**
     * Takes in a String of 2 integers separated by a '-' and adds all integers in-between and inclusive
     * of the 2 integers into <code>indexes</code>.
     *
     * @param indexes The <code>TreeSet&lt;Integer&gt;</code> of integers corresponding to Tasks to be deleted.
     * @param indexString The <code>String</code> to be parsed for the indexes to add to <code>indexes</code>.
     * @throws seedu.duke.exception.ParseTaskIndexesFailedException If
     *     listIndexes[0] || listIndexes[1] is an empty <code>String</code>.
     */
    private void addListIndexes(TreeSet<Integer> indexes, String indexString) throws ParseTaskIndexesFailedException {
        String[] listIndexes = indexString.split(LIST_NUMBERS, 2);
        Integer startIndex = CommandParser.parseTaskIndex(listIndexes[0]);
        Integer endIndex = CommandParser.parseTaskIndex(listIndexes[1]);
        for (Integer index = startIndex; index <= endIndex; index++) {
            indexes.add(index);
        }
    }

    private String deleteTasks(TreeSet<Integer> indexes) throws InvalidTaskIndexException {
        taskManager.checkFilteredListIndexValid(indexes.first() - 1);
        taskManager.checkFilteredListIndexValid(indexes.last() - 1);
        int offset = 0;
        String message = "";
        for (Integer index : indexes) {
            Task deletedTask = taskManager.deleteFilteredTask(index - 1 - offset++);
            message += deletedTask.getTaskEntryDescription() + '\n';
        }
        return message;
    }
}
