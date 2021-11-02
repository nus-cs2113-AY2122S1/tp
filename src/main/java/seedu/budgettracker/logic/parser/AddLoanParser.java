package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.AddLoanCommand;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.util.HashMap;

public class AddLoanParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_NAME,
        PREFIX_AMOUNT,
        PREFIX_DATE };

    public static AddLoanCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArguments(args, PREFIX_ARRAY);

        String name = ParserUtil.parseName(argumentMap.get(PREFIX_NAME), IS_NOT_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_NOT_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE));

        return new AddLoanCommand(name,amount,date);
    }
}
