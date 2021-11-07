package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandFlag;
import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Message;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.AddLessonCommand;
import seedu.duke.logic.commands.module.AddModuleCommand;
import seedu.duke.logic.commands.task.AddTaskCommand;
import seedu.duke.logic.commands.task.DoneTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import java.time.LocalTime;
import java.util.HashMap;

import static seedu.duke.commons.core.CommandFormat.ADD_LESSON_FORMAT;
import static seedu.duke.commons.core.CommandFormat.ADD_MODULE_FORMAT;
import static seedu.duke.commons.core.CommandFormat.ADD_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormat.DONE_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.commons.core.Priority.LOW;
import static seedu.duke.commons.util.StringUtil.removeFirstParam;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseDayOfTheWeek;
import static seedu.duke.logic.parser.ParserUtil.parseGrade;
import static seedu.duke.logic.parser.ParserUtil.parsePriority;
import static seedu.duke.logic.parser.ParserUtil.parseTime;
import static seedu.duke.logic.parser.ParserUtil.parseTitle;
import static seedu.duke.logic.parser.ParserUtil.parseToZeroIndex;

//@@author richwill28
public class EditCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseDoneTaskCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(promptFormat(DONE_TASK_FORMAT));
        }
    }

    private static Command parseDoneTaskCommand(String userResponse) throws ParseException {
        try {
            int taskIndex = parseToZeroIndex(Integer.parseInt(userResponse));
            return new DoneTaskCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(Message.ERROR_INVALID_NUMBER);
        }
    }
}
