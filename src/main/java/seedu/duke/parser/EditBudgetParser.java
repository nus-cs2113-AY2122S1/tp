package seedu.duke.parser;

import seedu.duke.commands.EditBudgetCommand;

import java.util.HashMap;

public class EditBudgetParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {PREFIX_MONTH,PREFIX_AMOUNT};

    public static EditBudgetCommand parse(String args) throws NumberFormatException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        int month = ParserUtil.parseMonth(argumentMap.get(PREFIX_MONTH));
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT),IS_COMPULSORY);

        return new EditBudgetCommand(month, amount);
    }
}
