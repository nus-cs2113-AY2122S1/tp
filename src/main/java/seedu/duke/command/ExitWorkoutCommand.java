package seedu.duke.command;

import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

/**
 * To exit back to Main View.
 */
public class ExitWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "back";
    private static final String NL = System.lineSeparator();
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Go back to main view"
            + NL + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Back to Main View";
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
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     */
    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) {
        workoutMode = 0;
        LOGGER.info("Exiting to main view");
        ui.showToUser(MESSAGE_SUCCESS);
    }
}
