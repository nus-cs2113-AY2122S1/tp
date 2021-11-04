package happybit.parser;

import happybit.command.Command;
import happybit.command.DeleteGoalCommand;
import happybit.command.DeleteHabitCommand;
import happybit.exception.HaBitParserException;

public class DeleteParser extends Parser {

    /**
     * Parses user input to delete a goal.
     *
     * @param input User input containing command parameters.
     * @return Command to delete specified goal.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseDeleteGoalCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
        return new DeleteGoalCommand(goalIndex);
    }

    /**
     * Parses user input to delete a habit associated with a goal.
     *
     * @param input User input containing command parameters.
     * @return Command to delete specified habit from a specified goal.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseDeleteHabitCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
        int habitIndex = getNumber(parameters, FLAG_HABIT_INDEX) - 1;
        return new DeleteHabitCommand(goalIndex, habitIndex);
    }

}
