package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.task.DoneTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseToZeroIndex;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author rebchua39
public class DoneCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseDoneTaskCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseDoneTaskCommand(String userResponse) throws ParseException {
        // TODO: Implement batch marking
        try {
            int taskIndex = parseToZeroIndex(Integer.parseInt(userResponse));
            return new DoneTaskCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(Messages.ERROR_INVALID_NUMBER);
        }
    }
}
