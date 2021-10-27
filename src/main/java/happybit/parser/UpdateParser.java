package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.exception.HaBitParserException;
import happybit.habit.Habit;

import static happybit.parser.Parser.splitInput;

public class UpdateParser extends Parser {

    private static final String GOAL_INDEX_FLAG = "g/";
    private static final String HABIT_INDEX_FLAG = "h/";
    private static final String INTERVAL_INDEX_FLAG = "i/";
    private static final String ERROR_BLANK = "No instruction given.";
    private static final String ERROR_NOT_NUM = "Expected a number.";
    private static final String ERROR_INCOMPLETE = "Instruction incomplete or improper.";
    private static final String ERROR_INVALID_GOAL_NUMBER = "Please enter a valid goal number";
    private static final String ERROR_INVALID_HABIT_NUMBER = "Please enter a valid habit number";
    private static final String ERROR_INVALID_INTERVAL_NUMBER = "Please enter a valid interval number";
    private static final String ERROR_INVALID_COMMAND_FORMAT = "Could not access goal number. "
            + "Please check your command format.";
    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_GOAL_NAME_FORMAT = "Use the 'n/' flag set the new goal name. "
            + "Eg: n/Reach for the starts";
    private static final int FLAG_LENGTH = 2;

    //todo S L A P more in the future; refer to AddParser

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

    public static Command parseUpdateHabitNameCommand(String commandInstruction) {
        return null;
    }

    public static Command parseUpdateHabitIntervalCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        String[] parameters = splitInput(commandInstruction);
        int goalIndex = getGoalIndex(parameters);
        int habitIndex = getHabitIndex(parameters);
        int interval = getInterval(parameters);
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
        return stringToInt(strGoalIndex.substring(FLAG_LENGTH), ERROR_INVALID_GOAL_NUMBER) - 1;
    }

    private static int getHabitIndex(String[] parameters) throws HaBitParserException {
        String strHabitIndex = getParameter(parameters, HABIT_INDEX_FLAG);
        if (strHabitIndex == null || strHabitIndex.equals(FLAG_HABIT_INDEX)) {
            throw new HaBitParserException(ERROR_INVALID_COMMAND_FORMAT );
        }
        return stringToInt(strHabitIndex.substring(FLAG_LENGTH), ERROR_INVALID_HABIT_NUMBER) - 1;
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



    private static int getInterval(String[] parameters) throws HaBitParserException {
        String strInterval = getParameter(parameters, INTERVAL_INDEX_FLAG);
        if (strInterval == null || strInterval.equals(INTERVAL_INDEX_FLAG)) {
            throw new HaBitParserException(ERROR_INVALID_COMMAND_FORMAT );
        }
        return stringToInt(strInterval.substring(FLAG_LENGTH), ERROR_INVALID_INTERVAL_NUMBER);
    }

    /**
     * Converts a string to an integer.
     *
     * @param strInt String representation of an integer.
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
