package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

//@@author KishorKumar11

/**
 * Parent class for all Command classes, which execute commands from user to operate on Workout and WorkoutList.
 */
public abstract class Command {
    public static final String ERROR_MESSAGE_EXERCISE_NOT_FOUND = "Error. Exercise not found\n";
    public static int workoutMode = 0;
    public static String MESSAGE_USAGE;

    /**
     * Abstract method to execute the Command's main functionality.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @throws GetJackDException if command cannot be executed correctly
     */
    public abstract CommandResult executeUserCommand(WorkoutList workouts, Storage storage)
            throws GetJackDException;
}
