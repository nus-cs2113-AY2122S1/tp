package happybit.parser;

import happybit.command.Command;
import happybit.command.ViewStreakCommand;
import happybit.exception.HaBitParserException;

public class StreakParser extends Parser {

    private static final String GOAL_INDEX_FLAG = "g/";
    private static final String HABIT_INDEX_FLAG = "h/";
    private static final String ERROR_INVALID_GOAL_NUMBER = "Please enter a valid goal number";
    private static final String ERROR_INVALID_HABIT_NUMBER = "Please enter a valid goal number";
    private static final String ERROR_NO_PARAMS = "Command requires two parameters in the correct format."
            + " Please ensure that they are present.";

    /**
     * Parses instruction to create ViewHabitStreakCommand for a specified goal and habit number.
     *
     * @param commandInstruction Goal Number and Habit Number.
     * @return ViewHabitStreakCommand that will allow users to see the streak for their habit.
     * @throws HaBitParserException If commandInstruction does not have 2 parameters.
     */
    public static Command parseViewHabitStreakCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        String[] params = splitInput(commandInstruction);
        int goalIndex = getGoalIndex(params);
        int habitIndex = getHabitIndex(params);
        return new ViewStreakCommand(goalIndex, habitIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    private static void checkNoDescription(String input) throws HaBitParserException {
        if (input == null) {
            throw new HaBitParserException(ERROR_NO_PARAMS);
        }
    }

    private static int getGoalIndex(String[] params) throws HaBitParserException {
        String goalParam = getParameter(params, GOAL_INDEX_FLAG);
        checkNoDescription(goalParam);
        try {
            String goalIndexString = goalParam.substring(goalParam.indexOf("/") + 1).trim();
            return Integer.parseInt(goalIndexString) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_GOAL_NUMBER);
        }
    }

    private static int getHabitIndex(String[] params) throws HaBitParserException {
        String habitParam = getParameter(params, HABIT_INDEX_FLAG);
        checkNoDescription(habitParam);
        try {
            String goalIndexString = habitParam.substring(habitParam.indexOf("/") + 1).trim();
            return Integer.parseInt(goalIndexString) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_HABIT_NUMBER);
        }
    }
}
