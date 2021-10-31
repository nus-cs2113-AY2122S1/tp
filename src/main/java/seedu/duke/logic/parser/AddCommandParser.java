package seedu.duke.logic.parser;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.commons.core.exceptions.PriorityException;
import seedu.duke.commons.core.Priority;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.module.AddModuleCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.ParserUtil.checkParamsLength;
import static seedu.duke.commons.core.Priority.LOW;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseDayOfTheWeek;
import static seedu.duke.logic.parser.ParserUtil.parseMeetingUrl;
import static seedu.duke.logic.parser.ParserUtil.parsePriorityAndInfo;
import static seedu.duke.logic.parser.ParserUtil.parsePriorityOrInfo;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author richwill28
public class AddCommandParser {
    public static final String LESSON_FLAGS = " -d | -s | -e | -l ";
    public static final String TASK_FLAGS = " -d | -p | -i ";
    public static final String DEFAULT_PRIORITY = LOW.toString();
    public static final String EMPTY_INFORMATION = "-";

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
        case MODULE:
            simplifiedUserResponse = removeFirstParam(userResponse, "module");
            return parseAddModuleCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    //@@author Roycius
    private static Command parseAddLessonCommand(String userResponse) throws ParseException {
        String[] params = userResponse.split(LESSON_FLAGS);
        checkParamsLength(params, 4, 5);

        String title = params[0].strip();
        String dayOfTheWeek = parseDayOfTheWeek(userResponse, params[1]);
        String startTime;
        String endTime;
        try {
            startTime = LocalTime.parse(params[2].strip()).format(DateTimeFormatter.ofPattern("hh:mm a"));
            endTime = LocalTime.parse(params[3].strip()).format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        switch (params.length) {
        case 4:
            return parseMeetingUrl(userResponse, title, dayOfTheWeek, startTime, endTime, EMPTY_INFORMATION, false);
        case 5:
            String url = params[4];
            return parseMeetingUrl(userResponse, title, dayOfTheWeek, startTime, endTime, url, true);
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseAddTaskCommand(String userResponse) throws ParseException {
        String[] params = userResponse.split(TASK_FLAGS);
        checkParamsLength(params, 2, 4);

        String title = params[0].strip();
        String dayOfTheWeek = parseDayOfTheWeek(userResponse, params[1]);

        switch (params.length) {
        case 2:
            return new AddTaskCommand(title, dayOfTheWeek, DEFAULT_PRIORITY, EMPTY_INFORMATION);
        case 3:
            return parsePriorityOrInfo(userResponse, params, title, dayOfTheWeek);
        case 4:
            return parsePriorityAndInfo(userResponse, params, title, dayOfTheWeek);
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    //@@author ptejasv
    public static Command parseAddModuleCommand(String userResponse) {
        String moduleCode = userResponse.strip().toUpperCase();
        return new AddModuleCommand(moduleCode);
    }
}
