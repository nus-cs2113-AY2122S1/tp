package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.exception.GetJackDException;

/**
 * Parses and processes input for the done command.
 */
public class MarkExerciseAsDoneParser extends Parser {

    public MarkExerciseAsDoneParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareMarkExerciseAsDone(String commandArgs) {
        try {
            int[] indices = parseWorkoutAndExerciseIndex(commandArgs);
            int exerciseIndex = indices[0];
            int workoutIndex = indices[1];

            return new MarkExerciseAsDoneCommand(workoutIndex, exerciseIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + MarkExerciseAsDoneCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareMarkExerciseAsDone(commandArgs);
    }
}
