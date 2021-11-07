package seedu.duke.parser.commandparser;


import seedu.duke.commands.Command;
import seedu.duke.commands.FindCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.parserexceptions.InvalidItemTypeException;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;


import static seedu.duke.parser.ItemType.EVENT;

public abstract class FindParser extends Parser {

    private static String keywords;

    public static Command getFindCommand(String[] command, String commandDetails) {
        try {
            ItemType itemType = getItemType(commandDetails);
            if (itemType == EVENT) {
                parseFindKeyword(command);
                return new FindCommand(keywords);
            }
            throw new InvalidItemTypeException();
        } catch (InvalidItemTypeException e) {
            System.out.println("Please add -e to find event(s)!");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void parseFindKeyword(String[] command) throws DukeException {
        keywords = extractKeywords(command);
    }

    private static String extractKeywords(String[] command) throws DukeException {
        if (command.length < 3) {
            throw new DukeException("Please specify what Events you wish to find!");
        }
        StringBuilder keyword = new StringBuilder();
        for (int i = 2; i < command.length; i++) {
            keyword.append(command[i].trim());
            keyword.append(" ");
        }
        return keyword.toString().trim();
    }

}
