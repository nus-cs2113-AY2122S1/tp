package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.exercise.EditExerciseCommand;
import seedu.duke.command.misc.IncorrectCommand;
import seedu.duke.exception.GetJackDException;

import java.util.ArrayList;
import java.util.Arrays;

//@@author KishorKumar11

/**
 * Parses and processes input for the edit command.
 */
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
            int[] indices = parseWorkoutAndExerciseIndex(commandArgs, true);
            int exerciseIndex = indices[0];
            workoutIndex = indices[1];

            // To obtain a string excluding the exerciseIndex and workoutIndex
            String[] params = commandArgs.split(PARAMETER_SEPARATOR);
            int separatorIndex = (params.length > 3) ? 2 : 1;
            ArrayList<String> paramsWithoutIndices = new ArrayList<>(Arrays.asList(params)
                    .subList(separatorIndex, params.length));
            String newArgs = String.join(PARAMETER_SEPARATOR, paramsWithoutIndices);

            // To extract the exercise description, sets and reps
            String[] exerciseArgs = getExerciseArgs(newArgs, true);

            String newDescription = exerciseArgs[0].trim();
            int newSets = parseArgsAsIndex(exerciseArgs[1]);
            int newReps = parseArgsAsIndex(exerciseArgs[2]);

            return new EditExerciseCommand(exerciseIndex, workoutIndex, newDescription, newSets, newReps);
        } catch (GetJackDException e) {
            if (Command.workoutMode != 0) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND
                        + EditExerciseCommand.MESSAGE_USAGE_WORKOUT_MODE);
            }
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + EditExerciseCommand.MESSAGE_USAGE_MAIN);
        }
    }

    @Override
    public Command parseInput() {
        String commandArgs = getCommandArguments(userInputString);
        return prepareEditExercise(commandArgs);
    }
}
