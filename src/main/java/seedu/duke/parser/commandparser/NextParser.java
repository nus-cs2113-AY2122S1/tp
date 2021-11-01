package seedu.duke.parser.commandparser;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.Command;
import seedu.duke.commands.NextCommand;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.exceptions.parserexceptions.NoCommandAttributesException;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

public class NextParser extends Parser {
    public static Command getNextCommand(String commandDetails, String response) {
        try {
            ItemType listType = getItemType(commandDetails);
            switch (listType) {
            case EVENT:
                return new NextCommand("event", 0);
            case TASK:
                return parseListTask(response);
            default:
                Ui.printNextCommandErrorMessage();
            }
        } catch (NumberFormatException | NullPointerException | IndexOutOfBoundsException | NoCommandAttributesException e) {
            System.out.println("Please key in a valid Event Index!");
        } catch (InvalidItemTypeException e) {
            System.out.println("Please key in the correct next type value (-e, -t)");
            ;
        }
        return null;
    }
    private static Command parseListTask(String response)
            throws NoCommandAttributesException, NumberFormatException {
        String[] index = response.trim().split(" +");
        checkNoCommandAttributesException(index);
        int eventIndex = Integer.parseInt(index[2]) - 1;
        if (Duke.eventCatalog.get(eventIndex).getTaskList().size() == 0) {
            return new NextCommand("noTask", eventIndex);
        }
        return new NextCommand("task", eventIndex);
    }

    private static void checkNoCommandAttributesException(String[] index) throws NoCommandAttributesException {
        if (index.length < 2) {
            throw new NoCommandAttributesException();
        }
    }
}
