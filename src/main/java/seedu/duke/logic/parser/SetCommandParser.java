package seedu.duke.logic.parser;

import seedu.duke.commons.core.CommandType;
import seedu.duke.commons.core.Message;
import seedu.duke.logic.commands.Command;
import seedu.duke.logic.commands.module.SetGradeCommand;
import seedu.duke.logic.parser.exceptions.ParseException;

import static seedu.duke.commons.core.CommandFormat.promptFormat;
import static seedu.duke.commons.core.CommandFormat.SET_GRADE_FORMAT;
import static seedu.duke.logic.parser.ParserUtil.parseCommandType;
import static seedu.duke.logic.parser.ParserUtil.removeFirstParam;

//@@author rebchua39
public class SetCommandParser {
    private static final String[] GRADES = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "D+", "D", "F", "S", "U",
        "CS", "CU", "NONE"};

    public static Command parse(String userResponse) throws ParseException {
        CommandType commandType = parseCommandType(userResponse);
        String simplifiedUserResponse;
        switch (commandType) {
        case GRADE:
            simplifiedUserResponse = removeFirstParam(userResponse, "grade");
            return parseSetGradeCommand(simplifiedUserResponse);
        default:
            throw new ParseException(promptFormat(SET_GRADE_FORMAT));
        }
    }

    public static Command parseSetGradeCommand(String userResponse) throws ParseException {
        try {
            String[] params = userResponse.split(" ");
            String moduleCode = params[0].toUpperCase();
            String grade = params[1].toUpperCase();
            if (!isValidGrade(grade)) {
                throw new ParseException("The grade you entered is invalid.");
            }
            return new SetGradeCommand(moduleCode, grade);
        } catch (IndexOutOfBoundsException e) {
            throw new ParseException(Message.ERROR_INVALID_COMMAND);
        }
    }

    private static boolean isValidGrade(String param) {
        for (String grade : GRADES) {
            if (param.equals(grade)) {
                return true;
            }
        }
        return false;
    }
}
