package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalNameCommand;
import happybit.exception.HaBitParserException;

public class UpdateParser extends Parser {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_GOAL_NAME_FORMAT = "Use the 'n/' flag set the new goal name. "
            + "Eg: n/Reach for the starts";
    private static final int FLAG_LENGTH = 2;

    /**
     * Parses detail from user into goalIndex and goalName to create a new Command.
     *
     * @param input Details from user.
     * @return A Command class with the goalIndex and goalName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateGoalNameCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getGoalIndex(parameters);
        String newGoalName = getNewGoalName(parameters);
        return new UpdateGoalNameCommand(goalIndex, newGoalName);
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
     * Gets the new goal name from user input. All leading and trailing whitespaces will be removed.
     *
     * @param parameters String array of command parameters.
     * @return New goal name.
     * @throws HaBitParserException If the new goal name or its flag is absent.
     */
    private static String getNewGoalName(String[] parameters) throws HaBitParserException {
        String strHabitIndex = getParameter(parameters, FLAG_NAME);
        if (strHabitIndex == null || strHabitIndex.equals(FLAG_NAME)) {
            throw new HaBitParserException(ERROR_GOAL_NAME_FORMAT);
        }
        return strHabitIndex.substring(FLAG_LENGTH).trim();
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
            throw new HaBitParserException(UpdateParser.ERROR_GOAL_INDEX_NON_INTEGER);
        }
    }
}
