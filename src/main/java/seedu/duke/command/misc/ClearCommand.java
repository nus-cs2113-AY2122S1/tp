package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.WorkoutList;
import seedu.duke.exception.GetJackDException;
import seedu.duke.storage.Storage;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author KishorKumar11

/**
 * Clears all the exercises present in the mentioned workoutIndex and all workouts in the application.
 */
public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears all exercises in a workout / "
            + "all workouts in the list.\n"
            + "Format: clear [Keyword] [Workout index]\n"
            + "Parameters:\n"
            + "\tKeyword - exercise or workout\n"
            + "\tWorkout index - Index of workout to clear all exercises inside (only when keyword is \"exercise\")\n"
            + "Example: " + COMMAND_WORD + " exercise 1\n"
            + "Example: " + COMMAND_WORD + " workout";
    private static final Logger LOGGER = Logger.getLogger(ClearCommand.class.getName());
    private final String keyword;
    private int workoutIndex;

    /**
     * Instantiates object and sets parameters for clearing workouts.
     *
     * @param keyword is the argument containing the word "workout"
     */
    public ClearCommand(String keyword) {
        assert !keyword.isEmpty();
        this.keyword = keyword;
        setupLogger(LOGGER);
    }

    /**
     * Instantiates object and sets parameters for clearing exercises in a specified workout.
     *
     * @param workoutIndex index of Workout that the exercise is in
     * @param keyword      is the argument containing the word "exercise"
     */
    public ClearCommand(int workoutIndex, String keyword) {
        assert !keyword.isEmpty();
        assert workoutIndex >= 0;
        this.workoutIndex = workoutIndex;
        this.keyword = keyword;
        setupLogger(LOGGER);
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
            LOGGER.info("Removing all workouts");
            workouts.removeAllWorkout();
            if (workoutMode != 0) {
                workoutMode = 0;
                return new CommandResult("All workouts have been cleared! Returning to main view.");
            }
            return new CommandResult("All workouts have been cleared!");
        } else if (keyword.equals("exercise")) {
            LOGGER.info("Removing all exercises in workout " + workoutIndex);
            workouts.getWorkout(workoutIndex).removeAllExercise();
            return new CommandResult("All exercises in workout " + workoutIndex + " have been cleared!");
        }
        LOGGER.info("Clear command failed - invalid keyword");
        throw new GetJackDException("Invalid command - Unable to execute!");
    }
}
