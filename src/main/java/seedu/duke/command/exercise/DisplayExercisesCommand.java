package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.exercises.Exercise;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

/**
 * To print exercises in a specified workout.
 */
public class DisplayExercisesCommand extends Command {
    public static final String COMMAND_WORD = "display";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all exercises in the particular workout.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX\n"
            + "\tExample: " + COMMAND_WORD + " " + WORKOUT_KEYWORD + "3";
    public static final String MESSAGE_DISPLAY_EXERCISES = "Exercises in %s";
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
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     * @throws GetJackDException if there is an invalid index used
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        Workout workout = workouts.getWorkout(workoutIndex);
        ArrayList<Exercise> exercises = workout.getAllExercises();

        if (exercises.isEmpty()) {
            ui.showToUser(MESSAGE_EMPTY_WORKOUT);
        } else {
            String displayMessage = String.format(MESSAGE_DISPLAY_EXERCISES, workout.getWorkoutName());
            ui.showItemListToUser(displayMessage, exercises, true);
        }
    }
}
