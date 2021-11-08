package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.DayOfTheWeek;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.ListLessonCommand;
import seedu.duke.logic.commands.module.ListModuleCommand;
import seedu.duke.logic.commands.task.ListTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.LIST_LESSON_FORMAT;
import static seedu.duke.commons.core.CommandFormat.LIST_MODULE_FORMAT;
import static seedu.duke.commons.core.CommandFormat.LIST_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.commons.util.DayUtil.isToday;
import static seedu.duke.commons.util.DayUtil.isTomorrow;
import static seedu.duke.commons.util.StringUtil.removeFirstParam;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;

//@@author richwill28
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
        case MODULE:
            simplifiedUserResponse = removeFirstParam(userResponse, "module");
            return parseListModuleCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(promptFormat(LIST_TASK_FORMAT, LIST_LESSON_FORMAT, LIST_MODULE_FORMAT));
        }
    }

    private static Command parseListLessonCommand(String userResponse) throws ParseException {
        boolean isBlank = userResponse.isBlank();
        boolean isDayOfTheWeek = DayOfTheWeek.is(userResponse);
        boolean isToday = isToday(userResponse);
        boolean isTomorrow = isTomorrow(userResponse);
        boolean isValid = isBlank || isDayOfTheWeek || isToday || isTomorrow;

        if (!isValid) {
            throw new ParseException(promptFormat(LIST_LESSON_FORMAT));
        }

        return new ListLessonCommand(userResponse.toLowerCase());
    }

    private static Command parseListTaskCommand(String userResponse) throws ParseException {
        boolean isBlank = userResponse.isBlank();
        boolean isDayOfTheWeek = DayOfTheWeek.is(userResponse);
        boolean isToday = isToday(userResponse);
        boolean isTomorrow = isTomorrow(userResponse);
        boolean isPriority = userResponse.equalsIgnoreCase("priority");
        boolean isValid = isBlank || isDayOfTheWeek || isToday || isTomorrow || isPriority;

        if (!isValid) {
            throw new ParseException(promptFormat(LIST_TASK_FORMAT));
        }

        return new ListTaskCommand(userResponse.toLowerCase());
    }

    private static Command parseListModuleCommand(String userResponse) throws ParseException {
        if (!userResponse.isBlank()) {
            throw new ParseException(promptFormat(LIST_MODULE_FORMAT));
        }
        return new ListModuleCommand();
    }
}
