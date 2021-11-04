package happybit.parser;

import happybit.command.Command;
import happybit.command.SetCommand;
import happybit.exception.HaBitParserException;

public class SetParser extends Parser {

    /**
     * Parses user input to set a goal as the default view in main interface.
     *
     * @param commandInstruction User input containing command parameters.
     * @return Command to set a goal as default view.
     * @throws HaBitParserException If command parameters are not defined, or defined improperly.
     */
    public static Command parseSetGoalCommand(String commandInstruction) throws HaBitParserException {
        checkNoDescription(commandInstruction);
        String[] parameters = splitInput(commandInstruction);
        int goalIndex = getNumber(parameters, FLAG_GOAL_INDEX) - 1;
        return new SetCommand(goalIndex);
    }

}
