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
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX\n"
            + "\tExample: " + COMMAND_WORD + " " + WORKOUT_KEYWORD + "1";
    public static final String MESSAGE_SUCCESS = "Deleted workout: %1$s";
    private static final Logger LOGGER = Logger.getLogger(DeleteWorkoutCommand.class.getName());

    public int getWorkoutIndex() {
        return workoutIndex;
    }

    private final int workoutIndex;

    public DeleteWorkoutCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
        setupLogger(LOGGER);
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        try {
            Workout toDelete = workouts.removeWorkout(workoutIndex);
            ui.showToUser(String.format(MESSAGE_SUCCESS,toDelete));
            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("delete workout failed - workout not found");
            throw new GetJackDException(ERROR_MESSAGE_WORKOUT_NOT_FOUND);
        }
    }
}
