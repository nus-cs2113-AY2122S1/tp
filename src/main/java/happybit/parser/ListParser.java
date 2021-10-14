package happybit.parser;

import happybit.command.Command;
import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalList;

public class ListParser {
    private static final String ERROR_INVALID_GOAL_INDEX = "Please enter a valid integer for the goal index";

    public static Command parseListHabitCommand(String commandInstruction) throws HaBitParserException {
        // write parse code
        int goalIndex;
        try {
            goalIndex = Integer.parseInt(commandInstruction) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_GOAL_INDEX);
        }
        return new ListHabitsCommand(goalIndex);
    }

}
