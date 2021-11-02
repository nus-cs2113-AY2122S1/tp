package seedu.duke.logic.parser;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.module.AddModuleCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.ADD_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormat.ADD_LESSON_FORMAT;
import static seedu.duke.commons.core.CommandFormat.ADD_MODULE_FORMAT;
import static seedu.duke.commons.core.Priority.LOW;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parsePriority;
import static seedu.duke.logic.parser.ParserUtil.parseDayOfTheWeek;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author richwill28
public class AddCommandParser {
    public static final String LESSON_FLAGS = "-d |-s |-e |-l ";
    public static final String TASK_FLAGS = "-d |-p |-i ";
    public static final String DEFAULT_PRIORITY = LOW.toString();
    public static final String EMPTY_INFORMATION = "-";

    public static Command parse(String userResponse) throws ParseException {
        if (userResponse.contains("|")) {
            userResponse = userResponse.replace("|","/");
        }

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
            throw new ParseException(promptFormat(ADD_TASK_FORMAT, ADD_LESSON_FORMAT, ADD_MODULE_FORMAT));
        }
    }

    //@@author Roycius
    private static Command parseAddLessonCommand(String userResponse) throws ParseException {
        HashMap<String, String> flagMap = ParserUtil.getFlagMap(userResponse, "-d", "-s", "-e", "-l");
        String[] params = userResponse.split(LESSON_FLAGS);
        if (!flagMap.containsKey("-d") || !flagMap.containsKey("-s") || !flagMap.containsKey("-e")) {
            throw new ParseException(Messages.ERROR_MISSING_FLAGS);
        }
        String title = params[0].strip();
        String dayOfTheWeek = parseDayOfTheWeek(flagMap.get("-d"));
        String startTime;
        String endTime;
        try {
            startTime = LocalTime.parse(flagMap.get("-s")).format(DateTimeFormatter.ofPattern("hh:mm a"));
            endTime = LocalTime.parse(flagMap.get("-e")).format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new ParseException(Messages.ERROR_INVALID_TIME_FORMAT);
        }
        if (!flagMap.containsKey("-l")) {
            return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, EMPTY_INFORMATION);
        }
        return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, flagMap.get("-l"));
    }

    private static Command parseAddTaskCommand(String userResponse) throws ParseException {
        HashMap<String, String> flagMap = ParserUtil.getFlagMap(userResponse, "-d", "-p", "-i");
        String[] params = userResponse.split(TASK_FLAGS);
        if (!flagMap.containsKey("-d")) {
            throw new ParseException(Messages.ERROR_MISSING_FLAGS);
        }
        String title = params[0].strip();
        String dayOfTheWeek = parseDayOfTheWeek(flagMap.get("-d"));
        String priority = DEFAULT_PRIORITY;
        String information = EMPTY_INFORMATION;

        if (flagMap.containsKey("-p")) {
            priority = parsePriority(flagMap.get("-p"));
        }
        if (flagMap.containsKey("-i")) {
            information = flagMap.get("-i");
        }
        return new AddTaskCommand(title, dayOfTheWeek, priority, information);
    }

    //@@author ptejasv
    public static Command parseAddModuleCommand(String userResponse) {
        String moduleCode = userResponse.strip().toUpperCase();
        return new AddModuleCommand(moduleCode);
    }
}
