package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.exception.GetJackDException;

/**
 * Parses and processes input for the add command.
 */
public class AddExerciseParser extends Parser {

    public AddExerciseParser(String userInputString) {
        this.userInputString = userInputString;
    }

    private Command prepareAddExercise(String commandArgs) {
        try {
            String[] exerciseArgs = getExerciseArgs(commandArgs);
            String exerciseName = exerciseArgs[1].trim();
            int workoutIndex = parseArgsAsIndex(exerciseArgs[0]);
            int sets = parseArgsAsIndex(exerciseArgs[2]);
            int reps = parseArgsAsIndex(exerciseArgs[3]);

            return new AddExerciseCommand(workoutIndex, exerciseName, sets, reps);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + AddExerciseCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareAddExercise(commandArgs);
    }
}
