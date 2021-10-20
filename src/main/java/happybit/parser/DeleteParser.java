package happybit.parser;

import happybit.command.Command;
import happybit.command.DeleteGoalCommand;
import happybit.command.DeleteHabitCommand;
import happybit.exception.HaBitParserException;

public class DeleteParser {

    private static final String ERROR_BLANK = "No instruction given.";
    private static final String ERROR_NOT_NUM = "Expected a number.";
    private static final String ERROR_HABIT_NOT_EXIST = "Habit index does not exist.";
    private static final String ERROR_INCOMPLETE = "Instruction incomplete or improper.";

    /**
     * Parses detail from user to figure out which goal to be deleted.
     *
     * @param commandInstruction Details from the user.
     * @return A Command class with the goalIndex information.
     * @throws HaBitParserException If commandInstruction is blank or does not have a number for goalIndex.
     */
    public static Command parseDeleteGoalCommand(String commandInstruction) throws HaBitParserException  {
        if (commandInstruction == null) {
            throw new HaBitParserException(ERROR_BLANK);
        }

        try {
            int goalIndex;
            goalIndex = Integer.parseInt(commandInstruction) - 1;
            assert (goalIndex > 0) : "Index of goal should be 1 or higher.";
            return new DeleteGoalCommand(goalIndex);

            //todo: if goal is a name
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_NOT_NUM);
        }

    }

    /**
     * Parses detail from user to figure out which habit to be deleted.
     *
     * @param commandInstruction Details from the user.
     * @return A Command class with the goalIndex and habitIndex information.
     * @throws HaBitParserException If commandInstruction is blank, missing information, or not numbers.
     */
    public static Command parseDeleteHabitCommand(String commandInstruction) throws HaBitParserException {
        if (commandInstruction == null) {
            throw new HaBitParserException(ERROR_BLANK);
        }

        try {
            int goalIndex;
            int habitIndex;
            String[] numbers = commandInstruction.split(" ");

            if (numbers.length != 2) {
                throw new HaBitParserException(ERROR_INCOMPLETE);
            }

            goalIndex = Integer.parseInt(numbers[0]) - 1;
            habitIndex = Integer.parseInt(numbers[1]) - 1;
            assert (goalIndex > 0) : "Index of goal should be 1 or higher.";
            assert (habitIndex > 0) : "Index of habit should be 1 or higher.";
            return new DeleteHabitCommand(goalIndex, habitIndex);

        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_NOT_NUM);
        }
    }

}
