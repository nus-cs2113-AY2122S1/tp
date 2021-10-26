package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;
import seedu.duke.exception.GetJackDException;

//@@author JMattChiam

/**
 * Parses and processes input for the delete command.
 */
public class DeleteWorkoutParser extends Parser {

    public DeleteWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareDeleteWorkout(String commandArgs) {
        try {
            int workoutIndex = parseWorkoutIndex(commandArgs);
            return new DeleteWorkoutCommand(workoutIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + DeleteWorkoutCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareDeleteWorkout(commandArgs);
    }
}
