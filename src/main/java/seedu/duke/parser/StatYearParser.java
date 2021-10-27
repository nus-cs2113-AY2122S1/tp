package seedu.duke.parser;

import seedu.duke.commands.StatYearCommand;

import java.util.HashMap;

public class StatYearParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_TYPE };

    public static StatYearCommand parse(String args) throws NumberFormatException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);
        int type = ParserUtil.parseType(argumentMap.get(PREFIX_TYPE), IS_COMPULSORY);
        return new StatYearCommand(type);
    }
}
