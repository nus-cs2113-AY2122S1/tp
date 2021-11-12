package happybit.parser;

import happybit.command.Command;
import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;

import java.util.ArrayList;

public class ListHabitParser extends Parser {

    /**
     * Parses user input to list habits under specified goal.
     *
     * @param input User input containing command parameter: goal number.
     * @return ListHabitsCommand that will list habits under specified goal number.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseListHabitCommand(String input) throws HaBitParserException {
        ArrayList<String> parameters = splitInput(input);
        int goalIndex = getIndex(parameters, FLAG_GOAL_INDEX);
        return new ListHabitsCommand(goalIndex);
    }

}
