package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.GetJackDException;
import seedu.duke.storage.Storage;

/**
 * Clears all the exercises present in the mentioned workoutIndex and all workouts in the application.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all exercises in a workout / "
            + "all workouts in the list.\n"
            + "Format: clear [exercise/workout]\n"
            + "Example: " + COMMAND_WORD + " exercise";

    private int workoutIndex;
    private final String keyword;

    /**
     * Instantiates object and sets parameters for clearing workouts.
     *
     * @param keyword is the argument containing the word "workout"
     */
    public ClearCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Instantiates object and sets parameters for clearing exercises in a specified workout.
     *
     * @param workoutIndex index of Workout that the exercise is in
     * @param keyword is the argument containing the word "exercise"
     */
    public ClearCommand(int workoutIndex, String keyword) {
        this.workoutIndex = workoutIndex;
        this.keyword = keyword;
    }

    /**
     * Executes clear command to remove all the exercises in a workout of given index and to remove all workouts.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     * @throws GetJackDException if command cannot be executed correctly
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        if (keyword.equals("workout")) {
            workouts.removeAllWorkout();
            return new CommandResult("All workouts have been cleared!");
        } else if (keyword.equals("exercise")) {
            workouts.getWorkout(workoutIndex).removeAllExercise();
            return new CommandResult("All exercises in workout " + workoutIndex + " have been cleared!");
        }
        throw new GetJackDException("Invalid command - Unable to execute!");
    }
}
