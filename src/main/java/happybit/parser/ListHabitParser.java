package happybit.parser;

import happybit.command.Command;
import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;


public class ListHabitParser {
    private static final String ERROR_INVALID_GOAL_NUMBER = "Please enter a valid goal number";

    /**
     * Parses instruction to create ListHabitsCommand for specified goal number.
     *
     * @param commandInstruction Goal number.
     * @return ListHabitsCommand that will list habits under specified goal number.
     * @throws HaBitParserException If commandInstruction is not an integer.
     */
    public static Command parseListHabitCommand(String commandInstruction) throws HaBitParserException {
        int goalIndex;

        try {
            goalIndex = Integer.parseInt(commandInstruction) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_GOAL_NUMBER);
        }

        return new ListHabitsCommand(goalIndex);
    }

}
