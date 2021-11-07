package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.parserexceptions.InvalidIndexException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.parser.ItemType.EVENT;
import static seedu.duke.parser.ItemType.TASK;

public abstract class DeleteParser extends Parser {

    private static int eventIndexToDelete;
    private static int taskIndexToDelete;

    public static Command getDeleteCommand(String[] command, String commandDetails) {
        try {
            ItemType itemType = getItemType(commandDetails);
            switch (itemType) {
            case EVENT:
                parseEvent(command);
                return new DeleteCommand(EVENT, eventIndexToDelete);
            case TASK:
                parseTask(command);
                return new DeleteCommand(TASK, taskIndexToDelete);
            case MEMBER:
                break;
            default:
                throw new InvalidItemTypeException();
            }
        } catch (InvalidItemTypeException e) {
            System.out.println("Please indicate a flag:\n" + "-e for Event\n" + "-t for Task\n"
                    + "-m for Member");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number for the item index!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select an index!");
        } catch (InvalidIndexException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void parseEvent(String[] command) throws InvalidIndexException {
        int eventIndex = getIndexFromCommand(command[2]);
        if (!isValidEventIndex(eventIndex)) {
            throw new InvalidIndexException("Invalid index range. Choose a number between 1 and "
                    + eventCatalog.size() + ".");
        }
        eventIndexToDelete = eventIndex;
    }

    private static void parseTask(String[] command) throws InvalidIndexException {
        int taskIndex = getIndexFromCommand(command[2]);
        int lastEventIndex = Parser.getIndexOfLastSelectedEvent();
        if (!isValidTaskIndex(lastEventIndex, taskIndex)) {
            throw new InvalidIndexException("Invalid index range. Choose a number between 0 to "
                    + eventCatalog.get(lastEventIndex).getTaskList().size() + ".");
        }
        taskIndexToDelete = taskIndex;
    }

    private static int getIndexFromCommand(String indexAsString) throws InvalidIndexException {
        return Integer.parseInt(indexAsString.trim()) - 1;
    }

    private static boolean isValidEventIndex(int eventIndex) {
        return eventIndex >= 0 && eventIndex < Duke.eventCatalog.size();
    }

    private static boolean isValidTaskIndex(int eventIndex, int taskIndex) {
        return taskIndex >= 0 && taskIndex < Duke.eventCatalog.get(eventIndex).getTaskList().size();
    }

    private boolean isDeleteAll(String[] command) {
        if (command.length == 2) {
            return (command[1].trim().equalsIgnoreCase("all"));
        }
        return false;
    }
}
