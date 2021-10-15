package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.DayOfTheWeek.is;
import static seedu.duke.logic.parser.ParserUtil.hasCorrectFlagSequence;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

public class AddCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case LESSON:
            simplifiedUserResponse = removeFirstParam(userResponse, "lesson");
            return parseAddLessonCommand(simplifiedUserResponse);
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseAddTaskCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseAddLessonCommand(String userResponse) throws ParseException {
        String[] params = userResponse.split(" -d | -s | -e ");
        if (params.length != 4) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        if (!hasCorrectFlagSequence(userResponse, "-d", "-s", "-e")) {
            throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
        }

        String title = params[0].strip();
        String dayOfTheWeek = params[1].strip();
        if (!is(dayOfTheWeek)) {
            throw new ParseException(dayOfTheWeek + Messages.ERROR_INVALID_DAY);
        }

        String startTIme = params[2].strip();       // TODO: Validate correctness with time library
        String endTime = params[3].strip();         // TODO: Validate correctness with time library
        return new AddLessonCommand(title, dayOfTheWeek, startTIme, endTime);
    }

    private static Command parseAddTaskCommand(String userResponse) throws ParseException {
        String[] params = userResponse.split(" -d | -i ");
        if (params.length < 2 || params.length > 3) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        String title = params[0].strip();
        String dayOfTheWeek = params[1].strip();
        if (!is(dayOfTheWeek)) {
            throw new ParseException(dayOfTheWeek + Messages.ERROR_INVALID_DAY);
        }

        switch (params.length) {
        case 2:
            return new AddTaskCommand(title, dayOfTheWeek, "");
        case 3:
            if (!hasCorrectFlagSequence(userResponse, "-d", "-i")) {
                throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
            }
            String information = params[2].strip();
            return new AddTaskCommand(title, dayOfTheWeek, information);
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }
}
