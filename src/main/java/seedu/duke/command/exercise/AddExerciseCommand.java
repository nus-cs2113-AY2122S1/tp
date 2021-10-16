package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.exercises.Exercise;
import seedu.duke.ui.Ui;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;
import static seedu.duke.parser.Parser.PARAMETER_SEPARATOR;

/**
 * For adding a new exercise to a workout.
 */
public class AddExerciseCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an exercise to a workout.\n"
            + "Format: add [exercise description], [sets and reps], [workout index]\n"
            + "Parameters:\n"
            + "\tSets and reps: \"5 10\" - 5 sets of 10 reps\n"
            + "\tWorkout index: Index of workout to add exercise to\n\n"
            + "Example: " + COMMAND_WORD + " Push-ups" + PARAMETER_SEPARATOR + "5 10" + PARAMETER_SEPARATOR + "1";
    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";

    private static final Logger LOGGER = Logger.getLogger(AddExerciseCommand.class.getName());
    private final Exercise toAdd;
    private final int workoutIndex;

    /**
     * Instantiates object and sets parameters for adding exercise to Workout.
     *
     * @param workoutIndex is the display index of Workout in the WorkoutList
     * @param description  is the description of exercise
     * @param sets         is the number of sets of exercise
     * @param reps         is the number of repetitions per set
     */
    public AddExerciseCommand(int workoutIndex, String description, int sets, int reps) {
        this.toAdd = new Exercise(description, sets, reps);
        this.workoutIndex = workoutIndex;
        setupLogger(LOGGER);
    }

    /**
     * Executes add exercise command to add the given exercise to the exercise list.
     *
     * @param workouts is the list of Workouts
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     * @throws GetJackDException if sets or reps are less than or equal to 0 or an error occurs within the storage
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        if (toAdd.getReps() <= 0 || toAdd.getSets() <= 0) {
            LOGGER.info("Add exercise failed - sets or reps <= 0");
            throw new GetJackDException("Sets or reps must be more than 0.");
        }

        try {
            workouts.getWorkout(workoutIndex).addExercise(toAdd);
            ui.showToUser(String.format(MESSAGE_SUCCESS, toAdd));

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            LOGGER.info("Add exercise failed - Workout not found");
            throw new GetJackDException(ERROR_MESSAGE_WORKOUT_NOT_FOUND);
        }
    }
}
