package seedu.duke.parser;

import seedu.duke.commands.ListRecordsCommand;
import seedu.duke.data.records.Category;
import seedu.duke.exception.EmptyDescriptionException;

import java.util.HashMap;

public class ListRecordParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_MONTH, PREFIX_CATEGORY};

    public static ListRecordsCommand parse(String args) throws NumberFormatException, EmptyDescriptionException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH));
        Category category = ParserUtil.parseListCategory(argumentMap.get(PREFIX_CATEGORY));

        if (month > 0) {
            return new ListRecordsCommand(month, category);
        } else {
            return  new ListRecordsCommand(category);
        }
    }
}