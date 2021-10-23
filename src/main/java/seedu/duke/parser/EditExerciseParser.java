package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.exercise.EditExerciseCommand;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.exception.GetJackDException;

import java.util.ArrayList;
import java.util.Arrays;

public class EditExerciseParser extends Parser {
    private static int workoutIndex;

    public EditExerciseParser(String userInputString) {
        this.userInputString = userInputString;
    }

    public static int getWorkoutIndex() {
        return workoutIndex;
    }

    private Command prepareEditExercise(String commandArgs) {
        try {
            int[] indices = parseWorkoutAndExerciseIndex(commandArgs);
            int exerciseIndex = indices[0];
            workoutIndex = indices[1];

            // String excluding the exerciseIndex and workoutIndex
            String[] params = commandArgs.split(PARAMETER_SEPARATOR);
            ArrayList<String> paramsWithoutIndices = new ArrayList<>(Arrays.asList(params).subList(2, params.length));

            String newArgs = String.join(PARAMETER_SEPARATOR, paramsWithoutIndices);
            String[] exerciseArgs = getExerciseArgs(newArgs, true);

            String newDescription = exerciseArgs[0].trim();
            int newSets = parseArgsAsIndex(exerciseArgs[1]);
            int newReps = parseArgsAsIndex(exerciseArgs[2]);

            return new EditExerciseCommand(exerciseIndex, workoutIndex, newDescription, newSets, newReps);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + EditExerciseCommand.MESSAGE_USAGE);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareEditExercise(commandArgs);
    }
}
