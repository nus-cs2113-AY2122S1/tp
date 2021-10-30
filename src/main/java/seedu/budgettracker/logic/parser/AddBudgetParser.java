package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.AddBudgetCommand;

import java.util.HashMap;

public class AddBudgetParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_MONTH,PREFIX_AMOUNT};

    public static AddBudgetCommand parse(String args) throws NumberFormatException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH), IS_NOT_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_COMPULSORY);

        return new AddBudgetCommand(amount, month);
    }
}
