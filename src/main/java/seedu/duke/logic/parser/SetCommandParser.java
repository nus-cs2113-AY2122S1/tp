package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Messages;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.module.SetGradeCommand;
import seedu.duke.logic.parser.exceptions.ParseException;
import seedu.duke.model.module.Grade;

import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.parseToZeroIndex;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

public class SetCommandParser {
    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);

        String simplifiedUserResponse;
        switch (commandType) {
        case GRADE:
            simplifiedUserResponse = removeFirstParam(userResponse, "grade");
            return parseSetGradeCommand(simplifiedUserResponse);
        default:
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        }
    }

    public static Command parseSetGradeCommand(String userResponse) throws ParseException {
        String[] params = userResponse.split(" ");
        try {
            int index = parseToZeroIndex(Integer.parseInt(params[0]));
            Grade grade = Grade.stringToGrade(params[1].toUpperCase());
            return new SetGradeCommand(index, grade);
        } catch (NumberFormatException e) {
            throw new ParseException(Messages.ERROR_INVALID_COMMAND);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(Messages.ERROR_INVALID_INDEX);
        }
    }
}
