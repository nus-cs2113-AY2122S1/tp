package seedu.duke.logic.parser;

import java.time.LocalTime;
import java.util.HashMap;

import seedu.duke.commons.core.CommandFlag;
import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Message;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.module.AddModuleCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.ADD_LESSON_FORMAT;
import static seedu.duke.commons.core.CommandFormat.ADD_MODULE_FORMAT;
import static seedu.duke.commons.core.CommandFormat.ADD_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.commons.core.Priority.LOW;
import static seedu.duke.commons.util.StringUtil.removeFirstParam;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseDayOfTheWeek;
import static seedu.duke.logic.parser.ParserUtil.parseModuleCode;
import static seedu.duke.logic.parser.ParserUtil.parseModuleGrade;
import static seedu.duke.logic.parser.ParserUtil.parsePriority;
import static seedu.duke.logic.parser.ParserUtil.parseTime;
import static seedu.duke.logic.parser.ParserUtil.parseTitle;

//@@author richwill28
public class AddCommandParser {
    public static final String DEFAULT_GRADE = "NONE";
    public static final String DEFAULT_INFORMATION = "-";
    public static final String DEFAULT_PRIORITY = LOW.toString();
    public static final String DEFAULT_URL = "-";

    public static Command parse(String userResponse) throws ParseException {
        if (userResponse.contains("|")) {
            userResponse = userResponse.replace("|", "/");
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
        HashMap<String, String> flagMap = ParserUtil.getFlagMap(userResponse,
                CommandFlag.DAY, CommandFlag.START, CommandFlag.END, CommandFlag.LINK);

        if (!flagMap.containsKey(CommandFlag.DAY)
                || !flagMap.containsKey(CommandFlag.START)
                || !flagMap.containsKey(CommandFlag.END)) {
            throw new ParseException(Message.ERROR_MISSING_FLAGS);
        }

        String[] params = userResponse.split(CommandFlag.LESSON);
        String title = parseTitle(params[0]);
        String dayOfTheWeek = parseDayOfTheWeek(flagMap.get(CommandFlag.DAY));
        String startTime = parseTime(flagMap.get(CommandFlag.START));
        String endTime = parseTime(flagMap.get(CommandFlag.END));
        String meetingUrl = DEFAULT_URL;

        if (flagMap.containsKey(CommandFlag.LINK)) {
            meetingUrl = flagMap.get(CommandFlag.LINK).strip();
        }

        if (LocalTime.parse(startTime).isAfter(LocalTime.parse(endTime))) {
            throw new ParseException(Message.ERROR_INVALID_TIME_SEQUENCE);
        }

        return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, meetingUrl);
    }

    private static Command parseAddTaskCommand(String userResponse) throws ParseException {
        HashMap<String, String> flagMap = ParserUtil.getFlagMap(userResponse,
                CommandFlag.DAY, CommandFlag.PRIORITY, CommandFlag.INFORMATION);

        if (!flagMap.containsKey(CommandFlag.DAY)) {
            throw new ParseException(Message.ERROR_MISSING_FLAGS);
        }

        String[] params = userResponse.split(CommandFlag.TASK);
        String title = parseTitle(params[0]);
        String dayOfTheWeek = parseDayOfTheWeek(flagMap.get(CommandFlag.DAY));
        String priority = DEFAULT_PRIORITY;
        String information = DEFAULT_INFORMATION;

        if (flagMap.containsKey(CommandFlag.PRIORITY)) {
            priority = parsePriority(flagMap.get(CommandFlag.PRIORITY));
        }
        if (flagMap.containsKey(CommandFlag.INFORMATION)) {
            information = flagMap.get(CommandFlag.INFORMATION).strip();
        }

        return new AddTaskCommand(title, dayOfTheWeek, priority, information);
    }

    //@@author richwill28
    public static Command parseAddModuleCommand(String userResponse) throws ParseException {
        HashMap<String, String> flagMap = ParserUtil.getFlagMap(userResponse, CommandFlag.GRADE);

        String[] params = userResponse.split(CommandFlag.MODULE);
        String moduleCode = parseModuleCode(params[0]);
        String moduleGrade = DEFAULT_GRADE;

        if (flagMap.containsKey(CommandFlag.GRADE)) {
            moduleGrade = parseModuleGrade(flagMap.get(CommandFlag.GRADE));
        }

        return new AddModuleCommand(moduleCode, moduleGrade);
    }
}
