package seedu.budgettracker.logic.parser;

import seedu.budgettracker.data.records.Category;
import seedu.budgettracker.logic.commands.EditExpenditureCommand;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.util.HashMap;

import static seedu.budgettracker.common.Messages.MESSAGE_INVALID_DATE;

//@@author jyxhazcake
/**
 * Parser class for parsing user input into an EditExpendituretCommand.
 */
public class EditExpenditureParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = { PREFIX_MONTH,
        PREFIX_INDEX,
        PREFIX_DESCRIPTION,
        PREFIX_AMOUNT,
        PREFIX_DATE,
        PREFIX_CATEGORY };

    public static EditExpenditureCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH), IS_COMPULSORY);
        int index = ParserUtil.parseIndex(argumentMap.get(PREFIX_INDEX));
        String description = ParserUtil.parseDescription(argumentMap.get(PREFIX_DESCRIPTION), IS_NOT_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_NOT_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE),true);
        Category category = ParserUtil.parseCategory(argumentMap.get(PREFIX_CATEGORY),true);

        checkArgumentsExist(argumentMap, description, amount);
        return new EditExpenditureCommand(month, index, amount, date, description, category);
    }

    private static void checkArgumentsExist(HashMap<String, String> argumentMap,
                                            String description, double amount) throws ParserException {
        if (description.equals("") && amount == 0.00 && argumentMap.get(PREFIX_DATE).equals("")
                && argumentMap.get(PREFIX_CATEGORY).equals("")) {
            throw new ParserException("Please include at least one valid value to edit!"
                    + "Invalid parameters will be ignored.");
        }
    }

}
