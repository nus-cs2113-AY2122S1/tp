package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Message;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.LaunchLessonCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.LAUNCH_LESSON_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseToZeroIndex;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author richwill28
public class LaunchCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case LESSON:
            simplifiedUserResponse = removeFirstParam(userResponse, "lesson");
            return parseLaunchLessonCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(promptFormat(LAUNCH_LESSON_FORMAT));
        }
    }

    private static Command parseLaunchLessonCommand(String userResponse) throws ParseException {
        try {
            int lessonIndex = parseToZeroIndex(Integer.parseInt(userResponse));
            return new LaunchLessonCommand(lessonIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(Message.ERROR_INVALID_NUMBER);
        }
    }
}
