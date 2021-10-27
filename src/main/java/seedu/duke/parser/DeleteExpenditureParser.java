package seedu.duke.parser;

import seedu.duke.commands.DeleteAllExpenditureCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DeleteMultipleExpenditureCommand;
import seedu.duke.commands.DeleteSingleExpenditureCommand;
import seedu.duke.exception.EmptyDescriptionException;

import java.util.HashMap;

public class DeleteExpenditureParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_MONTH,
        PREFIX_INDEX };

    public static DeleteCommand parse(String args) throws NumberFormatException, EmptyDescriptionException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH),IS_COMPULSORY);

        String indexString = argumentMap.get(PREFIX_INDEX);
        if (!args.contains("i/")) {
            return new DeleteAllExpenditureCommand(month);
        } else if (indexString.contains("-")) {
            int[] indexArray = ParserUtil.parseMultipleIndexes(indexString);
            int startIndex = indexArray[0];
            int endIndex = indexArray[1];
            return new DeleteMultipleExpenditureCommand(startIndex, endIndex, month);
        }
        int index = ParserUtil.parseIndex(indexString);
        return new DeleteSingleExpenditureCommand(index, month);
    }
}
