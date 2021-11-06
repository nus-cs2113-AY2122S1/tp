package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.DeleteAllExpenditureCommand;
import seedu.budgettracker.logic.commands.DeleteCommand;
import seedu.budgettracker.logic.commands.DeleteMultipleExpenditureCommand;
import seedu.budgettracker.logic.commands.DeleteSingleExpenditureCommand;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.util.HashMap;

public class DeleteExpenditureParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_MONTH,
        PREFIX_INDEX };

    public static DeleteCommand parse(String args) throws ParserException {
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
