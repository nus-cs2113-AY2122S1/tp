package happybit.parser;

import happybit.command.Command;
import happybit.command.DeleteGoalCommand;
import happybit.command.DeleteHabitCommand;
import happybit.exception.HaBitParserException;

public class DeleteParser extends Parser {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_HABIT_INDEX_FORMAT = "Use the 'h/' flag to define the habit index. Eg: h/1";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The habit index has to be a number.";
    private static final int FLAG_LENGTH = 2;

    /**
     * Parses user input to delete a goal.
     *
     * @param input User input containing command parameters.
     * @return Command to delete specified goal.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseDeleteGoalCommand(String input) throws HaBitParserException  {
        String[] parameters = splitInput(input);
        int goalIndex = getGoalIndex(parameters);
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
        String[] parameters = splitInput(input);
        int goalIndex = getGoalIndex(parameters);
        int habitIndex = getHabitIndex(parameters);
        return new DeleteHabitCommand(goalIndex, habitIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /** Gets the goal index from user input.
     * @param parameters String array of command parameters.
     * @return Goal index.
     * @throws HaBitParserException If the goal index flag or goal index is absent, or non-integer goal index.
     */
    private static int getGoalIndex(String[] parameters) throws HaBitParserException {
        String strGoalIndex = getParameter(parameters, FLAG_GOAL_INDEX);
        if (strGoalIndex == null || strGoalIndex.equals(FLAG_GOAL_INDEX)) {
            throw new HaBitParserException(ERROR_GOAL_INDEX_FORMAT);
        }
        return stringToInt(strGoalIndex.substring(FLAG_LENGTH), ERROR_GOAL_INDEX_NON_INTEGER) - 1;
    }

    /** Gets the habit index from user input.
     *
     * @param parameters String array of command parameters.
     * @return Habit index.
     * @throws HaBitParserException If the habit index flag or habit index is absent, or non-integer habit index.
     */
    private static int getHabitIndex(String[] parameters) throws HaBitParserException {
        String strHabitIndex = getParameter(parameters, FLAG_HABIT_INDEX);
        if (strHabitIndex == null || strHabitIndex.equals(FLAG_HABIT_INDEX)) {
            throw new HaBitParserException(ERROR_HABIT_INDEX_FORMAT);
        }
        return stringToInt(strHabitIndex.substring(FLAG_LENGTH), ERROR_HABIT_INDEX_NON_INTEGER) - 1;
    }

    /**
     * Converts a string to an integer.
     *
     * @param strInt       String representation of an integer.
     * @param errorMessage Error message to call if string fails to parse.
     * @return Integer parsed from the string.
     * @throws HaBitParserException If the string fails to parse.
     */
    private static int stringToInt(String strInt, String errorMessage) throws HaBitParserException {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            throw new HaBitParserException(errorMessage);
        }
    }
}
