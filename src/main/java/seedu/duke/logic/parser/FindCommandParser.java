package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.lesson.FindLessonCommand;
import seedu.duke.logic.commands.module.FindModuleCommand;
import seedu.duke.logic.commands.task.FindTaskCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.FIND_LESSON_FORMAT;
import static seedu.duke.commons.core.CommandFormat.FIND_MODULE_FORMAT;
import static seedu.duke.commons.core.CommandFormat.FIND_TASK_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.commons.util.StringUtil.isVerbose;
import static seedu.duke.commons.util.StringUtil.removeFirstParam;
import static seedu.duke.logic.parser.ParserUtil.checkParamsLength;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;

//@@author richwill28
public class FindCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case LESSON:
            simplifiedUserResponse = removeFirstParam(userResponse, "lesson");
            return parseFindLessonCommand(simplifiedUserResponse);
        case TASK:
            simplifiedUserResponse = removeFirstParam(userResponse, "task");
            return parseFindTaskCommand(simplifiedUserResponse);
        case MODULE:
            simplifiedUserResponse = removeFirstParam(userResponse, "module");
            return parseFindModuleCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(promptFormat(FIND_TASK_FORMAT, FIND_LESSON_FORMAT, FIND_MODULE_FORMAT));
        }
    }

    private static Command parseFindLessonCommand(String userResponse) throws ParseException {
        if (userResponse.isBlank()) {
            throw new ParseException(promptFormat(FIND_LESSON_FORMAT));
        }

        return new FindLessonCommand(userResponse.toLowerCase());
    }

    private static Command parseFindTaskCommand(String userResponse) throws ParseException {
        if (userResponse.isBlank()) {
            throw new ParseException(promptFormat(FIND_TASK_FORMAT));
        }

        return new FindTaskCommand(userResponse.toLowerCase());
    }

    private static Command parseFindModuleCommand(String userResponse) throws ParseException {
        if (userResponse.isBlank()) {
            throw new ParseException(promptFormat(FIND_MODULE_FORMAT));
        }

        String[] params = userResponse.split("\\s+");
        checkParamsLength(params, 1, 2);

        String moduleCode = params[0].toUpperCase();
        switch (params.length) {
        case 1:
            return new FindModuleCommand(moduleCode, false);
        case 2:
            boolean isVerbose = isVerbose(params[1]);
            return new FindModuleCommand(moduleCode, isVerbose);
        default:
            throw new ParseException(promptFormat(FIND_MODULE_FORMAT));
        }
    }
}
