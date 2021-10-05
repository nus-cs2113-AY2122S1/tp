package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.tasks.Exercise;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

public class DisplayExercisesCommand extends Command{
    public static final String COMMAND_WORD = "display";
    public static final String MESSAGE_USAGE = "display: Displays all exercises in the particular workout.\n"
            + "\tParameters: w/WORKOUT_INDEX\n"
            + "\tExample: display w/3\n";
    public static final String MESSAGE_LIST_EXERCISES = "Exercises in %1$s\n";
    public static final String MESSAGE_EMPTY_WORKOUT = "You have no workouts.\n";
    private final int workoutIndex;
    public DisplayExercisesCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        Workout workout = workouts.getWorkout(workoutIndex);
        ArrayList<Exercise> exercises = workout.getAllExercises();
        if (exercises.isEmpty()) {
            System.out.println(MESSAGE_EMPTY_WORKOUT);
        }
        else {
            System.out.println(String.format(MESSAGE_LIST_EXERCISES, workout));
            ui.showExercisesToUser(exercises);
        }
    }
}
