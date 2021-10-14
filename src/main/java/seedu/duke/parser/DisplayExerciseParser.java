package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.exercise.DisplayExercisesCommand;
import seedu.duke.exception.GetJackDException;


/**
 * Parses and processes input for the display command.
 */
public class DisplayExerciseParser extends Parser {

    public DisplayExerciseParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareDisplayExercises(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);

            return new DisplayExercisesCommand(workoutIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + DisplayExercisesCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareDisplayExercises(commandArgs);
    }
}
