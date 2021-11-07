package happybit.parser;

import happybit.command.Command;
import happybit.command.ListGoalsCommand;
import happybit.exception.HaBitParserException;

public class ListGoalParser extends Parser {

    public static Command parseListGoalCommand(String input) throws HaBitParserException {
        return new ListGoalsCommand(input);
    }
}