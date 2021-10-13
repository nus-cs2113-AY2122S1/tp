package happybit.parser;

import happybit.command.Command;
import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalList;

public class ListParser {

    public static Command parseListHabitCommand(String commandInstruction) throws HaBitParserException {
        // write parse code
        int goalIndex;
        try {
            goalIndex = Integer.parseInt(commandInstruction);
        } catch (NumberFormatException e) {
            throw new HaBitParserException("Please enter a valid integer for the goal index");
        }
        return new ListHabitsCommand(goalIndex);
    }

}
