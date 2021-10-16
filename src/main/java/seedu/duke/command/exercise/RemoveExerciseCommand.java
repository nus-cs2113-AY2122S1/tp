package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.exercises.Exercise;
import seedu.duke.ui.Ui;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;
import static seedu.duke.parser.Parser.EXERCISE_KEYWORD;
import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

/**
 * To remove an exercise from a Workout.
 */
public class RemoveExerciseCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the exercise from the workout.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX " + EXERCISE_KEYWORD + "EXERCISE_INDEX\n"
            + "\tExample: " + COMMAND_WORD + " " + WORKOUT_KEYWORD + "1 " + EXERCISE_KEYWORD + "3";
    public static final String MESSAGE_SUCCESS = "Removed exercise: %s";
    private static final Logger LOGGER = Logger.getLogger(RemoveExerciseCommand.class.getName());

    private final int workoutIndex;
    private final int exerciseIndex;

    /**
     * Instantiates object and sets workoutIndex and exerciseIndex.
     *
     * @param workoutIndex  display index of Workout that the exercise is in
     * @param exerciseIndex display index of exercise to remove
     */
    public RemoveExerciseCommand(int workoutIndex, int exerciseIndex) {
        this.workoutIndex = workoutIndex;
        this.exerciseIndex = exerciseIndex;
        assert workoutIndex >= 0;
        assert exerciseIndex >= 0;
        setupLogger(LOGGER);
    }

    /**
     * Executes remove exercise command to remove an exercise of given index from the list.
     *
     * @param workouts is the list of Workouts
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     * @throws GetJackDException if there is an invalid index used or an error occurs within the storage
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        try {
            Exercise toRemove = workouts.getWorkout(workoutIndex).removeExercise(exerciseIndex);
            ui.showToUser(String.format(MESSAGE_SUCCESS, toRemove.toString()));

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("remove exercise failed - exercise not found");
            throw new GetJackDException(ERROR_MESSAGE_EXERCISE_NOT_FOUND);
        }
    }
}
