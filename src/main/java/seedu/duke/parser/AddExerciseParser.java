package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.exception.GetJackDException;

//@@author qqkoh

/**
 * Parses and processes input for the add command.
 */
public class AddExerciseParser extends Parser {

    public AddExerciseParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareAddExercise(String commandArgs) {
        try {
            String[] exerciseArgs = getExerciseArgs(commandArgs, false);
            String exerciseName = exerciseArgs[0].trim();
            int sets = parseArgsAsIndex(exerciseArgs[1]);
            int reps = parseArgsAsIndex(exerciseArgs[2]);
            int workoutIndex = parseArgsAsIndex(exerciseArgs[3]);
            return new AddExerciseCommand(workoutIndex, exerciseName, sets, reps);
        } catch (GetJackDException e) {
            if (Command.workoutMode != 0) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                        + AddExerciseCommand.MESSAGE_USAGE_WORKOUT_MODE);
            }
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + AddExerciseCommand.MESSAGE_USAGE_MAIN);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareAddExercise(commandArgs);
    }
}
