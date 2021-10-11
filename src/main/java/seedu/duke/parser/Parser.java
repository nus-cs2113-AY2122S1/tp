package seedu.duke.parser;


import seedu.duke.command.HelpCommand;
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
    public static final String WORKOUT_KEYWORD = "/w ";
    public static final String EXERCISE_KEYWORD = "/e ";
    public static final String SETS_KEYWORD = "/s ";
    public static final String REPS_KEYWORD = "/r ";
    static final String MESSAGE_INVALID_COMMAND = "Invalid command format\n";

    /**
     * Converts raw user input string to a command.
     *
     * @param userInputString raw user input string
     * @return command
     */
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
        case HelpCommand.COMMAND_WORD:
            return prepareHelpMessage(commandArgs);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new IncorrectCommand("Invalid Command");
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

    private Command prepareHelpMessage(String commandArgs) {
        switch (commandArgs) {
        case DisplayExercisesCommand.COMMAND_WORD:
            return new HelpCommand(DisplayExercisesCommand.MESSAGE_USAGE);
        case ListWorkoutsCommand.COMMAND_WORD:
            return new HelpCommand(ListWorkoutsCommand.MESSAGE_USAGE);
        case AddExerciseCommand.COMMAND_WORD:
            return new HelpCommand(AddExerciseCommand.MESSAGE_USAGE);
        case CreateWorkoutCommand.COMMAND_WORD:
            return new HelpCommand(CreateWorkoutCommand.MESSAGE_USAGE);
        case DeleteWorkoutCommand.COMMAND_WORD:
            return new HelpCommand(DeleteWorkoutCommand.MESSAGE_USAGE);
        case MarkExerciseAsDoneCommand.COMMAND_WORD:
            return new HelpCommand(MarkExerciseAsDoneCommand.MESSAGE_USAGE);
        case RemoveExerciseCommand.COMMAND_WORD:
            return new HelpCommand(RemoveExerciseCommand.MESSAGE_USAGE);
        case ExitCommand.COMMAND_WORD:
            return new HelpCommand(ExitCommand.MESSAGE_USAGE);
        default:
            return new HelpCommand();
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

    /**
     * Gets arguments required for an exercise, such as workoutIndex, exerciseName, sets and reps.
     *
     * @param commandArgs user input without the command word.
     * @return string array containing workoutIndex, exerciseName, sets and reps.
     * @throws GetJackDException if any of the above-mentioned arguments are empty.
     */
    private static String[] getExerciseArgs(String commandArgs) throws GetJackDException {
        String args = commandArgs.replace(WORKOUT_KEYWORD, "").trim();
        assert (!args.contains(WORKOUT_KEYWORD));

        String[] workoutIndexAndExerciseArgs = splitCommandWordsAndArgs(args, EXERCISE_KEYWORD);
        assert (!workoutIndexAndExerciseArgs[0].contains(EXERCISE_KEYWORD));
        assert (!workoutIndexAndExerciseArgs[1].contains(EXERCISE_KEYWORD));

        String workoutIndex = workoutIndexAndExerciseArgs[0].trim();

        String[] nameAndSetsReps = splitCommandWordsAndArgs(workoutIndexAndExerciseArgs[1].trim(), SETS_KEYWORD);
        assert (!nameAndSetsReps[0].contains(SETS_KEYWORD));
        assert (!nameAndSetsReps[1].contains(SETS_KEYWORD));

        String exerciseName = nameAndSetsReps[0].trim();

        String[] setsAndReps = splitCommandWordsAndArgs(nameAndSetsReps[1].trim(), REPS_KEYWORD);
        assert (!setsAndReps[0].contains(REPS_KEYWORD));
        assert (!setsAndReps[1].contains(REPS_KEYWORD));

        String sets = setsAndReps[0].trim();
        String reps = setsAndReps[1].trim();

        String[] exerciseArgs = new String[]{workoutIndex, exerciseName, sets, reps};
        for (String s : exerciseArgs) {
            assert (!s.contains(WORKOUT_KEYWORD));
            assert (!s.contains(EXERCISE_KEYWORD));
            assert (!s.contains(SETS_KEYWORD));
            assert (!s.contains(REPS_KEYWORD));
        }

        if (workoutIndex.isEmpty()) {
            throw new GetJackDException("Error. Empty workout index");
        }
        if (exerciseName.isEmpty()) {
            throw new GetJackDException("Error. Empty exercise name.");
        }
        if (sets.isEmpty() || reps.isEmpty()) {
            throw new GetJackDException("Error. Empty sets or reps.");
        }
        return exerciseArgs;
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

    /**
     * Gets workout and exercise indices.
     *
     * @param commandArgs raw input string without the command word.
     * @return String array of size 2. If there is no workout index, empty String array is returned.
     * If there is a workout index but no exercise index, only workout index is returned.
     * Otherwise, both workout index and exercise index are returned.
     */
    private String[] getWorkoutAndExerciseIndices(String commandArgs) {
        if (!commandArgs.contains(WORKOUT_KEYWORD)) {
            return new String[]{"", ""};
        }
        if (!commandArgs.contains(EXERCISE_KEYWORD)) {
            String workoutIndex = commandArgs.replace(WORKOUT_KEYWORD, "").trim();
            return new String[]{workoutIndex, ""};
        }
        assert (commandArgs.contains(WORKOUT_KEYWORD));
        assert (commandArgs.contains(EXERCISE_KEYWORD));

        String[] args = splitCommandWordsAndArgs(commandArgs, EXERCISE_KEYWORD);
        String workoutIndex = args[0].replace(WORKOUT_KEYWORD, "").trim();
        String exerciseIndex = args[1].trim();

        assert (!workoutIndex.contains(WORKOUT_KEYWORD));
        assert (!workoutIndex.contains(EXERCISE_KEYWORD));
        assert (!exerciseIndex.contains(WORKOUT_KEYWORD));
        assert (!exerciseIndex.contains(EXERCISE_KEYWORD));

        return new String[]{workoutIndex, exerciseIndex};
    }

    /**
     * Given a string and a keyword, this method splits the string around the keyword into 2.
     *
     * @param input   String
     * @param keyword keyword that we want to split the string around.
     * @return String array of size 2, none of the elements in the array contain the keyword.
     */
    private static String[] splitCommandWordsAndArgs(String input, String keyword) {
        final String[] split = input.trim().split(keyword, 2);
        if (split.length == 2) {
            return split;
        }
        return new String[]{split[0].trim(), ""};
    }

    /**
     * Parses a string as an integer.
     *
     * @param index String that we want to parse as an integer.
     * @return integer obtained from the String
     * @throws GetJackDException if index is empty, or index cannot be parsed as integer.
     */
    private static int parseArgsAsIndex(String index) throws GetJackDException {
        if (index.isEmpty()) {
            throw new GetJackDException("Error. Workout or exercise index not found.");
        }
        try {
            return Integer.parseInt(index.trim());
        } catch (NumberFormatException e) {
            throw new GetJackDException("Error. Invalid workout or exercise index.");
        }
    }
}
