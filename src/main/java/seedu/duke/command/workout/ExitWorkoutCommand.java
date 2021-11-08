package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author IshaaanVyas

/**
 * To exit back to Main View.
 */
public class ExitWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "back";
    public static final String MESSAGE_SUCCESS = "Back to Main View";
    private static final String NL = System.lineSeparator();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Go back to main view"
            + NL + "Example: " + COMMAND_WORD;
    private static final Logger LOGGER = Logger.getLogger(EnterWorkoutCommand.class.getName());

    /**
     * Instantiates object.
     */
    public ExitWorkoutCommand() {
        setupLogger(LOGGER);
    }

    /**
     * Executes exit workout command to exit out from the current workout.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) {
        workoutMode = 0;
        LOGGER.info("Exiting to main view");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
