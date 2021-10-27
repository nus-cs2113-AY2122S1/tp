package happybit.parser;

import happybit.command.Command;
import happybit.command.SetCommand;
import happybit.exception.HaBitParserException;

public class SetParser {

    public static Command parseSetGoalCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        int goalIndex = getGoalIndex(commandInstruction);
        return new SetCommand(goalIndex);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Checks if the input is null.
     *
     * @param input String of the user input.
     * @throws HaBitParserException If the user input is null (blank).
     */
    private static void checkNoDescription(String input) throws HaBitParserException {
        if (input == null) {
            throw new HaBitParserException("Command cannot be called without parameters.");
        }
    }

    /**
     * Checks if the input is an integer.
     *
     * @param input String of the user input.
     * @return Integer index of a goal.
     * @throws HaBitParserException If the user input cannot be converted to an integer.
     */
    private static int getGoalIndex(String input) throws HaBitParserException {
        int goalNumber;
        try {
            goalNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new HaBitParserException("Please enter a valid integer for the goal index");
        }
        return goalNumber - 1;
    }

}
