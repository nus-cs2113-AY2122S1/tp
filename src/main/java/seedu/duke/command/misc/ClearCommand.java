package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.GetJackDException;
import seedu.duke.storage.Storage;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all exercises in a workout / "
            + "all workouts in the list.\n"
            + "Format: clear [exercise/workout]\n"
            + "Example: " + COMMAND_WORD + " exercise";

    private int workoutIndex;
    private final String keyword;

    public ClearCommand(String keyword) {
        this.keyword = keyword;
    }

    public ClearCommand(int workoutIndex, String keyword) {
        this.workoutIndex = workoutIndex;
        this.keyword = keyword;
    }

    /**
     * Abstract method to execute the Command's main functionality.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
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
        return null;
    }
}
