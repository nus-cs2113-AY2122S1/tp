package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Parent class for all Command classes, which execute commands from user to operate on Workout and WorkoutList.
 */
public abstract class Command {
    public static final String ERROR_MESSAGE_WORKOUT_NOT_FOUND = "Error. Workout not found\n";
    public static final String ERROR_MESSAGE_EXERCISE_NOT_FOUND = "Error. Exercise not found\n";
    public static String MESSAGE_USAGE;

    protected Command() {
    }

    /**
     * abstract method to execute the Command's main functionality.
     * @param workouts List of Workouts
     * @param ui User-Interface object
     * @param storage Storage object
     * @throws GetJackDException Thrown when command cannot be executed correctly
     */
    public abstract void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException;
}
