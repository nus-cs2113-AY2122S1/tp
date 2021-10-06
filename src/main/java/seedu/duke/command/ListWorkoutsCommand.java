package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ListWorkoutsCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "list: Lists all workouts.\n"
            + "\tExample: list\n";
    public static final String MESSAGE_LIST_WORKOUTS = "Workout list: ";
    public static final String MESSAGE_EMPTY_WORKOUT_LIST = "Empty workout list";

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        ArrayList<Workout> allWorkouts = workouts.getAllWorkouts();
        if (allWorkouts.isEmpty()) {
            ui.showToUser(MESSAGE_EMPTY_WORKOUT_LIST);
        } else {
            ui.showToUser(MESSAGE_LIST_WORKOUTS);
            ui.showWorkoutsToUser(allWorkouts);
        }
    }
}
