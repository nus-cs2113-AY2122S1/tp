package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.StatCategoryCommand;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.util.HashMap;

//@@author jyxhazcake
/**
 * Parser class for parsing user input into an StatCategoryCommand.
 */
public class StatCategoryParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_MONTH };

    public static StatCategoryCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);
        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH), IS_COMPULSORY);
        return new StatCategoryCommand(month);
    }
}
