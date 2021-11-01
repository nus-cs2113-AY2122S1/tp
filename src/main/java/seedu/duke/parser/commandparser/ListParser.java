package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ListCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

public abstract class ListParser extends Parser {

    public static Command getListCommand(String commandDetails, String response) {

        try {
            ItemType listType = getItemType(commandDetails);
            switch (listType) {
            case EVENT:
                return new ListCommand("event", -1, -1);
            case TASK:
                return parseListTask(response);
            case MEMBER:
                return parseListMember(response);
            default:
                System.out.println("please specify type for list "
                        + System.lineSeparator()
                        + "[list -e: to see overall events"
                        + System.lineSeparator()
                        + "list -t [EVENT_NUM]: to see tasks in an Event"
                        + System.lineSeparator()
                        + "list -m e/[Event Index] t/[Task Index] : to see members in a Task"
                        + System.lineSeparator()
                        + "[list -m: to see overall member roster");
            }
        } catch (NullPointerException | IndexOutOfBoundsException | NoCommandAttributesException e) {
            System.out.println("Please check through the format carefully");
            Ui.listUsageCommands();
        } catch (InvalidItemTypeException  e) {
            System.out.println("Please key in the correct list type value (-e, -t, -m)");;
        } catch (NumberFormatException  e) {
            System.out.println("Please key in an integer that exists for the selection");;
        }
        return null;
    }

    private static Command parseListMember(String response) throws NoCommandAttributesException
            , NumberFormatException {
        if ((response.trim().split(" +")).length == 2) {
            return new ListCommand("memberRoster", -1, -1);
        }
        String[] index = response.trim().split(" +");
        checkNoCommandAttributesException(index);
        String[] taskStringIndex = index[2].trim().split("/+");
        String[] eventStringIndex = index[3].trim().split("/+");
        int eventIndex = Integer.parseInt(taskStringIndex[1]) - 1;
        if (eventIndex >= Duke.eventCatalog.size()) {
            System.out.println("Event does not exist!");
            throw new NumberFormatException();
        }
        int taskIndex = Integer.parseInt(eventStringIndex[1]) - 1;
        if (taskIndex >= Duke.eventCatalog.get(eventIndex).getTaskList().size()) {
            System.out.println("Task does not exist!");
            throw new NumberFormatException();
        }
        return new ListCommand("member", eventIndex, taskIndex);
    }

    private static Command parseListTask(String response)
            throws NoCommandAttributesException, NumberFormatException {
        String[] index = response.trim().split(" +");
        checkNoCommandAttributesException(index);
        int eventIndex = Integer.parseInt(index[2]) - 1;
        return new ListCommand("task", eventIndex, -1);
    }

    private static void checkNoCommandAttributesException(String[] index) throws NoCommandAttributesException {
        if (index.length < 2) {
            throw new NoCommandAttributesException();
        }
    }
}
