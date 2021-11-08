package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.commands.Command;
import seedu.duke.commands.DoneUndoCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidIndexException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import java.util.ArrayList;

public abstract class DoneUndoParser extends Parser {

    private static final String DONE = "done";
    private static final String UNDO = "undo";

    public static Command getDoneUndoCommand(String[] command, String commandDetails) {
        String action = command[0];
        assert action.equals(DONE) || action.equals(UNDO) : "Invalid action for command";
        try {
            if (command.length == 2) {
                throw new DukeException("Please add the index(es) of the item(s) you want to "
                        + (action.equals(DONE) ? "mark as done" : "undo") + ". ");
            }
            ItemType itemType = getItemType(commandDetails);
            ArrayList<Integer> indexes = getIndexes(commandDetails);
            checkForValidIndexes(itemType, indexes);
            switch (itemType) {
            case EVENT:
            case TASK:
                return new DoneUndoCommand(action, itemType, indexes);
            default:
                throw new InvalidItemTypeException();
            }
        } catch (InvalidItemTypeException e) {
            System.out.println("Having some trouble understanding what exactly you're trying to "
                    + (action.equals(DONE) ? "mark as done" : "undo") + "!\n"
                    + "TIP: Specify event '-e' or task '-t' after the '"
                    + (action.equals(DONE) ? "done" : "undo") + "' command.");
        } catch (DukeException | InvalidIndexException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please make sure you're only giving me numbers for the indexes!");
        }
        return null;
    }

    private static ArrayList<Integer> getIndexes(String commandDetails) throws DukeException {
        String indexesInString = commandDetails.split(" ", 2)[1];
        return extractIndexes(indexesInString);
    }

    private static void checkForValidIndexes(ItemType itemType, ArrayList<Integer> indexes)
            throws DukeException, InvalidIndexException {
        switch (itemType) {
        case EVENT:
            for (int index : indexes) {
                if (!isValidEventIndex(index)) {
                    throw new InvalidIndexException("One or more of these events do not exist. ");
                }
            }
            break;
        case TASK:
            for (int index : indexes) {
                if (!isValidTaskIndex(index)) {
                    throw new InvalidIndexException("One or more of these tasks do not exist. ");
                }
            }
            break;
        default:
            throw new DukeException("That's weird, the item is not an event or a task. ");
        }
    }

    private static boolean isValidEventIndex(int eventIndex) {
        return eventIndex >= 0 && eventIndex < Duke.eventCatalog.size();
    }

    private static boolean isValidTaskIndex(int taskIndex) throws DukeException {
        if (Parser.noEventSelected()) {
            throw new DukeException("Please select which event the task is under using the "
                    + "'select' command. ");
        }
        int eventIndex = getIndexOfLastSelectedEvent();
        return taskIndex >= 0 && taskIndex < Duke.eventCatalog.get(eventIndex).getTaskList().size();
    }
}
