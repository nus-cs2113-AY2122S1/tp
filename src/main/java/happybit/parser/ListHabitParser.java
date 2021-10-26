package happybit.parser;

import happybit.command.Command;
import happybit.command.ListHabitsCommand;
import happybit.exception.HaBitParserException;


public class ListHabitParser extends Parser {

    private static final String GOAL_INDEX_FLAG = "g/";
    private static final String ERROR_INVALID_GOAL_NUMBER = "Please enter a valid goal number";
    private static final String ERROR_INVALID_COMMAND_FORMAT = "Could not access goal number. "
        + "Please check your command format.";

    /**
     * Parses instruction to create ListHabitsCommand for specified goal number.
     *
     * @param commandInstruction Goal number.
     * @return ListHabitsCommand that will list habits under specified goal number.
     * @throws HaBitParserException If commandInstruction is not an integer.
     */
    public static Command parseListHabitCommand(String commandInstruction) throws HaBitParserException {
        if (commandInstruction == null) {
            throw new HaBitParserException(ERROR_INVALID_COMMAND_FORMAT);
        }

        int goalIndex = getGoalIndex(commandInstruction);
        return new ListHabitsCommand(goalIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    private static int getGoalIndex(String commandInstruction) throws HaBitParserException {
        String[] params = splitInput(commandInstruction);
        String goalParam = getParameter(params, GOAL_INDEX_FLAG);

        if (goalParam == null) {
            throw new HaBitParserException(ERROR_INVALID_COMMAND_FORMAT);
        }

        try {
            String goalIndexString = goalParam.substring(goalParam.indexOf("/") + 1).trim();
            return Integer.parseInt(goalIndexString) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_GOAL_NUMBER);
        }
    }

}
