package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.exercise.DisplayExercisesCommand;
import seedu.duke.exception.GetJackDException;

//@@author JMattChiam

/**
 * Parses and processes input for the display command.
 */
public class DisplayExerciseParser extends Parser {

    public DisplayExerciseParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareDisplayExercises(String commandArgs) {
        try {
            int workoutIndex = parseWorkoutIndex(commandArgs);
            return new DisplayExercisesCommand(workoutIndex);
        } catch (GetJackDException e) {
            if (Command.workoutMode != 0) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                        + DisplayExercisesCommand.MESSAGE_USAGE_WORKOUT_MODE);
            }
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                    + DisplayExercisesCommand.MESSAGE_USAGE_MAIN);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareDisplayExercises(commandArgs);
    }
}
