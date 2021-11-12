package seedu.budgettracker.logic.parser;

import seedu.budgettracker.logic.commands.AddLoanCommand;
import seedu.budgettracker.common.exception.EmptyDescriptionException;
import seedu.budgettracker.logic.parser.exceptions.ParserException;

import java.time.LocalDate;
import java.util.HashMap;

//@@author jyxhazcake
/**
 * Parser class for parsing user input into an AddLoanCommand.
 */
public class AddLoanParser implements ParserPrefix {
    public static final String[] PREFIX_ARRAY = {
        PREFIX_NAME,
        PREFIX_AMOUNT,
        PREFIX_DATE };

    public static AddLoanCommand parse(String args) throws ParserException {
        HashMap<String, String> argumentMap = Parser.splitArgs(args, PREFIX_ARRAY);

        String name = ParserUtil.parseName(argumentMap.get(PREFIX_NAME), IS_NOT_COMPULSORY);
        double amount = ParserUtil.parseAmount(argumentMap.get(PREFIX_AMOUNT), IS_NOT_COMPULSORY);
        LocalDate date = ParserUtil.parseDate(argumentMap.get(PREFIX_DATE), false);

        return new AddLoanCommand(name,amount,date);
    }
}
