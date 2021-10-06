package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.tasks.Exercise;
import seedu.duke.ui.Ui;

public class AddExerciseCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_USAGE = "add: Adds an exercise to a workout.\n"
            + "\tParameters: w/WORKOUT_INDEX e/EXERCISE_NAME s/NUMBER_OF_SETS r/NUMBER_OF_REPS\n"
            + "\tExample: add w/3 e/push ups s/3 r/10\n";
    public static final String MESSAGE_SUCCESS = "New exercise added: %1$s";

    private final Exercise toAdd;
    private final int workoutIndex;

    public AddExerciseCommand(int workoutIndex, String description, int sets, int reps) {
        this.toAdd = new Exercise(description, sets, reps);
        this.workoutIndex = workoutIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        try {
            workouts.getWorkout(workoutIndex).addExercise(toAdd);
            ui.showToUser(String.format(MESSAGE_SUCCESS, toAdd));
            String jsonString = storage.convertToJSON(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            ui.showToUser(MESSAGE_WORKOUT_NOT_FOUND);
        }
    }
}
