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

    //todo S L A P more in the future; refer to AddParser

    /**
     * Parses detail from user into goalIndex and goalName to create a new Command.
     *
     * @param commandInstruction Details from user.
     * @return A Command class with the goalIndex and goalName.
     * @throws HaBitParserException When commandInstruction is blank or does not have a number for goalIndex.
     */
    public static Command parseUpdateGoalNameCommand(String commandInstruction) throws HaBitParserException {
        if (commandInstruction == null) {
            throw new HaBitParserException(ERROR_BLANK);
        }

        try {

            int goalIndex;
            String goalName;
            int spaceIndex = commandInstruction.indexOf(" ");
            boolean instructionIsComplete = (spaceIndex != -1);

            if (!instructionIsComplete) {
                throw new HaBitParserException(ERROR_INCOMPLETE);
            }

            goalIndex = Integer.parseInt(commandInstruction.substring(0, spaceIndex)) - 1;
            goalName = commandInstruction.substring(spaceIndex + 1);

            assert (goalIndex > 0) : "Index of goal should be 1 or higher.";

            return new UpdateGoalNameCommand(goalIndex, goalName);

        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_NOT_NUM);
        }
    }

    public static Command parseUpdateHabitNameCommand(String commandInstruction) throws HaBitParserException {
        return null;
    }

    public static Command parseUpdateHabitIntervalCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        int goalIndex = getGoalIndex(commandInstruction);
        int habitIndex = getHabitIndex(commandInstruction);
        int interval = getInterval(commandInstruction);
        return new UpdateHabitIntervalCommand(goalIndex, habitIndex, interval);
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

    private static int getGoalIndex(String commandInstruction) throws HaBitParserException {
        String[] params = splitInput(commandInstruction);
        String goalParam = getParameter(params, GOAL_INDEX_FLAG);
        checkNoDescription(goalParam);
        return getInteger(goalParam, ERROR_INVALID_GOAL_NUMBER) - 1;
    }

    private static int getHabitIndex(String commandInstruction) throws HaBitParserException {
        String[] params = splitInput(commandInstruction);
        String habitParam = getParameter(params, HABIT_INDEX_FLAG);
        checkNoDescription(habitParam);
        return getInteger(habitParam, ERROR_INVALID_HABIT_NUMBER) - 1;
    }

    private static int getInterval(String commandInstruction) throws HaBitParserException {
        String[] params = splitInput(commandInstruction);
        String intervalParam = getParameter(params, INTERVAL_INDEX_FLAG);
        checkNoDescription(intervalParam);
        return getInteger(intervalParam, ERROR_INVALID_INTERVAL_NUMBER);
    }

    private static int getInteger(String input, String error_message) throws HaBitParserException {
        try {
            String integerString = input.substring(input.indexOf("/") + 1).trim();
            return Integer.parseInt(integerString);
        } catch (NumberFormatException e) {
            throw new HaBitParserException(error_message);
        }
    }

}
