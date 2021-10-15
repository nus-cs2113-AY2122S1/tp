package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;
import seedu.duke.exception.GetJackDException;


/**
 * Parses and processes input for the delete command.
 */
public class DeleteWorkoutParser extends Parser {

    public DeleteWorkoutParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareDeleteWorkout(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);

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
