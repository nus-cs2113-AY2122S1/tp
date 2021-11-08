package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Message;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.util.StringUtil.removeFirstParam;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;

//@@author richwill28
public class Parser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case ADD:
            simplifiedUserResponse = removeFirstParam(userResponse, "add");
            return AddCommandParser.parse(simplifiedUserResponse);
        case DELETE:
            simplifiedUserResponse = removeFirstParam(userResponse, "delete");
            return DeleteCommandParser.parse(simplifiedUserResponse);
        case DONE:
            simplifiedUserResponse = removeFirstParam(userResponse, "done");
            return DoneCommandParser.parse(simplifiedUserResponse);
        case EDIT:
            simplifiedUserResponse = removeFirstParam(userResponse, "edit");
            return EditCommandParser.parse(simplifiedUserResponse);
        case FIND:
            simplifiedUserResponse = removeFirstParam(userResponse, "find");
            return FindCommandParser.parse(simplifiedUserResponse);
        case LAUNCH:
            simplifiedUserResponse = removeFirstParam(userResponse, "launch");
            return LaunchCommandParser.parse(simplifiedUserResponse);
        case LIST:
            simplifiedUserResponse = removeFirstParam(userResponse, "list");
            return ListCommandParser.parse(simplifiedUserResponse);
        case HELP:
            simplifiedUserResponse = removeFirstParam(userResponse, "help");
            return HelpCommandParser.parse(simplifiedUserResponse);
        case EXIT:
            simplifiedUserResponse = removeFirstParam(userResponse, "exit");
            return ExitCommandParser.parse(simplifiedUserResponse);
        case INVALID:
            // Fallthrough
        default:
            throw new ParseException(Message.ERROR_INVALID_COMMAND);
        }
    }
}
