package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.exercise.RemoveExerciseCommand;
import seedu.duke.exception.GetJackDException;

//@@author qqkoh

/**
 * Parses and processes input for the remove command.
 */
public class RemoveExerciseParser extends Parser {

    public RemoveExerciseParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareRemoveExercise(String commandArgs) {
        try {
            int[] indices = parseWorkoutAndExerciseIndex(commandArgs, false);
            int exerciseIndex = indices[0];
            int workoutIndex = indices[1];

            return new RemoveExerciseCommand(workoutIndex, exerciseIndex);
        } catch (GetJackDException e) {
            if (Command.workoutMode != 0) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                        + RemoveExerciseCommand.MESSAGE_USAGE_WORKOUT_MODE);
            }
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                    + RemoveExerciseCommand.MESSAGE_USAGE_MAIN);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareRemoveExercise(commandArgs);
    }
}
