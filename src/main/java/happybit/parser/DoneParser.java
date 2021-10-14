package happybit.parser;

import happybit.command.Command;
import happybit.command.DoneHabitCommand;
import happybit.exception.HaBitParserException;

public class DoneParser {
    private static final String ERROR_INVALID_DESCRIPTION = "Please provide the goal and habit index"
            + " of the habit to be marked as done";
    private static final String ERROR_EXTRA_PARAMETERS = "Please provide only 2 integers";
    private static final String ERROR_MISSING_HABIT_INDEX = "The habit index is missing.";
    private static final String ERROR_INVALID_GOAL_INDEX = "Please enter a valid integer for the goal index";
    private static final String ERROR_INVALID_HABIT_INDEX = "Please enter a valid integer for the habit index";
    private static final int GOAL_INDEX = 0;
    private static final int HABIT_INDEX = 1;

    public static Command parseDoneHabitCommand(String commandInstruction) throws HaBitParserException  {
        // write parse code
        if (commandInstruction == null) {
            throw new HaBitParserException(ERROR_INVALID_DESCRIPTION);
        }

        String[] descriptions = commandInstruction.split("\\s");
        if (descriptions.length > 2) {
            throw new HaBitParserException(ERROR_EXTRA_PARAMETERS);
        }

        if (descriptions.length < 2) {
            throw new HaBitParserException(ERROR_MISSING_HABIT_INDEX);
        }

        // get goal Index
        int goalIndex;
        try {
            goalIndex = Integer.parseInt(descriptions[GOAL_INDEX]) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_GOAL_INDEX);
        }

        // get goal Index
        int habitIndex;
        try {
            habitIndex = Integer.parseInt(descriptions[HABIT_INDEX]) - 1;
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_INVALID_HABIT_INDEX);
        }

        return new DoneHabitCommand(goalIndex, habitIndex);
    }

}
