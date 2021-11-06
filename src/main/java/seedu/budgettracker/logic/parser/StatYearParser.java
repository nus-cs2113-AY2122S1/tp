package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.StatYearCommand;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.util.HashMap;

public class StatYearParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_TYPE };

    public static StatYearCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);
        int type = ParserUtil.parseType(argumentMap.get(PREFIX_TYPE), IS_COMPULSORY);
        return new StatYearCommand(type);
    }
}
