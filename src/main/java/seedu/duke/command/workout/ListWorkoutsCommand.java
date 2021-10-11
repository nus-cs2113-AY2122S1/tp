package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * To list all Workouts
 * Does not show the exercises in each workout.
 */
public class ListWorkoutsCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_LIST_WORKOUTS = "Workout list: ";
    public static final String MESSAGE_EMPTY_WORKOUT_LIST = "Empty workout list";

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        ArrayList<Workout> allWorkouts = workouts.getAllWorkouts();
        if (allWorkouts.isEmpty()) {
            ui.showToUser(MESSAGE_EMPTY_WORKOUT_LIST);
        } else {
            ui.showWorkoutsToUser(allWorkouts);
        }
    }
}
