package seedu.duke.parser;

import seedu.duke.commands.EditExpenditureCommand;
import seedu.duke.commands.EditLoanCommand;
import seedu.duke.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.util.HashMap;

public class EditLoanParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_MONTH,PREFIX_INDEX,PREFIX_NAME,PREFIX_AMOUNT,PREFIX_DATE};

    public static EditLoanCommand parse(String args) throws NumberFormatException, EmptyDescriptionException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH));
        int index = ParserUtil.parseIndex(argumentMap.get(PREFIX_INDEX));
        String name = ParserUtil.parseName(argumentMap.get(PREFIX_NAME), IS_NOT_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT),IS_NOT_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE));

        return new EditLoanCommand(month, index,amount, date, name);
    }
}
