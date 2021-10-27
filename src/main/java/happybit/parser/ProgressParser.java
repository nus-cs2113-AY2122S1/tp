package happybit.parser;

import happybit.command.Command;
import happybit.command.ViewProgressCommand;
import happybit.exception.HaBitParserException;
import happybit.habit.Habit;

public class ProgressParser extends ParserUtils {

    private static final String GOAL_INDEX_FLAG = "g/";
    private static final String ERROR_INVALID_GOAL_NUMBER = "Please enter a valid goal number";
    private static final String ERROR_INVALID_COMMAND_FORMAT = "Could not access goal number. "
            + "Please check your command format.";

    public static Command parseViewGoalProgressCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        int goalIndex = getGoalIndex(commandInstruction);
        return new ViewProgressCommand(goalIndex);
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
            throw new HaBitParserException(ERROR_INVALID_COMMAND_FORMAT);
        }
    }

    private static int getGoalIndex(String input) throws HaBitParserException {
        String[] params = splitInput(input);
        String goalParam = getParameter(params, GOAL_INDEX_FLAG);
        checkNoDescription(goalParam);
        try {
            String goalIndexString = goalParam.substring(goalParam.indexOf("/") + 1).trim();
            return Integer.parseInt(goalIndexString) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_GOAL_NUMBER);
        }
    }
}
