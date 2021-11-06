package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.EditBudgetCommand;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.util.HashMap;

public class EditBudgetParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_MONTH,PREFIX_AMOUNT};

    public static EditBudgetCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH), IS_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_COMPULSORY);

        return new EditBudgetCommand(month, amount);
    }
}
