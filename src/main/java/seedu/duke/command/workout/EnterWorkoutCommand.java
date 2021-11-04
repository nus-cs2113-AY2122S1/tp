package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author IshaaanVyas

/**
 * To enter into a Workout.
 */
public class EnterWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "enter";
    public static final String MESSAGE_SUCCESS = "Now inside workout %d : %s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Enter into a Workout to edit its exercises"
            + "Format: enter [Workout index]\n"
            + "Parameters: \n"
            + "\tWorkout index - index of workout to enter\n"
            + "Example: " + COMMAND_WORD + " 1";
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
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     * @throws GetJackDException if there is an invalid index used
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        if (workoutIndex <= 0 || workoutIndex > workouts.getAllWorkouts().size()) {
            LOGGER.log(Level.SEVERE, "Error in entering Workout - Workout doesn't exist");
            throw new GetJackDException("Invalid Workout Index");
        }

        workoutMode = workoutIndex;
        LOGGER.info("Entering the " + workoutIndex + "th(st/nd/rd) Workout");
        Workout workoutEntered = workouts.getAllWorkouts().get(workoutIndex - 1);
        return new CommandResult(String.format(MESSAGE_SUCCESS, workoutIndex, workoutEntered));
    }
}
