package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.commands.Command;
import seedu.duke.commands.SelectCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidIndexException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import static seedu.duke.Duke.eventCatalog;

public abstract class SelectParser extends Parser {

    private static int eventIndexToSelect;
    private static int taskIndexToSelect;
    private static int memberIndexToSelect;

    public static Command getSelectCommand(String[] command, String commandDetails) {

        try {
            ItemType itemType = getItemType(commandDetails);
            switch (itemType) {
            case EVENT:
                parseEvent(command);
                return new SelectCommand(itemType, eventIndexToSelect);
            case TASK:
                parseTask(command);
                return new SelectCommand(itemType, taskIndexToSelect);
            case MEMBER:
                return null;
            default:
                throw new InvalidItemTypeException();
            }
        } catch (InvalidItemTypeException | InvalidIndexException | DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number for the item index!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No such item index exists!");
        }
        return null;
    }

    private static void parseEvent(String[] command) throws InvalidIndexException {
        int index = getIndexFromCommand(command[2]);
        if (!isValidEventIndex(index)) {
            throw new InvalidIndexException("Invalid index range. Choose a number between 0 to "
                    + eventCatalog.size() +".");
        }
        updateIndexOfLastSelectedEvent(index);
        eventIndexToSelect = index;
    }

    private static void parseTask(String[] command) throws InvalidIndexException, DukeException {
        if (Parser.noEventSelected()) {
            throw new DukeException("Please select an event first!");
        }
        int indexOfLastEvent = getIndexOfLastSelectedEvent();
        int index = getIndexFromCommand(command[2]);
        if (!isValidTaskIndex(indexOfLastEvent, taskIndexToSelect)) {
            throw new InvalidIndexException("Invalid index range. Choose a number between 0 to "
                    + eventCatalog.get(indexOfLastEvent).getTaskList().size() +".");
        }
        taskIndexToSelect = index;
    }

    private void parseMember(String[] command) {
        // find member by string, return name and assigned tasks
    }

    private static int getIndexFromCommand(String indexAsString) {
        return Integer.parseInt(indexAsString.trim()) - 1;
    }

    private static boolean isValidEventIndex(int eventIndex) {
        return eventIndex >= 0 && eventIndex < Duke.eventCatalog.size();
    }

    private static boolean isValidTaskIndex(int eventIndex, int taskIndex) {
        return taskIndex >= 0 && taskIndex < Duke.eventCatalog.get(eventIndex).getTaskList().size();
    }
}
