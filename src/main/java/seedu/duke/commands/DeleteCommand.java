package seedu.duke.commands;

import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.Duke.taskList;

public class DeleteCommand extends Command {

    protected static final String EVENT_FLAG = "-e";
    protected static final String TASK_FLAG = "-t";

    // input from user
    private String itemFlag;
    private int indexToDelete;


    // v2.0: deleteCommand deletes purely based on index, i.e. delete -t/-e [TASK_INDEX]
    public DeleteCommand(String[] command) {
        try {
            if (command.length == 1) {
                throw new DukeException("Please specify what you would like to delete");
            }
            prepareInputs(command);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public CommandResult execute() {

        String deletedItem;
        if (isEventFlag(itemFlag)) {
            deletedItem = deleteEvent(indexToDelete);
        } else if (isTaskFlag(itemFlag)) {
            deletedItem = deleteTask(indexToDelete);
        } else {
            return new CommandResult("Insert something here");
        }
        return new CommandResult(deletedItem);
    }

    public void prepareInputs(String[] command) throws DukeException {
        try {
            if (command[1].isEmpty()) {
                throw new DukeException("Please enter a valid task or event flag!");
            }
            if (command[2].isEmpty()) {
                throw new DukeException("Please enter the index of the item you wish to delete!");
            }
            itemFlag = command[1].trim();
            indexToDelete = getIndex(command[2]);
        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number for the index!");
        }
    }

    public static String deleteEvent(int index) {
        String eventTitle = eventCatalog.get(index).getTitle();
        eventCatalog.remove(index);
        return eventTitle;
    }

    public static String deleteTask(int index) {
        String taskTitle = eventCatalog.get(index).getTitle();
        eventCatalog.remove(index);
        return taskTitle;
    }

    public static int getIndex(String indexAsString) {
        return Integer.parseInt(indexAsString.trim());
    }

    public static boolean isEventFlag(String flag) {
        return flag.trim().equalsIgnoreCase(EVENT_FLAG);
    }

    public static boolean isTaskFlag(String flag) {
        return flag.trim().equalsIgnoreCase(TASK_FLAG);
    }

    public static boolean isDeleteAll(String command) {
        return command.trim().equalsIgnoreCase("all");
    }
}

