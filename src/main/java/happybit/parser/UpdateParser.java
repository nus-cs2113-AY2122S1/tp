package happybit.parser;

import happybit.command.Command;
import happybit.command.UpdateGoalNameCommand;
import happybit.exception.HaBitParserException;

public class UpdateParser {

    private static final String ERROR_BLANK = "No instruction given.";
    private static final String ERROR_NOT_NUM = "Expected a number.";
    private static final String ERROR_INCOMPLETE = "Instruction incomplete or improper.";

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

}
