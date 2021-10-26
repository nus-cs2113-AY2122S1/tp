package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.FindLessonCommand;
import seedu.duke.logic.commands.task.FindTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author richwill28
public class FindCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case LESSON:
            simplifiedUserResponse = removeFirstParam(userResponse, "lesson");
            return parseFindLessonCommand(simplifiedUserResponse);
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseFindTaskCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseFindLessonCommand(String userResponse) throws ParseException {
        if (userResponse.isBlank()) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        return new FindLessonCommand(userResponse.toLowerCase());
    }

    private static Command parseFindTaskCommand(String userResponse) throws ParseException {
        if (userResponse.isBlank()) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        return new FindTaskCommand(userResponse.toLowerCase());
    }
}
