package seedu.duke.parser;

import seedu.duke.commands.StatCategoryCommand;

import java.util.HashMap;

public class StatCategoryParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_MONTH };

    public static StatCategoryCommand parse(String args) throws NumberFormatException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);
        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH), IS_COMPULSORY);
        return new StatCategoryCommand(month);
    }
}
