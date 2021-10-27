package seedu.duke.parser;

import seedu.duke.commands.DeleteAllLoanCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DeleteMultipleLoanCommand;
import seedu.duke.commands.DeleteSingleLoanCommand;
import seedu.duke.exception.EmptyDescriptionException;

import java.util.HashMap;

public class DeleteLoanParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_MONTH,
        PREFIX_INDEX };

    public static DeleteCommand parse(String args) throws NumberFormatException, EmptyDescriptionException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH),IS_COMPULSORY);

        String indexString = argumentMap.get(PREFIX_INDEX);
        if (indexString.equals("")) {
            return new DeleteAllLoanCommand(month);
        } else if (indexString.contains("-")) {
            int[] indexArray = ParserUtil.parseMultipleIndexes(indexString);
            int startIndex = indexArray[0];
            int endIndex = indexArray[1];
            return new DeleteMultipleLoanCommand(startIndex, endIndex, month);
        }
        int index = ParserUtil.parseIndex(indexString);
        return new DeleteSingleLoanCommand(index, month);
    }
}
