package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.tasks.Exercise;
import seedu.duke.ui.Ui;

import static seedu.duke.parser.Parser.EXERCISE_KEYWORD;
import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

public class RemoveExerciseCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes the exercise from the workout.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX " + EXERCISE_KEYWORD + "EXERCISE_INDEX\n"
            + "\tExample: remove " + WORKOUT_KEYWORD + "1 " + EXERCISE_KEYWORD + "3\n";
    public static final String MESSAGE_SUCCESS = "Removed exercise %1$s";
    private final int workoutIndex;
    private final int exerciseIndex;

    public RemoveExerciseCommand(int workoutIndex, int exerciseIndex) {
        this.workoutIndex = workoutIndex;
        this.exerciseIndex = exerciseIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        try {
            Exercise toRemove = workouts.getWorkout(workoutIndex).removeExercise(exerciseIndex);
            ui.showToUser(String.format(MESSAGE_SUCCESS, toRemove.toString()));
            String jsonString = storage.convertToJSON(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            ui.showToUser(MESSAGE_EXERCISE_NOT_FOUND);
        }
    }
}
