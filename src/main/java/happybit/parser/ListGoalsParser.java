package happybit.parser;

import happybit.command.Command;
import happybit.command.ListGoalsCommand;

public class ListGoalsParser extends Parser {

    /**
     * Parses user input to list all goals.
     *
     * @param input User input containing command parameter: goal number.
     * @return ListGoalsCommand that will list all goals.
     */
    public static Command parseListGoalsCommand(String input) {
        return new ListGoalsCommand(input);
    }
}