package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Exercise;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Level;
import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;
import static seedu.duke.parser.Parser.PARAMETER_SEPARATOR;

//@@author qqkoh

/**
 * For adding a new exercise to a workout.
 */
public class AddExerciseCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE_MAIN = COMMAND_WORD + ": Adds an exercise to a workout.\n"
            + "Format: add [exercise description], [sets and reps], [workout index]\n"
            + "Parameters:\n"
            + "\tSets and reps: \"5 10\" - 5 sets of 10 reps\n"
            + "\tWorkout index: Index of workout to add exercise to\n"
            + "Example: " + COMMAND_WORD + " Push-ups" + PARAMETER_SEPARATOR + "5 10" + PARAMETER_SEPARATOR + "1";

    public static final String MESSAGE_USAGE_WORKOUT_MODE = COMMAND_WORD
            + ": Adds an exercise to the workout the user is currently in.\n"
            + "Format: add [exercise description], [sets and reps]\n"
            + "Parameters:\n"
            + "\tSets and reps: \"5 10\" - 5 sets of 10 reps\n"
            + "Example: " + COMMAND_WORD + " Push-ups" + PARAMETER_SEPARATOR + "5 10";

    public static final String MESSAGE_SUCCESS = "New exercise added to workout %d : %s";

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
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     * @throws GetJackDException if sets or reps are less than or equal to 0 or an error occurs within the storage
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        if (toAdd.getReps() <= 0 || toAdd.getSets() <= 0) {
            LOGGER.log(Level.SEVERE, "Add exercise failed - sets or reps <= 0");
            throw new GetJackDException("Sets or reps must be more than 0.");
        }

        workouts.getWorkout(workoutIndex).addExercise(toAdd);

        String jsonString = storage.convertToJson(workouts);
        storage.saveData(jsonString);

        return new CommandResult(String.format(MESSAGE_SUCCESS, workoutIndex, toAdd));
    }

    public int getWorkoutIndex() {
        return workoutIndex;
    }
}
