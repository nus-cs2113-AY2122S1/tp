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

    /**
     * Gets arguments required for an exercise, such as workoutIndex, exerciseName, sets and reps.
     * commandArgs passed in as [exercise description], [sets and reps], [workout index or name]
     * @param commandArgs user input without the command word.
     * @return string array containing workoutIndex, exerciseName, sets and reps.
     * @throws GetJackDException if any of the above-mentioned arguments are empty.
     */
    static String[] getExerciseArgs(String commandArgs) throws GetJackDException {
        if (!commandArgs.contains(PARAMETER_SEPARATOR)) {
            throw new GetJackDException("Invalid format for add exercise.");
        }

        String[] arguments = commandArgs.split(PARAMETER_SEPARATOR);
        if (arguments.length < 3) {
            LOGGER.info("Missing exercise arguments");
            throw new GetJackDException("Error. Missing exercise parameters");
        }

        String exerciseDescription = arguments[0];
        String[] setsAndReps = arguments[1].split(" ");
        String sets = setsAndReps[0];
        String reps = setsAndReps[1];
        String workoutIdentifier = arguments[2];

        String[] exerciseArgs = new String[] {exerciseDescription, sets, reps, workoutIdentifier};
        for (String s : exerciseArgs) {
            assert (!s.contains(PARAMETER_SEPARATOR));
        }

        return exerciseArgs;
    }

    private Command prepareAddExercise(String commandArgs) {
        try {
            String[] exerciseArgs = getExerciseArgs(commandArgs);
            String exerciseName = exerciseArgs[0].trim();
            int sets = parseArgsAsIndex(exerciseArgs[1]);
            int reps = parseArgsAsIndex(exerciseArgs[2]);
            int workoutIndex = parseArgsAsIndex(exerciseArgs[3]);

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
