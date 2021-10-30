package seedu.duke.logic.parser;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.commons.core.exceptions.DayOfTheWeekException;
import seedu.duke.commons.core.exceptions.PriorityException;
import seedu.duke.commons.core.Priority;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.module.AddModuleCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.logic.parser.ParserUtil.hasCorrectFlagSequence;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author richwill28
public class AddCommandParser {

    public static final String LESSON_FLAGS = " -d | -s | -e | -l ";
    public static final String TASK_FLAGS = " -d | -p | -i ";
    public static final String DEFAULT_PRIORITY = "LOW";
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
        String dayOfTheWeek = parseDayOfTheWeek(params[1]);

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
            return parseAddLessonFourParams(userResponse, title, dayOfTheWeek, startTime, endTime);
        case 5:
            return parseAddLessonFiveParams(userResponse, params[4], title, dayOfTheWeek, startTime, endTime);
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseAddLessonFiveParams(String userResponse, String param, String title,
                                                    String dayOfTheWeek, String startTime, String endTime)
            throws ParseException {
        String meetingUrl;
        if (!hasCorrectFlagSequence(userResponse, "-d", "-s", "-e", "-l")) {
            throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
        }
        meetingUrl = param.strip();
        return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, meetingUrl);
    }

    private static AddLessonCommand parseAddLessonFourParams(String userResponse, String title, String dayOfTheWeek,
                                                             String startTime, String endTime) throws ParseException {
        if (!hasCorrectFlagSequence(userResponse, "-d", "-s", "-e")) {
            throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
        }
        return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, EMPTY_INFORMATION);
    }

    private static Command parseAddTaskCommand(String userResponse) throws ParseException {
        String[] params = userResponse.split(TASK_FLAGS);
        checkParamsLength(params, 2, 4);

        String title = params[0].strip();
        String dayOfTheWeek = parseDayOfTheWeek(params[1]);

        switch (params.length) {
        case 2:
            return new AddTaskCommand(title, dayOfTheWeek, DEFAULT_PRIORITY, EMPTY_INFORMATION);
        case 3:
            return parseAddTaskThreeParams(userResponse, params, title, dayOfTheWeek);
        case 4:
            return parseAddTaskFourParams(userResponse, params, title, dayOfTheWeek);
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static AddTaskCommand parseAddTaskFourParams(String userResponse, String[] params, String title,
                                                         String dayOfTheWeek) throws ParseException {
        String information;
        String priority;
        if (!hasCorrectFlagSequence(userResponse, "-d", "-p", "-i")) {
            throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
        }

        try {
            priority = Priority.toProper(params[2].strip());
        } catch (PriorityException e) {
            throw new ParseException(e.getMessage());
        }

        information = params[3].strip();
        return new AddTaskCommand(title, dayOfTheWeek, priority, information);
    }

    private static AddTaskCommand parseAddTaskThreeParams(String userResponse, String[] params, String title,
                                                          String dayOfTheWeek) throws ParseException {
        String priority = DEFAULT_PRIORITY;
        String information = EMPTY_INFORMATION;
        if (userResponse.contains("-p")) {
            if (!hasCorrectFlagSequence(userResponse, "-d", "-p")) {
                throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
            }

            try {
                priority = Priority.toProper(params[2].strip());
            } catch (PriorityException e) {
                throw new ParseException(e.getMessage());
            }
        } else if (userResponse.contains("-i")) {
            if (!hasCorrectFlagSequence(userResponse, "-d", "-i")) {
                throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
            }

            information = params[2].strip();
        } else {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        return new AddTaskCommand(title, dayOfTheWeek, priority, information);
    }

    /**
     * Checks if the number of items in an array of Strings is within a certain range.
     *
     * @param params the array of Strings
     * @param min the lower bound of the range
     * @param max the upper bound of the range
     * @throws ParseException when the number of items is out of the given range
     */
    private static void checkParamsLength(String[] params, int min, int max) throws ParseException {
        if (params.length < min || params.length > max) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static String parseDayOfTheWeek(String param) throws ParseException {
        String dayOfTheWeek;
        try {
            dayOfTheWeek = DayOfTheWeek.toProper(param.strip());
        } catch (DayOfTheWeekException e) {
            throw new ParseException(e.getMessage());
        }
        return dayOfTheWeek;
    }

    //@@author ptejasv
    public static Command parseAddModuleCommand(String userResponse) {
        String moduleCode = userResponse.strip().toUpperCase();
        return new AddModuleCommand(moduleCode);
    }
}
