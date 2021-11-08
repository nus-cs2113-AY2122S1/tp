package seedu.duke.logic.parser;

import java.util.HashMap;

import seedu.duke.commons.core.CommandFlag;
import seedu.duke.commons.core.CommandType;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.module.EditModuleCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.EDIT_MODULE_FORMAT;
import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.commons.util.StringUtil.removeFirstParam;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseModuleCode;
import static seedu.duke.logic.parser.ParserUtil.parseModuleGrade;

//@@author richwill28
public class EditCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case MODULE:
            simplifiedUserResponse = removeFirstParam(userResponse, "module");
            return parseEditModuleCommand(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(promptFormat(EDIT_MODULE_FORMAT));
        }
    }

    private static Command parseEditModuleCommand(String userResponse) throws ParseException {
        HashMap<String, String> flagMap = ParserUtil.getFlagMap(userResponse, CommandFlag.GRADE);

        if (!flagMap.containsKey(CommandFlag.GRADE)) {
            throw new ParseException(promptFormat(EDIT_MODULE_FORMAT));
        }

        String[] params = userResponse.split(CommandFlag.MODULE);
        String moduleCode = parseModuleCode(params[0]);
        String moduleGrade = parseModuleGrade(flagMap.get(CommandFlag.GRADE));

        return new EditModuleCommand(moduleCode, moduleGrade);
    }
}
