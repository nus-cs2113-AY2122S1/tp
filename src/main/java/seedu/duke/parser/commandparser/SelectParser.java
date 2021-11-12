package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.commands.Command;
import seedu.duke.commands.SelectCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidIndexException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

import static seedu.duke.Duke.eventCatalog;
import static seedu.duke.parser.ItemType.MEMBER;
import static seedu.duke.parser.ItemType.EVENT;
import static seedu.duke.parser.ItemType.TASK;

public abstract class SelectParser extends Parser {

    private static int eventIndexToSelect;
    private static int taskIndexToSelect;
    private static int lastEventIndex;
    private static int memberIndexToSelect;

    public static Command getSelectCommand(String[] command, String commandDetails) {

        try {
            ItemType itemType = getItemType(commandDetails);
            switch (itemType) {
            case EVENT:
                parseEvent(command);
                return new SelectCommand(EVENT, eventIndexToSelect);
            case TASK:
                parseTask(command);
                return new SelectCommand(TASK, lastEventIndex, taskIndexToSelect);
            case MEMBER:
                parseMember(command);
                return new SelectCommand(MEMBER, memberIndexToSelect);
            default:
                throw new InvalidItemTypeException();
            }
        } catch (InvalidItemTypeException e) {
            System.out.println("Please indicate a flag:\n" + "-e for Event\n" + "-t for Task\n"
                    + "-m for Member");
        } catch (InvalidIndexException | DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number for the item index!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please select an index!");
        }
        return null;
    }

    private static void parseEvent(String[] command) throws InvalidIndexException {
        int index = getIndexFromCommand(command[2]);
        if (!isValidEventIndex(index)) {
            throw new InvalidIndexException("Invalid index range. Choose a number between 1 and "
                    + eventCatalog.size() + ".");
        }
        updateIndexOfLastSelectedEvent(index);
        eventIndexToSelect = index;
    }

    private static void parseTask(String[] command) throws InvalidIndexException, DukeException {
        if (Parser.noEventSelected()) {
            throw new DukeException("Please select an event first!");
        }
        lastEventIndex = getIndexOfLastSelectedEvent();
        int index = getIndexFromCommand(command[2]);
        if (!isValidTaskIndex(lastEventIndex, index)) {
            throw new InvalidIndexException("Invalid index range. Choose a number between 1 to "
                    + eventCatalog.get(lastEventIndex).getTaskList().size() + ".");
        }
        taskIndexToSelect = index;
    }

    private static void parseMember(String[] command) throws DukeException {
        String memberName = getMemberNameFromCommand(command);
        memberIndexToSelect = getMemberIndexFromQuery(memberName);
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
}
