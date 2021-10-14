package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.exercise.RemoveExerciseCommand;
import seedu.duke.exception.GetJackDException;

/**
 * Parses and processes input for the remove command.
 */
public class RemoveExerciseParser extends Parser {

    public RemoveExerciseParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareRemoveExercise(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);
            int exerciseIndex = parseArgsAsIndex(indices[1]);

            return new RemoveExerciseCommand(workoutIndex, exerciseIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + RemoveExerciseCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareRemoveExercise(commandArgs);
    }
}
