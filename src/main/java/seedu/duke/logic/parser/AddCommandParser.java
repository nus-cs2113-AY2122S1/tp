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
        String[] params = userResponse.split(" -d | -s | -e | -l ");
        if (params.length < 4 || params.length > 5) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        String title = params[0].strip();

        String dayOfTheWeek;
        try {
            dayOfTheWeek = DayOfTheWeek.toProper(params[1].strip());
        } catch (DayOfTheWeekException e) {
            throw new ParseException(e.getMessage());
        }

        String startTime;
        String endTime;
        try {
            startTime = LocalTime.parse(params[2].strip()).format(DateTimeFormatter.ofPattern("hh:mm a"));
            endTime = LocalTime.parse(params[3].strip()).format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }

        String meetingUrl = "-";
        switch (params.length) {
        case 4:
            if (!hasCorrectFlagSequence(userResponse, "-d", "-s", "-e")) {
                throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
            }
            return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, meetingUrl);
        case 5:
            if (!hasCorrectFlagSequence(userResponse, "-d", "-s", "-e", "-l")) {
                throw new ParseException(Messages.ERROR_INVALID_FLAG_SEQUENCE);
            }
            meetingUrl = params[4].strip();
            return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, meetingUrl);
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    private static Command parseAddTaskCommand(String userResponse) throws ParseException {
        String[] params = userResponse.split(" -d | -p | -i");
        if (params.length < 2 || params.length > 4) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }

        String title = params[0].strip();

        String dayOfTheWeek;
        try {
            dayOfTheWeek = DayOfTheWeek.toProper(params[1].strip());
        } catch (DayOfTheWeekException e) {
            throw new ParseException(e.getMessage());
        }

        String priority = "L";
        String information = "-";

        switch (params.length) {
        case 2:
            return new AddTaskCommand(title, dayOfTheWeek, priority, information);
        case 3:
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
        case 4:
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
