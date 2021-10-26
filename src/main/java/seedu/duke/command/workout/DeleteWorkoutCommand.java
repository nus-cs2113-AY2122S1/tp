package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author qqkoh

/**
 * To delete a Workout.
 */
public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the workout corresponding to the workout index.\n"
            + "Format: delete [Workout index]\n"
            + "Parameters:\n"
            + "\tWorkout index - Index of workout to delete\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted workout: %s";

    private static final Logger LOGGER = Logger.getLogger(DeleteWorkoutCommand.class.getName());
    private final int workoutIndex;

    /**
     * Instantiates object and sets workoutIndex and exerciseIndex.
     *
     * @param workoutIndex display index of workout that we want to delete
     */
    public DeleteWorkoutCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
        assert workoutIndex >= 0;
        setupLogger(LOGGER);
    }

    public int getWorkoutIndex() {
        return workoutIndex;
    }

    /**
     * Executes delete workout command to delete the workout of the given index from the workout list.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     * @throws GetJackDException if there is an invalid index used or an error occurs within the storage
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        final Workout toDelete = workouts.removeWorkout(workoutIndex);
        workouts.sortWorkouts();

        String jsonString = storage.convertToJson(workouts);
        storage.saveData(jsonString);

        if (workoutMode == workoutIndex) {
            workoutMode = 0;
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }
}
