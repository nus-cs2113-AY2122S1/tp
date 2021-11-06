package seedu.duke.logic.parser;

import java.util.HashMap;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Message;
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
import static seedu.duke.logic.parser.ParserUtil.parseTime;
import static seedu.duke.logic.parser.ParserUtil.parseTitle;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author richwill28
public class AddCommandParser {
    public static final String LESSON_FLAG = "-d |-s |-e |-l ";
    public static final String TASK_FLAG = "-d |-p |-i ";
    public static final String DAY_FLAG = "-d";
    public static final String START_FLAG = "-s";
    public static final String END_FLAG = "-e";
    public static final String LINK_FLAG = "-l";
    public static final String PRIORITY_FLAG = "-p";
    public static final String INFORMATION_FLAG = "-i";

    public static final String DEFAULT_PRIORITY = LOW.toString();
    public static final String EMPTY_INFORMATION = "-";

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
        HashMap<String, String> flagMap =
                ParserUtil.getFlagMap(userResponse, DAY_FLAG, START_FLAG, END_FLAG, LINK_FLAG);
        String[] params = userResponse.split(LESSON_FLAG);
        if (!flagMap.containsKey(DAY_FLAG) || !flagMap.containsKey(START_FLAG) || !flagMap.containsKey(END_FLAG)) {
            throw new ParseException(Message.ERROR_MISSING_FLAGS);
        }

        String title = parseTitle(params[0]);
        String dayOfTheWeek = parseDayOfTheWeek(flagMap.get(DAY_FLAG));
        String startTime = parseTime(flagMap.get(START_FLAG));
        String endTime = parseTime(flagMap.get(END_FLAG));
        String meetingUrl = EMPTY_INFORMATION;

        if (flagMap.containsKey(LINK_FLAG)) {
            meetingUrl = flagMap.get(LINK_FLAG);
        }
        return new AddLessonCommand(title, dayOfTheWeek, startTime, endTime, meetingUrl);
    }

    private static Command parseAddTaskCommand(String userResponse) throws ParseException {
        HashMap<String, String> flagMap =
                ParserUtil.getFlagMap(userResponse, DAY_FLAG, PRIORITY_FLAG, INFORMATION_FLAG);
        String[] params = userResponse.split(TASK_FLAG);
        if (!flagMap.containsKey(DAY_FLAG)) {
            throw new ParseException(Message.ERROR_MISSING_FLAGS);
        }
        String title = parseTitle(params[0]);
        String dayOfTheWeek = parseDayOfTheWeek(flagMap.get(DAY_FLAG));
        String priority = DEFAULT_PRIORITY;
        String information = EMPTY_INFORMATION;

        if (flagMap.containsKey(PRIORITY_FLAG)) {
            priority = parsePriority(flagMap.get(PRIORITY_FLAG));
        }
        if (flagMap.containsKey(INFORMATION_FLAG)) {
            information = flagMap.get(INFORMATION_FLAG);
        }
        return new AddTaskCommand(title, dayOfTheWeek, priority, information);
    }

    //@@author ptejasv
    public static Command parseAddModuleCommand(String userResponse) {
        String moduleCode = userResponse.strip().toUpperCase();
        return new AddModuleCommand(moduleCode);
    }
}
