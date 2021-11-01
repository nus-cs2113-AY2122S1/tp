package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.commons.core.Messages;
import seedu.duke.commons.core.Priority;
import seedu.duke.commons.core.exceptions.DayOfTheWeekException;
import seedu.duke.commons.core.exceptions.PriorityException;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormats.ADD_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormats.promptFormat;
import static seedu.duke.logic.parser.AddCommandParser.DEFAULT_PRIORITY;
import static seedu.duke.logic.parser.AddCommandParser.EMPTY_INFORMATION;

//@@author richwill28
public class ParserUtil {
    public static boolean hasCorrectFlagSequence(String userResponse, String... flags) {
        for (int i = 0; i < flags.length - 1; i++) {
            int leftFlagIndex = userResponse.indexOf(flags[i]);
            int rightFlagIndex = userResponse.indexOf(flags[i + 1]);
            if (leftFlagIndex >= rightFlagIndex) {
                return false;
            }
        }
        return true;
    }

    public static CommandType parseCommandType(String userResponse) {
        String[] params = userResponse.split(" ", 2);
        return CommandType.of(params[0]);
    }

    public static int parseToOneIndex(int index) {
        return index + 1;
    }

    public static int parseToZeroIndex(int index) {
        return index - 1;
    }

    public static String removeFirstParam(String userResponse, String firstParam) {
        return userResponse.replaceFirst(firstParam, "").strip();
    }

    public static boolean isVerbose(String userResponse) {
        return userResponse.equalsIgnoreCase("verbose");
    }

    //@@author Roycius
    /**
     * Checks if the number of items in an array of Strings is within a certain range.
     *
     * @param params the array of Strings
     * @param min the lower bound of the range
     * @param max the upper bound of the range
     * @throws ParseException when the number of items is out of the given range
     */
    public static void checkParamsLength(String[] params, int min, int max) throws ParseException {
        if (params.length < min || params.length > max) {
            throw new ParseException(Messages.ERROR_INVALID_NUMBER_OF_PARAMS);
        }
    }

    public static String parseDayOfTheWeek(String userResponse, String param) throws ParseException {
        String dayOfTheWeek;
        if (!userResponse.contains("-d")) {
            throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
        }
        try {
            dayOfTheWeek = DayOfTheWeek.toProper(param.strip());
        } catch (DayOfTheWeekException e) {
            throw new ParseException(e.getMessage());
        }
        return dayOfTheWeek;
    }

    public static Command parseMeetingUrl(String userResponse, String title, String dayOfTheWeek,
                                           String startTime, String endTime, String meetingUrl, boolean hasMeetingUrl)
            throws ParseException {
        if ((hasMeetingUrl && !hasCorrectFlagSequence(userResponse, "-d", "-s", "-e", "-l"))
                || (!hasMeetingUrl && !hasCorrectFlagSequence(userResponse, "-d", "-s", "-e"))) {
            throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
        }
        return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, meetingUrl);
    }

    public static AddTaskCommand parsePriorityAndInfo(String userResponse, String[] params, String title,
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

    public static AddTaskCommand parsePriorityOrInfo(String userResponse, String[] params, String title,
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
            throw new ParseException(promptFormat(ADD_TASK_FORMAT));
        }

        return new AddTaskCommand(title, dayOfTheWeek, priority, information);
    }
}
