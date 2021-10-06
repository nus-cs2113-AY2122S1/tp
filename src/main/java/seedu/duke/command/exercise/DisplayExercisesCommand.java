package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.exercises.Exercise;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

public class DisplayExercisesCommand extends Command {
    public static final String COMMAND_WORD = "display";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all exercises in the particular workout.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX\n"
            + "\tExample: display " + WORKOUT_KEYWORD + "3\n";
    public static final String MESSAGE_LIST_EXERCISES = "Exercises in %1$s";
    public static final String MESSAGE_EMPTY_WORKOUT = "You have no workouts.";
    private final int workoutIndex;

    public DisplayExercisesCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        Workout workout = workouts.getWorkout(workoutIndex);
        ArrayList<Exercise> exercises = workout.getAllExercises();
        if (exercises.isEmpty()) {
            ui.showToUser(MESSAGE_EMPTY_WORKOUT);
        } else {
            ui.showToUser(String.format(MESSAGE_LIST_EXERCISES, workout));
            ui.showExercisesToUser(exercises);
        }
    }
}
