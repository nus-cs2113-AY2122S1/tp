package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.exception.GetJackDException;

//@@author JMattChiam

/**
 * Parses and processes input for the done command.
 */
public class MarkExerciseAsDoneParser extends Parser {

    public MarkExerciseAsDoneParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareMarkExerciseAsDone(String commandArgs) {
        try {
            int[] indices = parseWorkoutAndExerciseIndex(commandArgs, false);
            //returns array of length 2, exercise index at index 0 and workout index at index 1

            int exerciseIndex = indices[0];
            int workoutIndex = indices[1];

            return new MarkExerciseAsDoneCommand(workoutIndex, exerciseIndex);
        } catch (GetJackDException e) {
            if (Command.workoutMode != 0) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                        + MarkExerciseAsDoneCommand.MESSAGE_USAGE_WORKOUT_MODE);
            }
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                    + MarkExerciseAsDoneCommand.MESSAGE_USAGE_MAIN);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareMarkExerciseAsDone(commandArgs);
    }
}
