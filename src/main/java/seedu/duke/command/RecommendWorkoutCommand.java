package seedu.duke.command;

import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * To recommend workouts with exercises of a difficulty level provided by the user.
 */
public class RecommendWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "recommend";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Provides a given set of workouts with exercises.\n"
            + "Format: recommend [Workout level]\n"
            + "Parameters: \n"
            + "\tWorkout level - difficulty of workout [beginner/intermediate/pro]\n"
            + "Example: " + COMMAND_WORD + " beginner";
    private final String workoutLevel;

    /**
     * Instantiates object and sets parameters for recommending workouts based on workout difficulty.
     *
     * @param workoutLevel is the difficulty of the workouts.
     */
    public RecommendWorkoutCommand(String workoutLevel) {
        this.workoutLevel = workoutLevel;
    }

    /**
     * Executes recommend command to display the workouts of the given difficulty.
     *
     * @param workouts is the list of Workouts
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) {
        ui.showRecommendedWorkouts(workoutLevel);
    }
}
