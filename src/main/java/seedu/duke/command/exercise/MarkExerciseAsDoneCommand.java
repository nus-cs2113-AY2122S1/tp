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
 * Sets isDone attribute in an exercise Object to true.
 */
public class MarkExerciseAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "done: Marks the exercise in the workout workout as complete.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX " + EXERCISE_KEYWORD + "EXERCISE_INDEX\n"
            + "\tExample: " + COMMAND_WORD + " " + WORKOUT_KEYWORD + "3 " + EXERCISE_KEYWORD + "2";
    public static final String MESSAGE_SUCCESS = "Completed: %s";
    private static final Logger LOGGER = Logger.getLogger(MarkExerciseAsDoneCommand.class.getName());

    private final int workoutIndex;
    private final int exerciseIndex;

    /**
     * Instantiates object and sets workoutIndex and exerciseIndex.
     *
     * @param workoutIndex  display index of Workout that the exercise is in
     * @param exerciseIndex display index of exercise to remove
     */
    public MarkExerciseAsDoneCommand(int workoutIndex, int exerciseIndex) {
        this.workoutIndex = workoutIndex;
        this.exerciseIndex = exerciseIndex;
        assert workoutIndex >= 0;
        assert exerciseIndex >= 0;
        setupLogger(LOGGER);
    }

    /**
     * Executes mark exercise command to mark the given exercise as done in the list.
     *
     * @param workouts is the list of Workouts
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     * @throws GetJackDException if there is an invalid index used or an error occurs within the storage
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        try {
            Exercise toMarkDone = workouts.getWorkout(workoutIndex).getExercise(exerciseIndex);
            toMarkDone.setDone();

            LOGGER.info("Set workout " + workoutIndex + ", exercise " + exerciseIndex + " done");

            ui.showToUser(String.format(MESSAGE_SUCCESS, toMarkDone));

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Set exercise as done failed - exercise not found");
            throw new GetJackDException(ERROR_MESSAGE_EXERCISE_NOT_FOUND);
        }
    }
}
