package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * To recommend workouts with exercises of a difficulty level provided by the user.
 */
public class RecommendWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "recommend";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Provides a given set of workouts with exercises.\n"
            + "Format: recommend [Workout level]\n"
            + "Parameters:\n"
            + "\tWorkout level - difficulty of workout [beginner/intermediate/pro]\n"
            + "Example: " + COMMAND_WORD + " beginner";
    private static final Logger LOGGER = Logger.getLogger(RecommendWorkoutCommand.class.getName());
    private final String workoutLevel;

    /**
     * Instantiates object and sets parameters for recommending workouts based on workout difficulty.
     *
     * @param workoutLevel is the difficulty of the workouts
     */
    public RecommendWorkoutCommand(String workoutLevel) {
        assert workoutLevel != null;
        this.workoutLevel = workoutLevel;
        setupLogger(LOGGER);
    }

    /**
     * Executes recommend command to display the workouts of the given difficulty.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) {
        LOGGER.info("Showing the recommended " + workoutLevel + " Workouts");
        //ui.showRecommendedWorkouts(workoutLevel);
        return new CommandResult("recommend workout command");
    }
}
