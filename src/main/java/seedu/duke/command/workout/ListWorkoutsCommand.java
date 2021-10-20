package seedu.duke.command.workout;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;

/**
 * To list all Workouts
 * Does not show the exercises in each workout.
 */
public class ListWorkoutsCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_EMPTY_WORKOUT_LIST = "Empty workout list";

    public ListWorkoutsCommand() {
    }

    /**
     * Executes list workout command to list all the workouts from the workout list.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) {
        ArrayList<Workout> allWorkouts = workouts.getAllWorkouts();

        if (allWorkouts.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_WORKOUT_LIST);
        }
        return new CommandResult("Workout list:", allWorkouts);
    }
}
