package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalNameCommand;
import happybit.command.UpdateGoalTypeCommand;
import happybit.command.UpdateHabitIntervalCommand;
import happybit.command.UpdateHabitNameCommand;
import happybit.exception.HaBitParserException;
import happybit.goal.GoalType;

public class UpdateParser extends Parser {

    private static final String ERROR_GOAL_INDEX_FORMAT = "Use the 'g/' flag to define the goal index. Eg: g/1";
    private static final String ERROR_GOAL_INDEX_NON_INTEGER = "The goal index has to be a number.";
    private static final String ERROR_GOAL_NAME_FORMAT = "Use the 'n/' flag set the new goal name. "
            + "Eg: n/Reach for the stars";
    private static final String ERROR_GOAL_TYPE_FORMAT = "Use the 't/' flag set the new goal type. "
            + "Eg: t/fd";
    private static final String ERROR_HABIT_INDEX_FORMAT = "Use the 'h/' flag to define the goal index. Eg: h/1";
    private static final String ERROR_HABIT_INDEX_NON_INTEGER = "The habit index has to be a number.";
    private static final String ERROR_HABIT_NAME_FORMAT = "Use the 'n/' flag set the new habit name. ";
    private static final String ERROR_INTERVAL_FORMAT = "Use the i/ flag to define the interval for the habit. Eg i/1";
    private static final String ERROR_INTERVAL_NON_INTEGER = "The interval has to be a number";
    private static final String ERROR_GOAL_TYPE_LABEL = "Use the following goal types: 'sl', 'fd', 'ex', 'sd', 'df'";

    private static final int FLAG_LENGTH = 2;
    private static final String SLEEP_LABEL = "sl";
    private static final String FOOD_LABEL = "fd";
    private static final String EXERCISE_LABEL = "ex";
    private static final String STUDY_LABEL = "sd";
    private static final String DEFAULT_LABEL = "df";

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
     * Parses detail from user into goalIndex and newGoalName (information) to create a new Command.
     *
     * @param input Input typed by the user.
     * @return A Command class with the goalIndex and newGoalName.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseUpdateGoalTypeCommand(String input) throws HaBitParserException {
        checkNoDescription(input);
        String[] parameters = splitInput(input);
        int goalIndex = getGoalIndex(parameters);
        GoalType newGoalType = getNewGoalType(parameters);
        return new UpdateGoalTypeCommand(goalIndex, newGoalType);
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
        return stringToInt(strGoalIndex.substring(FLAG_LENGTH), FLAG_GOAL_INDEX) - 1;
    }

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
     * Gets the new goal type from user input. All leading and trailing whitespaces will be removed.
     *
     * @param parameters String array of command parameters.
     * @return New goal type.
     * @throws HaBitParserException If the new goal type or its flag is absent.
     */
    private static GoalType getNewGoalType(String[] parameters) throws HaBitParserException {
        String strGoalType = getParameter(parameters, FLAG_GOAL_TYPE);
        if (strGoalType == null || strGoalType.equals(FLAG_GOAL_TYPE)) {
            throw new HaBitParserException(ERROR_GOAL_TYPE_FORMAT);
        }
        strGoalType = strGoalType.substring(FLAG_LENGTH).trim();
        return getGoalType(strGoalType);
    }

    /**
     * Gets Goal Type from a string label.
     *
     * @param label String containing label of goal type.
     * @return Goal type corresponding to string label.
     * @throws HaBitParserException If an invalid label is used.
     */
    private static GoalType getGoalType(String label) throws HaBitParserException {
        switch (label) {
        case SLEEP_LABEL:
            return GoalType.SLEEP;
        case FOOD_LABEL:
            return GoalType.FOOD;
        case EXERCISE_LABEL:
            return GoalType.EXERCISE;
        case STUDY_LABEL:
            return GoalType.STUDY;
        case DEFAULT_LABEL:
            return GoalType.DEFAULT;
        default:
            throw new HaBitParserException(ERROR_GOAL_TYPE_LABEL);
        }
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
            switch (flag) {
            case FLAG_GOAL_INDEX:
                throw new HaBitParserException(ERROR_GOAL_INDEX_NON_INTEGER);
            case FLAG_HABIT_INDEX:
                throw new HaBitParserException(ERROR_HABIT_INDEX_NON_INTEGER);
            case FLAG_INTERVAL:
                throw new HaBitParserException(ERROR_INTERVAL_NON_INTEGER);
            default:
                throw new HaBitParserException("Index has to be a number.");
            }
        }
    }

}
