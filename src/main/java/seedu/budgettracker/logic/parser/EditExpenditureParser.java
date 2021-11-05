package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.EditExpenditureCommand;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.util.HashMap;

public class EditExpenditureParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = { PREFIX_MONTH,
        PREFIX_INDEX,
        PREFIX_DESCRIPTION,
        PREFIX_AMOUNT,
        PREFIX_DATE };

    public static EditExpenditureCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH), IS_COMPULSORY);
        int index = ParserUtil.parseIndex(argumentMap.get(PREFIX_INDEX));
        String description = ParserUtil.parseDescription(argumentMap.get(PREFIX_DESCRIPTION), IS_NOT_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_NOT_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE));

        if (description.equals("") && amount == 0.00 && argumentMap.get(PREFIX_DATE).equals("")) {
            throw new ParserException("Please include at least one value to edit!");
        }
        return new EditExpenditureCommand(month, index, amount, date, description);
    }
}
