package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidIndexException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.parser.ItemType.EVENT;
import static seedu.duke.parser.ItemType.TASK;
import static seedu.duke.parser.ItemType.MEMBER;

public abstract class DeleteParser extends Parser {

    private static int eventIndexToDelete;
    private static int taskIndexToDelete;
    private static int memberIndexToDelete;

    public static Command getDeleteCommand(String[] command, String commandDetails) {
        try {
            if (isDeleteAll(command)) {
                return new DeleteCommand();
            }
            ItemType itemType = getItemType(commandDetails);
            switch (itemType) {
            case EVENT:
                parseEvent(command);
                return new DeleteCommand(EVENT, eventIndexToDelete);
            case TASK:
                parseTask(command);
                return new DeleteCommand(TASK, taskIndexToDelete);
            case MEMBER:
                parseMember(command);
                return new DeleteCommand(MEMBER, memberIndexToDelete);
            default:
                throw new InvalidItemTypeException();
            }
        } catch (InvalidItemTypeException e) {
            System.out.println("Please indicate a flag:\n" + "-e for Event\n" + "-t for Task\n"
                    + "-m for Member");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number for the item index!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select an event first!");
        } catch (DukeException | InvalidIndexException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void parseEvent(String[] command) throws InvalidIndexException {
        int eventIndex = getIndexFromCommand(command[2]);
        if (!isValidEventIndex(eventIndex)) {
            if (eventCatalog.isEmpty()) {
                throw new InvalidIndexException("There are no events to delete.");
            }
            throw new InvalidIndexException("Invalid index range. Choose a number between 1 and "
                    + eventCatalog.size() + ".");
        }
        eventIndexToDelete = eventIndex;
    }

    private static void parseTask(String[] command) throws InvalidIndexException {
        int taskIndex = getIndexFromCommand(command[2]);
        int lastEventIndex = Parser.getIndexOfLastSelectedEvent();
        if (!isValidTaskIndex(lastEventIndex, taskIndex)) {
            if (eventCatalog.get(lastEventIndex).getTaskList().isEmpty()) {
                throw new InvalidIndexException("There are no tasks to delete.");
            }
            throw new InvalidIndexException("Invalid index range. Choose a number between 1 to "
                    + eventCatalog.get(lastEventIndex).getTaskList().size() + ".");
        }
        taskIndexToDelete = taskIndex;
    }

    private static void parseMember(String[] command) throws DukeException {
        String memberName = getMemberNameFromCommand(command);
        memberIndexToDelete = getMemberIndexFromQuery(memberName);
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

    private static String getMemberNameFromCommand(String[] command) throws DukeException {
        if (command.length < 3) {
            throw new DukeException("Please enter a name!");
        }
        StringBuilder memberNameQuery = new StringBuilder();
        for (int i = 2; i < command.length; i++) {
            memberNameQuery.append(command[i].trim());
            memberNameQuery.append(" ");
        }
        return memberNameQuery.toString().trim();
    }

    private static int getMemberIndexFromQuery(String memberName) throws DukeException {
        for (int i = 0; i < Duke.memberRoster.size(); i++) {
            Member member = Duke.memberRoster.get(i);
            if (member.getName().equalsIgnoreCase(memberName)) {
                return i;
            }
        }
        throw new DukeException("No matching names found!");
    }

    private static boolean isDeleteAll(String[] command) {
        if (command.length == 2) {
            return (command[1].trim().equalsIgnoreCase("all"));
        }
        return false;
    }
}
