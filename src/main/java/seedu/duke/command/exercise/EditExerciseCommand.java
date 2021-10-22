package seedu.duke.command.exercise;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

public class EditExerciseCommand extends Command {
    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the exercise in the workout.\n"
            + "Format: edit [exercise index], [workout index], [new Description], [sets and reps]\n"
            + "Parameters:\n"
            + "\tExercise index - Index of exercise to edit\n"
            + "\tWorkout index - Index of workout containing exercise to edit\n"
            + "Example: " + COMMAND_WORD + " 1, 2, Lunges, 5 10 - edit exercise 1 to Lunges of 5 sets and 10 reps "
            + "from workout 2";
    public static final String MESSAGE_SUCCESS = "The edited exercise: %1$s";

    private final String newDescription;

    private final int workoutIndex;
    private final int exerciseIndex;
    private final int newReps;
    private final int newSets;

    public EditExerciseCommand(int workoutIndex, int exerciseIndex, String newDescription, int newReps, int newSets) {
        this.workoutIndex = workoutIndex;
        this.exerciseIndex = exerciseIndex;
        this.newDescription = newDescription;
        this.newReps = newReps;
        this.newSets = newSets;

        assert workoutIndex >= 0;
        assert exerciseIndex >= 0;
    }

    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        try {
            Exercise toEdit = workouts.getWorkout(workoutIndex).getExercise(exerciseIndex);
            toEdit.setDescription(newDescription);
            toEdit.setReps(newReps);
            toEdit.setSets(newSets);

            String jsonString = storage.convertToJson(workouts);
            storage.saveData(jsonString);

            return new CommandResult(String.format(MESSAGE_SUCCESS, toEdit));
        } catch (NumberFormatException e) {
            throw new GetJackDException(ERROR_MESSAGE_EXERCISE_NOT_FOUND);
        }
    }
}
