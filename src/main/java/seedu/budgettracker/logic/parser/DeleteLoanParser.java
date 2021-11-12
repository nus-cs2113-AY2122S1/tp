package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.DeleteAllLoanCommand;
import seedu.budgettracker.logic.commands.DeleteCommand;
import seedu.budgettracker.logic.commands.DeleteMultipleLoanCommand;
import seedu.budgettracker.logic.commands.DeleteSingleLoanCommand;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.util.HashMap;

//@@author jyxhazcake
//@@author EdisonZhong17
/**
 * Parser class for parsing user input into a DeleteLoanCommand.
 */
public class DeleteLoanParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_MONTH,
        PREFIX_INDEX };

    public static DeleteCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH),IS_COMPULSORY);

        String indexString = argumentMap.get(PREFIX_INDEX);
        return parseDeleteCommand(month, indexString);
    }

    private static DeleteCommand parseDeleteCommand(int month, String indexString) throws ParserException {
        if (indexString.equals("")) {
            return new DeleteAllLoanCommand(month);
        } else if (indexString.contains("-")) {
            int[] indexArray = ParserUtil.parseMultipleIndexes(indexString);
            int startIndex = indexArray[0];
            int endIndex = indexArray[1];
            return new DeleteMultipleLoanCommand(startIndex - 1, endIndex - 1, month);
        }
        int index = ParserUtil.parseIndex(indexString);
        return new DeleteSingleLoanCommand(index, month);
    }
}
