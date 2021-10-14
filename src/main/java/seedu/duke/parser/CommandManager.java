package seedu.duke.parser;

import seedu.duke.command.Command;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.HelpCommand;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.exercise.AddExerciseCommand;
import seedu.duke.command.exercise.DisplayExercisesCommand;
import seedu.duke.command.exercise.MarkExerciseAsDoneCommand;
import seedu.duke.command.exercise.RemoveExerciseCommand;
import seedu.duke.command.workout.CreateWorkoutCommand;
import seedu.duke.command.workout.DeleteWorkoutCommand;
import seedu.duke.command.workout.ListWorkoutsCommand;
import seedu.duke.exception.GetJackDException;

import java.util.Locale;

public class Generator extends Parser {
    public static final String WORKOUT_KEYWORD = "/w ";
    static final String MESSAGE_INVALID_COMMAND = "Invalid command format\n";

    /**
     * Converts raw user input string to a command.
     *
     * @param userInputString raw user input string
     * @return command
     */
    public Command generateCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordsAndArgs(userInputString, "\\s+");
        final String commandType = commandTypeAndParams[0].trim().toLowerCase(Locale.ROOT);
        final String commandArgs = commandTypeAndParams[1].trim();

        switch (commandType) {
        case ListWorkoutsCommand.COMMAND_WORD:
            return new ListWorkoutsCommand();
        case CreateWorkoutCommand.COMMAND_WORD:
            return prepareCreateWorkout(commandArgs);
        case DeleteWorkoutCommand.COMMAND_WORD:
            return prepareDeleteWorkout(commandArgs);
        case DisplayExercisesCommand.COMMAND_WORD:
            return prepareDisplayExercises(commandArgs);
        case AddExerciseCommand.COMMAND_WORD:
            return prepareAddExercise(commandArgs);
        case RemoveExerciseCommand.COMMAND_WORD:
            return prepareRemoveExercise(commandArgs);
        case MarkExerciseAsDoneCommand.COMMAND_WORD:
            return prepareMarkExerciseAsDone(commandArgs);
        case HelpCommand.COMMAND_WORD:
            return prepareHelpMessage(commandArgs);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            return new IncorrectCommand("Invalid Command");
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

    private Command prepareDisplayExercises(String commandArgs) {
        try {
            String[] indices = getWorkoutAndExerciseIndices(commandArgs);
            int workoutIndex = parseArgsAsIndex(indices[0]);

            return new DisplayExercisesCommand(workoutIndex);
        } catch (GetJackDException e) {
            return new IncorrectCommand(MESSAGE_INVALID_COMMAND + DisplayExercisesCommand.MESSAGE_USAGE);
        }
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
}
