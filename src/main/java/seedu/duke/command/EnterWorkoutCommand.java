package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * To enter into a Workout.
 */
public class EnterWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "enter";
    private static final String NL = System.lineSeparator();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enter into a Workout to edit its exercises"
            + NL + "Parameters: WORKOUT_INDEX"
            + NL + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_SUCCESS = "Now inside Workout: %1$s";
    private static final Logger LOGGER = Logger.getLogger(EnterWorkoutCommand.class.getName());
    private final int workoutIndex;

    /**
     * Instantiates object and sets workoutIndex.
     *
     * @param workoutIndex display index of workout we want to enter.
     */
    public EnterWorkoutCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
        setupLogger(LOGGER);
    }

    public int getWorkoutIndex() {
        return workoutIndex;
    }

    /**
     * Executes enter workout command to enter into the workout of the given index from the workout list.
     *
     * @param workouts is the list of Workouts
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     * @throws GetJackDException if there is an invalid index used
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        if (workoutIndex <= 0 || workoutIndex > workouts.getAllWorkouts().size()) {
            LOGGER.log(Level.SEVERE, "Error in entering Workout - Workout doesn't exist");
            throw new GetJackDException("Invalid Workout Index");
        }
        workoutMode = workoutIndex;
        LOGGER.info("Entering the " + workoutIndex + "th(st/nd/rd) Workout");
        ui.showToUser(String.format(MESSAGE_SUCCESS, workouts.getAllWorkouts().get(workoutIndex - 1)));
    }
}
