package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.AddExpenditureCommand;
import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.common.exception.EmptyDescriptionException;

import java.time.LocalDate;
import java.util.HashMap;

public class AddExpenditureParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_DESCRIPTION,
        PREFIX_AMOUNT,
        PREFIX_DATE,
        PREFIX_CATEGORY };

    public static AddExpenditureCommand parse(String args) throws NumberFormatException, EmptyDescriptionException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        String description = ParserUtil.parseDescription(argumentMap.get(PREFIX_DESCRIPTION), IS_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE));
        Category category = ParserUtil.parseCategory(argumentMap.get(PREFIX_CATEGORY));

        return new AddExpenditureCommand(description,amount,date,category);
    }
}
