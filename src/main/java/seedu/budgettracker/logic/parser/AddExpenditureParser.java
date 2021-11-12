package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.AddExpenditureCommand;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.util.HashMap;

//@@author jyxhazcake
/**
 * Parser class for parsing user input into an AddExpenditureCommand.
 */
public class AddExpenditureParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_DESCRIPTION,
        PREFIX_AMOUNT,
        PREFIX_DATE,
        PREFIX_CATEGORY };

    public static AddExpenditureCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);

        String description = ParserUtil.parseDescription(argumentMap.get(PREFIX_DESCRIPTION), IS_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE),false);
        Category category = ParserUtil.parseCategory(argumentMap.get(PREFIX_CATEGORY),false);

        return new AddExpenditureCommand(description, amount, date, category);
    }
}
