package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class ListWorkoutsCommand extends Command{
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_USAGE = "list: Lists all workouts.\n"
            + "\tExample: list\n";
    public static final String MESSAGE_LIST_WORKOUTS = "Workout list: \n";
    public static final String MESSAGE_EMPTY_WORKOUT_LIST = "Empty workout list\n";

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        ArrayList<Workout> allWorkouts = workouts.getAllWorkouts();
        if (allWorkouts.isEmpty()) {
            System.out.println(MESSAGE_EMPTY_WORKOUT_LIST);
        }
        else {
            System.out.println(MESSAGE_LIST_WORKOUTS);
            ui.showWorkoutsToUser(allWorkouts);
        }
    }
}
