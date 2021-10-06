package seedu.duke.parser;


import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.command.Command;
import seedu.duke.command.exercise.DisplayExercisesCommand;
import seedu.duke.command.workout.ListWorkoutsCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;
import seedu.duke.command.exercise.RemoveExerciseCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.IncorrectCommand;

import seedu.duke.exception.GetJackDException;

import java.util.Locale;

/**
 * To make sense of user commands by extracting keywords, descriptions and time/date.
 */
public class Parser {
    public static final String WORKOUT_KEYWORD = "/w";
    public static final String EXERCISE_KEYWORD = "/e";
    public static final String SETS_KEYWORD = "/s";
    public static final String REPS_KEYWORD = "/r";
    private static final String MESSAGE_INVALID_COMMAND = "Invalid command\n";

    public Command parseCommand(String userInputString) {

        final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        final String commandType = commandTypeAndParams[0].trim().toLowerCase(Locale.ROOT);
        final String commandArgs = commandTypeAndParams[1].trim();

        switch (commandType) {
        case DisplayExercisesCommand.COMMAND_WORD:
            return prepareDisplayExercises(commandArgs);
        case ListWorkoutsCommand.COMMAND_WORD:
            return new ListWorkoutsCommand();
        case AddExerciseCommand.COMMAND_WORD:
            return prepareAddExercise(commandArgs);
        case CreateWorkoutCommand.COMMAND_WORD:
            return prepareCreateWorkout(commandArgs);
        case DeleteWorkoutCommand.COMMAND_WORD:
            return prepareDeleteWorkout(commandArgs);
        case MarkExerciseAsDoneCommand.COMMAND_WORD:
            return prepareMarkExerciseAsDone(commandArgs);
        case RemoveExerciseCommand.COMMAND_WORD:
            return prepareRemoveExercise(commandArgs);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND);
        }
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

    private Command prepareCreateWorkout(String commandArgs) {
        if (commandArgs.contains(WORKOUT_KEYWORD)) {
            String workoutName = commandArgs.replace(WORKOUT_KEYWORD, "").trim();
            if (workoutName.isEmpty()) {
                return new IncorrectCommand(MESSAGE_INVALID_COMMAND + CreateWorkoutCommand.MESSAGE_USAGE);
            }
            return new CreateWorkoutCommand(workoutName);
        }
        return new IncorrectCommand(MESSAGE_INVALID_COMMAND + CreateWorkoutCommand.MESSAGE_USAGE);
    }

    private Command prepareDeleteWorkout(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);
            return new DeleteWorkoutCommand(workoutIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + DeleteWorkoutCommand.MESSAGE_USAGE);
        }
    }

    private Command prepareRemoveExercise(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);
            int exerciseIndex = parseArgsAsIndex(indices[1]);
            return new RemoveExerciseCommand(workoutIndex, exerciseIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + RemoveExerciseCommand.MESSAGE_USAGE);
        }
    }

    private Command prepareAddExercise(String commandArgs) {
        try {
            String[] exerciseArgs = getExerciseArgs(commandArgs);
            int workoutIndex = parseArgsAsIndex(exerciseArgs[0]);
            String exerciseName = exerciseArgs[1].trim();
            int sets = parseArgsAsIndex(exerciseArgs[2]);
            int reps = parseArgsAsIndex(exerciseArgs[3]);
            return new AddExerciseCommand(workoutIndex, exerciseName, sets, reps);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + AddExerciseCommand.MESSAGE_USAGE);
        }
    }

    private static String[] getExerciseArgs(String commandArgs) throws GetJackDException {
        String args = commandArgs.replace(WORKOUT_KEYWORD, "").trim();
        String[] workoutIndexAndExerciseArgs = splitCommandWordsAndArgs(args, EXERCISE_KEYWORD);
        String workoutIndex = workoutIndexAndExerciseArgs[0].trim();
        String[] nameAndSetsReps = splitCommandWordsAndArgs(workoutIndexAndExerciseArgs[1].trim(), SETS_KEYWORD);
        String exerciseName = nameAndSetsReps[0].trim();
        String[] setsAndReps = splitCommandWordsAndArgs(nameAndSetsReps[1].trim(), REPS_KEYWORD);
        String sets = setsAndReps[0].trim();
        String reps = setsAndReps[1].trim();

        if (workoutIndex.isEmpty()) {
            throw new GetJackDException("Error. Empty workout index");
        }
        if (exerciseName.isEmpty()) {
            throw new GetJackDException("Error. Empty exercise name.");
        }
        if (sets.isEmpty() || reps.isEmpty()) {
            throw new GetJackDException("Error. Empty sets or reps.");
        }
        return new String[]{workoutIndex, exerciseName, sets, reps};
    }


    private Command prepareMarkExerciseAsDone(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);
            int exerciseIndex = parseArgsAsIndex(indices[1]);
            return new MarkExerciseAsDoneCommand(workoutIndex, exerciseIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + MarkExerciseAsDoneCommand.MESSAGE_USAGE);
        }
    }

    private String[] getWorkoutAndExerciseIndices(String commandArgs) {
        if (!commandArgs.contains(WORKOUT_KEYWORD)) {
            return new String[]{"", ""};
        }
        if (!commandArgs.contains(EXERCISE_KEYWORD)) {
            String workoutIndex = commandArgs.replace(WORKOUT_KEYWORD, "").trim();
            return new String[]{workoutIndex, ""};
        }
        String[] args = splitCommandWordsAndArgs(commandArgs, EXERCISE_KEYWORD);
        String workoutIndex = args[0].replace(WORKOUT_KEYWORD, "").trim();
        String exerciseIndex = args[1].trim();
        return new String[]{workoutIndex, exerciseIndex};
    }

    private static String[] splitCommandWordsAndArgs(String input, String keyword) {
        final String[] split = input.trim().split(keyword, 2);
        if (split.length == 2) {
            return split;
        }
        return new String[]{split[0].trim(), ""};
    }

    private static int parseArgsAsIndex(String index) throws GetJackDException {
        if (index.isEmpty()) {
            throw new GetJackDException("Error. Workout or exercise index not found. ");
        }
        try {
            return Integer.parseInt(index.trim());
        } catch (NumberFormatException e) {
            throw new GetJackDException("Error. Invalid workout or exercise index.");
        }
    }
}
