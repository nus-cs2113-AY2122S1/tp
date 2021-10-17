package seedu.duke.commands;

import seedu.duke.Ui;
import seedu.duke.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.Duke.taskList;

public class DeleteCommand extends Command {

    protected static final String TASK_OR_EVENT_FLAG = "-";

    // input from user
    public String taskToDelete;
    public static ArrayList<Item> combinedItemList = new ArrayList<>();

    // v1.0: deleteCommand deletes purely based on index, i.e. delete [TASK_INDEX]
    // converted command array to string for future uses, where input may have extra spaces
    // such as 'delete foo bar'
    public DeleteCommand(String[] itemDescription) {
        taskToDelete = Arrays.toString(itemDescription);
    }

    public CommandResult execute() {

        fillCombinedItemList();

        String removedTaskTitle;
        removedTaskTitle = deleteTask(taskToDelete);
        return new CommandResult(Ui.getTaskDeletionMessage(removedTaskTitle));
    }

    public static void fillCombinedItemList() {
        combinedItemList.clear();
        combinedItemList.addAll(eventCatalog);
        combinedItemList.addAll(taskList);
    }

    // did not throw a custom exception if taskIndex > itemList.size(), already caught by ArrayIndexOutOfBoundsException
    public static String deleteTask(String input) {
        try {
            int taskIndex = getTaskIndex(input);
            String taskTitle = combinedItemList.get(taskIndex).getTitle();
            combinedItemList.remove(taskIndex);
            return taskTitle;
        } catch (ArrayIndexOutOfBoundsException e) {
            return "That task does not exist";
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Please tell me which task number to delete";
        }
    }

    public static int getTaskIndex(String command) {
        // command will contain either a "-t" or "-e"
        int taskIndexPosition = command.trim().indexOf(TASK_OR_EVENT_FLAG) + 2;
        return Integer.parseInt(command.trim().substring(taskIndexPosition)) - 1;
    }
}
