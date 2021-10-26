package seedu.duke.command;

import java.util.Map;
import java.util.TreeSet;
import seedu.duke.exception.EmptyTasklistException;
import seedu.duke.exception.InvalidTaskIndexException;
import seedu.duke.local.DataManager;
import seedu.duke.task.Task;
import seedu.duke.task.TaskManager;
import seedu.duke.utility.Utility;

//@@author SeanRobertDH
public class DeleteCommand extends Command {
    private static final CommandEnum COMMAND = CommandEnum.DELETE;

    private static final String TASK_DELETED = "Tasks deleted:\n";
    private static final String INVALID_TASK_INDEX = "%s is not an integer!";

    private static final String SPACE_REGEX = "[\\s|_]+";
    private static final String SEPARATOR = ",";
    private static final String LIST_NUMBERS = "-";


    public DeleteCommand(Map<String, String> commandArguments) {
        super(COMMAND, commandArguments);
    }

    @Override
    public CommandResult executeCommand() throws Exception {
        String message = TASK_DELETED;
        try {
            String mainArgument = getMainArgument();
            if (mainArgument == null) {
                throw new NullPointerException();
            }
            if (TaskManager.isEmpty()) {
                throw new EmptyTasklistException();
            }
            String[] taskIndexStrings = splitIndexesString(mainArgument);
            TreeSet<Integer> tasksToDelete = getTasksToDelete(taskIndexStrings);
            message += deleteTasks(tasksToDelete);
        } catch (NullPointerException npe) {
            message = getUsage();
        } catch (EmptyTasklistException etle) {
            message = etle.getMessage();
        } catch (NumberFormatException nfe) {
            message = nfe.getMessage();
        } catch (InvalidTaskIndexException itie) {
            message = itie.getMessage();
        }
        return new CommandResult(message, true, false);
    }

    private String[] splitIndexesString(String argument) {
        String parsedArgument = argument.replaceAll(SPACE_REGEX, "");
        return parsedArgument.split(SEPARATOR);
    }

    private TreeSet<Integer> getTasksToDelete(String[] indexStrings) throws NumberFormatException {
        TreeSet<Integer> indexes = new TreeSet<>();
        for (String indexString : indexStrings) {
            if (indexString.contains(LIST_NUMBERS)) {
                String[] listIndexes = indexString.split(LIST_NUMBERS, 2);
                Integer startIndex = parseIndex(listIndexes[0]);
                Integer endIndex = parseIndex(listIndexes[1]);
                for (Integer index = startIndex; index <= endIndex; index++) {
                    indexes.add(index);
                }
            } else {
                indexes.add(parseIndex(indexString));
            }
        }
        return indexes;
    }

    private String deleteTasks(TreeSet<Integer> indexes) throws InvalidTaskIndexException {
        String message = "";
        int offset = 0;
        TaskManager.checkIndexValid(indexes.first() - 1);
        TaskManager.checkIndexValid(indexes.last() - 1);
        for (Integer index : indexes) {
            Task deletedTask = TaskManager.deleteTask(index - 1 - offset++);
            DataManager.deleteTask(index - 1 - offset);
            message += deletedTask.getTaskEntryDescription() + '\n';
        }
        return message;
    }

    private Integer parseIndex(String index) throws NumberFormatException {
        if (!Utility.isInteger(index)) {
            throw new NumberFormatException(String.format(INVALID_TASK_INDEX, index));
        }
        return Integer.parseInt(index);
    }
}
