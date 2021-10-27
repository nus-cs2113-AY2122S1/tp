package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;
import seedu.duke.parser.Parser;

public class SelectCommand extends Command {

    protected static final String EVENT_FLAG = "-e";
    protected static final String TASK_FLAG = "-t";

    private String itemFlag;
    private int eventIndexToSelect;
    private int taskIndexToSelect;

    private static boolean isCorrectFormat;

    public SelectCommand(String[] command) {
        isCorrectFormat = true;
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
            isCorrectFormat = false;
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number for the item index!");
            isCorrectFormat = false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No such item index exists!");
            isCorrectFormat = false;
        }
    }

    public CommandResult execute() {
        if (!isCorrectFormat) {
            return new CommandResult("Try again!");
        }

        Item selectedItem;
        if (isEventFlag(itemFlag)) {
            selectedItem = getEventFromIndex(eventIndexToSelect);
            return new CommandResult(Ui.getSelectedEventMessage((Event) selectedItem));
        } else if (isTaskFlag(itemFlag)) {
            if (Parser.getIndexOfLastSelectedEvent() == -1) {
                return new CommandResult("Please select an event first!");
            }
            eventIndexToSelect = Parser.getIndexOfLastSelectedEvent();
            selectedItem = getTaskFromEventIndex(eventIndexToSelect, taskIndexToSelect);
            return new CommandResult(Ui.getSelectedTaskMessage((Task) selectedItem));
        }
        return new CommandResult("Insert something here.");
    }

    private void prepareInputs(String[] command) throws DukeException {
        if (command.length == 2) {
            throw new DukeException("Please give me the index of the event you wish to select!");
        }
        if (!isValidFlag(itemFlag)) {
            throw new DukeException("Invalid item flag!");
        }
        int index = getIndexFromCommand(command[2]);
        if (isEventFlag(itemFlag)) {
            if (!isValidEventIndex(index)) {
                throw new DukeException("Invalid event index!");
            }
            eventIndexToSelect = index;
            Parser.updateIndexOfLastSelectedEvent(index);
        } else {
            if (Parser.noEventSelected()) {
                throw new DukeException("Select an event first!");
            }
            if (!isValidTaskIndex(Parser.getIndexOfLastSelectedEvent(), index)) {
                throw new DukeException("Invalid task index!");
            }
            taskIndexToSelect = index;
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

    private static boolean isValidEventIndex(int eventIndex) {
        return eventIndex >= 0 && eventIndex <= Duke.eventCatalog.size();
    }

    private static boolean isValidTaskIndex(int eventIndex, int taskIndex) {
        return taskIndex >= 0 && taskIndex <= Duke.eventCatalog.get(eventIndex).getTaskList().size();
    }

    private static Event getEventFromIndex(int eventIndex) {
        return Duke.eventCatalog.get(eventIndex);
    }

    private Task getTaskFromEventIndex(int eventIndex, int taskIndex) {
        return Duke.eventCatalog.get(eventIndex).getFromTaskList(taskIndex);
    }
}
