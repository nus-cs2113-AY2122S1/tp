package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * Sets isDone attribute in an exercise Object to true.
 */
public class MarkExerciseAsDoneCommand extends Command {
    public static final String COMMAND_WORD = "done";
    public static final String MESSAGE_USAGE = "done: Marks the exercise in the workout workout as complete.\n"
            + "Format: done [Exercise index], [Workout index]\n"
            + "Parameters:\n"
            + "\tExercise index - Index of exercise to mark done\n"
            + "\tWorkout index - Index of workout containing exercise to mark done\n"
            + "Example: " + COMMAND_WORD + " 1, 2  - Mark exercise 1 from workout 2 as done";

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
     * @param storage  is a storage object
     * @throws GetJackDException if there is an invalid index used or an error occurs within the storage
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        try {
            Exercise toMarkDone = workouts.getWorkout(workoutIndex).getExercise(exerciseIndex);
            toMarkDone.setDone();

            LOGGER.info("Set workout " + workoutIndex + ", exercise " + exerciseIndex + " done");

            //ui.showToUser(String.format(MESSAGE_SUCCESS, toMarkDone));

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toMarkDone));

        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Set exercise as done failed - exercise not found");
            throw new GetJackDException(ERROR_MESSAGE_EXERCISE_NOT_FOUND);
        }
    }
}
