package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

public class SelectCommand extends Command {

    protected static final String EVENT_FLAG = "-e";
    protected static final String TASK_FLAG = "-t";

    private String itemFlag;
    private int eventIndexToSelect;
    private int taskIndexToSelect;
    private boolean hasSelectedEvent;

    public SelectCommand(String[] command) {
        try {
            if (command.length == 1) {
                throw new DukeException("Please specify what you wish to select.");
            }
            itemFlag = command[1].trim().toLowerCase();
            if (isValidFlag(itemFlag)) {
                prepareInputs(command);
            } else {
                throw new DukeException("Invalid item flag entered. Please specify event '-e' "
                        + "or task '-t'.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number for the item index!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No such item index exists!");
        }
    }

    public CommandResult execute() {
        Item selectedItem;
        if (isEventFlag(itemFlag)) {
            selectedItem = getEventFromIndex(eventIndexToSelect);
            return new CommandResult("These are the details of the event:\n" + Ui.getEvent((Event) selectedItem));
        }
        if (isTaskFlag(itemFlag) && hasSelectedEvent) {
            selectedItem = getTaskFromEventIndex(taskIndexToSelect);
            return new CommandResult("These are the details of the task:\n" + Ui.getTask((Task) selectedItem));
        } else if (isTaskFlag(itemFlag) && !hasSelectedEvent) {
            return new CommandResult("Please select an event first!");
        }
        return new CommandResult("Insert something here.");
    }

    private void prepareInputs(String[] command) throws DukeException {
        if (command.length == 2) {
            throw new DukeException("Please give me the index of the event you wish to delete!");
        }
        if (isValidFlag(itemFlag)) {
            int index = getIndexFromCommand(command[2]);
            if (isEventFlag(itemFlag)) {
                hasSelectedEvent = true;
                eventIndexToSelect = index;
            } else {
                hasSelectedEvent = false;
                taskIndexToSelect = index;
            }
        } else {
            throw new DukeException("Invalid item index!");
        }
    }

    private boolean isValidFlag(String flag) {
        return isTaskFlag(flag) || isEventFlag(flag);
    }

    private static boolean isTaskFlag(String flag) {
        return flag.trim().equalsIgnoreCase(TASK_FLAG);
    }

    private static boolean isEventFlag(String flag) {
        return flag.trim().equalsIgnoreCase(EVENT_FLAG);
    }

    private static int getIndexFromCommand(String indexAsString) {
        return Integer.parseInt(indexAsString.trim()) - 1;
    }

    private static Event getEventFromIndex(int eventIndex) {
        return Duke.eventCatalog.get(eventIndex);
    }

    private Task getTaskFromEventIndex(int taskIndex) {
        return Duke.eventCatalog.get(eventIndexToSelect).getTaskList().get(taskIndex);
    }
}
