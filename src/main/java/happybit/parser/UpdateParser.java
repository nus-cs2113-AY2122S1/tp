package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.command.UpdateHabitNameCommand;
import happybit.exception.HaBitParserException;

public class UpdateParser extends Parser {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_GOAL_NAME_FORMAT = "Use the 'n/' flag set the new goal name. "
            + "Eg: n/Reach for the stars";
    private static final String ERROR_HABIT_INDEX_FORMAT = "Use the 'h/' flag to define the goal index. Eg: h/1";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The habit index has to be a number.";
    private static final String ERROR_HABIT_NAME_FORMAT = "Use the 'n/' flag set the new habit name. ";
    private static final String ERROR_INTERVAL_FORMAT = "Use the i/ flag to define the interval for the habit. Eg i/1";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The interval has to be a number";
    private static final int FLAG_LENGTH = 2;

    //todo S L A P more in the future; refer to AddParser

    /**
     * Parses detail from user into goalIndex and newGoalName (information) to create a new Command.
     *
     * @param input Input typed by the user.
     * @return A Command class with the goalIndex and newGoalName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateGoalNameCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getGoalIndex(parameters);
        String newGoalName = getNewGoalName(parameters);
        return new UpdateGoalNameCommand(goalIndex, newGoalName);
    }

    /**
     * Parses detail from user into goalIndex, habitIndex, and newHabitName (information) to create a new Command.
     *
     * @param input Input typed by the user.
     * @return A Command class with the goalIndex, habitIndex, and newHabitName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateHabitNameCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getGoalIndex(parameters);
        int habitIndex = getHabitIndex(parameters); //if name missing flag, will give (wrong) "has to be a number" error
        String newHabitName = getNewHabitName(parameters);
        return new UpdateHabitNameCommand(goalIndex, habitIndex, newHabitName);
    }

    /**
     * Parser to parse user's command of updating interval.
     *
     * @param commandInstruction Input from user.
     * @return A Command instance for UpdateHabitIntervalCommand with the goalIndex, habitIndex and interval
     * @throws HaBitParserException If command parameters are not defined, or defined improperly
     */
    public static Command parseUpdateHabitIntervalCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        String[] parameters = splitInput(commandInstruction);
        int goalIndex = getGoalIndex(parameters);
        int habitIndex = getHabitIndex(parameters);
        int interval = getInterval(parameters);
        assert (goalIndex > 0);
        assert (habitIndex > 0);
        assert (interval > 0);
        return new UpdateHabitIntervalCommand(goalIndex, habitIndex, interval);
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
        return stringToInt(strGoalIndex.substring(FLAG_LENGTH), FLAG_GOAL_INDEX) - 1;
    }

    /**
     * Gets the habit index from user input.
     *
     * @param parameters String array of command parameters.
     * @return Habit index.
     * @throws HaBitParserException If the habit index flag or habit index is absent, or non-integer.
     */
    private static int getHabitIndex(String[] parameters) throws HaBitParserException {
        String strHabitIndex = getParameter(parameters, FLAG_HABIT_INDEX);
        if (strHabitIndex == null || strHabitIndex.equals(FLAG_HABIT_INDEX)) {
            throw new HaBitParserException(ERROR_HABIT_INDEX_FORMAT);
        }
        return stringToInt(strHabitIndex.substring(FLAG_LENGTH), FLAG_HABIT_INDEX) - 1;
    }

    /**
     * Gets the new goal name from user input. All leading and trailing whitespaces will be removed.
     *
     * @param parameters String array of command parameters.
     * @return New goal name.
     * @throws HaBitParserException If the new goal name or its flag is absent.
     */
    private static String getNewGoalName(String[] parameters) throws HaBitParserException {
        String strGoalIndex = getParameter(parameters, FLAG_NAME);
        if (strGoalIndex == null || strGoalIndex.equals(FLAG_NAME)) {
            throw new HaBitParserException(ERROR_GOAL_NAME_FORMAT);
        }
        return strGoalIndex.substring(FLAG_LENGTH).trim();
    }

    /**
     * Gets the new habit name from user input. All leading and trailing whitespaces will be removed.
     *
     * @param parameters String array of command parameters.
     * @return New goal name.
     * @throws HaBitParserException If the new goal name or its flag is absent.
     */
    private static String getNewHabitName(String[] parameters) throws HaBitParserException {
        String strHabitIndex = getParameter(parameters, FLAG_NAME);
        if (strHabitIndex == null || strHabitIndex.equals(FLAG_NAME)) {
            throw new HaBitParserException(ERROR_HABIT_NAME_FORMAT);
        }
        return strHabitIndex.substring(FLAG_LENGTH).trim();
    }



    private static int getInterval(String[] parameters) throws HaBitParserException {
        String strInterval = getParameter(parameters, FLAG_INTERVAL);
        if (strInterval == null || strInterval.equals(FLAG_INTERVAL)) {
            throw new HaBitParserException(ERROR_INTERVAL_FORMAT);
        }
        return stringToInt(strInterval.substring(FLAG_LENGTH), FLAG_INTERVAL);
    }

    /**
     * Converts a string to an integer.
     *
     * @param strInt String representation of an integer.
     * @return Integer parsed from the string.
     * @throws HaBitParserException If the string fails to parse.
     */
    private static int stringToInt(String strInt, String flag) throws HaBitParserException {
        try {
            return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            if (flag.equals(FLAG_GOAL_INDEX)) {
                throw new HaBitParserException(ERROR_GOAL_INDEX_NON_INTEGER);
            } else if (flag.equals(FLAG_HABIT_INDEX)) {
                throw new HaBitParserException(ERROR_HABIT_INDEX_NON_INTEGER);
            } else if (flag.equals(FLAG_INTERVAL)) {
                throw new HaBitParserException(ERROR_INTERVAL_NON_INTEGER);
            } else {
                throw new HaBitParserException("Index has to be a number.");
            }
        }
    }
}
