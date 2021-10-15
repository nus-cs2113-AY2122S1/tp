package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.ListLessonCommand;
import seedu.duke.logic.commands.task.ListTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

public class ListCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case LESSON:
            simplifiedUserResponse = removeFirstParam(userResponse, "lesson");
            return parseListLessonCommand(simplifiedUserResponse);
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseListTaskCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseListLessonCommand(String userResponse) throws ParseException {
        // TODO: Validate today and tomorrow
        if (!userResponse.isBlank() && !DayOfTheWeek.is(userResponse)) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        return new ListLessonCommand(userResponse);
    }

    private static Command parseListTaskCommand(String userResponse) throws ParseException {
        // TODO: Validate today and tomorrow
        if (!userResponse.isBlank() && !DayOfTheWeek.is(userResponse)) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        return new ListTaskCommand(userResponse);
    }
}
