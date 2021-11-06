package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.EditLoanCommand;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.util.HashMap;

public class EditLoanParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_MONTH,PREFIX_INDEX,PREFIX_NAME,PREFIX_AMOUNT,PREFIX_DATE};

    public static EditLoanCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH), IS_COMPULSORY);
        int index = ParserUtil.parseIndex(argumentMap.get(PREFIX_INDEX));
        String name = ParserUtil.parseName(argumentMap.get(PREFIX_NAME), IS_NOT_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT),IS_NOT_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE));

        if (name.equals("") && amount == 0.00 && argumentMap.get(PREFIX_DATE).equals("")) {
            throw new ParserException("Please include at least one value to edit!");
        }

        return new EditLoanCommand(month, index,amount, date, name);
    }
}
