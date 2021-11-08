package happybit.parser;

import happybit.command.Command;
import happybit.command.ListGoalsCommand;
import happybit.exception.HaBitParserException;

public class ListGoalsParser extends Parser {

    public static Command parseListGoalsCommand(String input) throws HaBitParserException {
        return new ListGoalsCommand(input);
    }
}