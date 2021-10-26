package happybit.parser;

import happybit.command.Command;
import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;


public class ListHabitParser extends Parser {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final int FLAG_LENGTH = 2;

    /**
     * Parses user input to list habits under specified goal.
     *
     * @param input User input containing command parameter: goal number.
     * @return ListHabitsCommand that will list habits under specified goal number.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseListHabitCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getGoalIndex(parameters);
        return new ListHabitsCommand(goalIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Gets the goal index from user input.
     *
     * @param parameters String array of command parameters.
     * @return Goal index.
     * @throws HaBitParserException If the goal index flag or goal index is absent, or non-integer goal index.
     */
    private static int getGoalIndex(String[] parameters) throws HaBitParserException {
        String strGoalIndex = getParameter(parameters, FLAG_GOAL_INDEX);
        if (strGoalIndex == null || strGoalIndex.equals(FLAG_GOAL_INDEX)) {
            throw new HaBitParserException(ERROR_GOAL_INDEX_FORMAT);
        }
        return stringToInt(strGoalIndex.substring(FLAG_LENGTH)) - 1;
    }

    /**
     * Converts a string to an integer.
     *
     * @param strInt String representation of an integer.
     * @return Integer parsed from the string.
     * @throws HaBitParserException If the string fails to parse.
     */
    private static int stringToInt(String strInt) throws HaBitParserException {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ListHabitParser.ERROR_GOAL_INDEX_NON_INTEGER);
        }
    }
}
