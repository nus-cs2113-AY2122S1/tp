package happybit.parser;

import happybit.command.Command;
import happybit.command.DeleteGoalCommand;
import happybit.command.DeleteHabitCommand;
import happybit.exception.HaBitParserException;

public class DeleteParser {

    private static final String ERROR_BLANK = "No instruction given.";
    private static final String ERROR_NOT_NUM = "Expected a number.";

    public static Command parseDeleteGoalCommand(String commandInstruction) throws HaBitParserException  {
        if (commandInstruction.isBlank()) {
            throw new HaBitParserException(ERROR_BLANK);
        }

        try {
            int goalIndex;
            goalIndex = Integer.parseInt(commandInstruction) - 1;
            return new DeleteGoalCommand(goalIndex);

            //todo: if goal is a name
        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_NOT_NUM);
        }

    }

    public static Command parseDeleteHabitCommand(String commandInstruction) throws HaBitParserException {
        if (commandInstruction.isBlank()) {
            throw new HaBitParserException(ERROR_BLANK);
        }

        try {
            int goalIndex;
            int habitIndex;
            String[] numbers = commandInstruction.split(" ");

            goalIndex = Integer.parseInt(numbers[0]) - 1;
            habitIndex = Integer.parseInt(numbers[1]) - 1;
            return new DeleteHabitCommand(goalIndex, habitIndex);

        } catch (NumberFormatException e) {
            throw new HaBitParserException(ERROR_NOT_NUM);
        }
    }

}
