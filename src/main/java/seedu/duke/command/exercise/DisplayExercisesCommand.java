package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Exercise;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;

//@@author qqkoh

/**
 * To print exercises in a specified workout.
 */
public class DisplayExercisesCommand extends Command {
    public static final String COMMAND_WORD = "display";

    public static final String MESSAGE_USAGE_MAIN = COMMAND_WORD
            + ": Displays all exercises in the particular workout.\n"
            + "Format: display [Workout index]\n"
            + "Parameters:\n"
            + "\tWorkout index - index of workout to display exercises\n"
            + "Example: " + COMMAND_WORD + " 3";

    public static final String MESSAGE_USAGE_WORKOUT_MODE = COMMAND_WORD
            + ": Displays all exercises in the workout the user is currently in.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DISPLAY_EXERCISES = "Exercises in (%d) %s";
    public static final String MESSAGE_EMPTY_WORKOUT = "You have no exercises.";

    private final int workoutIndex;

    /**
     * Instantiates object and sets parameters for displaying all the exercises in a specified workout.
     *
     * @param workoutIndex display index of workout in workoutList
     */
    public DisplayExercisesCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
    }

    /**
     * Executes display exercise command to show all the exercises list.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     * @throws GetJackDException if there is an invalid index used
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        Workout workout = workouts.getWorkout(workoutIndex);
        ArrayList<Exercise> exercises = workout.getAllExercises();

        if (exercises.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_WORKOUT);
        } else {
            String displayMessage = String.format(MESSAGE_DISPLAY_EXERCISES, workoutIndex, workout);
            return new CommandResult(displayMessage, exercises);
        }
    }

    public int getWorkoutIndex() {
        return workoutIndex;
    }
}
