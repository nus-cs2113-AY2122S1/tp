package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Message;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.DeleteLessonCommand;
import seedu.duke.logic.commands.module.DeleteModuleCommand;
import seedu.duke.logic.commands.task.DeleteTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.DELETE_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormat.DELETE_LESSON_FORMAT;
import static seedu.duke.commons.core.CommandFormat.DELETE_MODULE_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseToZeroIndex;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author richwill28
public class DeleteCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case LESSON:
            simplifiedUserResponse = removeFirstParam(userResponse, "lesson");
            return parseDeleteLessonCommand(simplifiedUserResponse);
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseDeleteTaskCommand(simplifiedUserResponse);
        case MODULE:
            simplifiedUserResponse = removeFirstParam(userResponse, "module");
            return parseDeleteModuleCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(promptFormat(DELETE_TASK_FORMAT, DELETE_LESSON_FORMAT, DELETE_MODULE_FORMAT));
        }
    }

    private static Command parseDeleteLessonCommand(String userResponse) throws ParseException {
        try {
            int lessonIndex = parseToZeroIndex(Integer.parseInt(userResponse));
            return new DeleteLessonCommand(lessonIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(Message.ERROR_INVALID_NUMBER);
        }
    }

    //@@author ptejasv
    private static Command parseDeleteTaskCommand(String userResponse) throws ParseException {
        try {
            int taskIndex = parseToZeroIndex(Integer.parseInt(userResponse));
            return new DeleteTaskCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ParseException(Message.ERROR_INVALID_NUMBER);
        }
    }

    //@@author Roycius
    private static Command parseDeleteModuleCommand(String userResponse) throws ParseException {
        try {
            String moduleCode = userResponse.toUpperCase();
            return new DeleteModuleCommand(moduleCode);
        } catch (NumberFormatException e) {
            throw new ParseException(Message.ERROR_INVALID_NUMBER);
        }
    }
}
