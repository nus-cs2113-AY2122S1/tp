package seedu.duke.parser;

import seedu.duke.commands.EditExpenditureCommand;
import seedu.duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.util.HashMap;

public class EditExpenditureParser {
    public static final String[] PREFIX_ARRAY = {"m/","i/","c/","a/","d/"};

    public static EditExpenditureCommand parse(String args) throws NumberFormatException, EmptyDescriptionException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(ParserPrefix.PREFIX_MONTH));
        int index = ParserUtil.parseIndex(argumentMap.get(ParserPrefix.PREFIX_INDEX));
        String description = ParserUtil.parseDescription(argumentMap.get(ParserPrefix.PREFIX_DESCRIPTION), false);
        double amount = ParserUtil.parseAmount(argumentMap.get(ParserPrefix.PREFIX_AMOUNT),false);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(ParserPrefix.PREFIX_DATE));

        return new EditExpenditureCommand(month, index,amount, date, description);
    }
}
