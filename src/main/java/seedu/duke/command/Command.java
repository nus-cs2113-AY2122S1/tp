package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Parent class for all Command classes, which execute commands from user to operate on Workout and WorkoutList.
 */
public abstract class Command {
    public static int workoutMode = 0;
    public static final String ERROR_MESSAGE_WORKOUT_NOT_FOUND = "Error. Workout not found\n";
    public static final String ERROR_MESSAGE_EXERCISE_NOT_FOUND = "Error. Exercise not found\n";
    public static String MESSAGE_USAGE;

    /**
     * Abstract method to execute the Command's main functionality.
     *
     * @param workouts is the list of Workouts
     * @param ui       is a user-interface object
     * @param storage  is a storage object
     * @throws GetJackDException if command cannot be executed correctly
     */
    public abstract void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException;
}
