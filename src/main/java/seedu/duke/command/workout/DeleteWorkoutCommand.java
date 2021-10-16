package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;
import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

/**
 * To delete a Workout.
 */
public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the workout corresponding to the workout index.\n"
            + "Format: delete [Workout index]\n"
            + "Parameters:\n"
            + "Workout index - Index of workout to delete\n\n"
            + "Example: " + COMMAND_WORD + " " + WORKOUT_KEYWORD + "1";
    public static final String MESSAGE_SUCCESS = "Deleted workout: %1$s";
    private static final Logger LOGGER = Logger.getLogger(DeleteWorkoutCommand.class.getName());
    private final int workoutIndex;

    /**
     * Instantiates object and sets workoutIndex and exerciseIndex.
     *
     * @param workoutIndex display index of workout that we want to delete.
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
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     * @throws GetJackDException if there is an invalid index used or an error occurs within the storage
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        try {
            Workout toDelete = workouts.removeWorkout(workoutIndex);
            ui.showToUser(String.format(MESSAGE_SUCCESS, toDelete));

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("delete workout failed - workout not found");
            throw new GetJackDException(ERROR_MESSAGE_WORKOUT_NOT_FOUND);
        }
    }
}
