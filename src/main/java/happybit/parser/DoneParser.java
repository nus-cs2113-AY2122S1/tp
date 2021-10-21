package happybit.parser;

import happybit.command.Command;
import happybit.command.DoneHabitCommand;
import happybit.exception.HaBitParserException;

public class DoneParser {
    private static final String ERROR_NO_PARAMETER = "Please provide the goal and habit index"
            + " of the habit to be marked as done";
    private static final String ERROR_EXTRA_PARAMETERS = "Please provide only 2 integers";
    private static final String ERROR_MISSING_PARAMETERS = "The habit index is missing.";
    private static final String ERROR_INVALID_GOAL_INDEX = "Please enter a valid integer for the goal index";
    private static final String ERROR_INVALID_HABIT_INDEX = "Please enter a valid integer for the habit index";
    private static final int GOAL_ARG_INDEX = 0;
    private static final int HABIT_ARG_INDEX = 1;

    public static Command parseDoneHabitCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        String[] arguments = getArguments(commandInstruction);
        int goalIndex = getGoalIndex(arguments);
        int habitIndex = getHabitIndex(arguments);
        return new DoneHabitCommand(goalIndex, habitIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    private static int getHabitIndex(String[] arguments) throws HaBitParserException {
        int habitIndex;
        try {
            habitIndex = Integer.parseInt(arguments[HABIT_ARG_INDEX]) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_HABIT_INDEX);
        }
        return habitIndex;
    }

    private static int getGoalIndex(String[] arguments) throws HaBitParserException {
        int goalIndex;
        try {
            goalIndex = Integer.parseInt(arguments[GOAL_ARG_INDEX]) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_GOAL_INDEX);
        }
        return goalIndex;
    }

    private static String[] getArguments(String commandInstruction) throws HaBitParserException {
        String[] arguments = commandInstruction.split("\\s");
        if (arguments.length > 2) {
            throw new HaBitParserException(ERROR_EXTRA_PARAMETERS);
        } else if (arguments.length < 2) {
            throw new HaBitParserException(ERROR_MISSING_PARAMETERS);
        }
        return arguments;
    }

    private static void checkNoDescription(String commandInstruction) throws HaBitParserException {
        if (commandInstruction == null || commandInstruction.trim().equals("")) {
            throw new HaBitParserException(ERROR_NO_PARAMETER);
        }
    }

}
