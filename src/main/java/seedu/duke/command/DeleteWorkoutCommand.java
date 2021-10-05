package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.ui.Ui;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "delete: Deletes the workout corresponding to the workout index.\n"
            + "\tParameters: w/WORKOUT_INDEX\n"
            + "\tExample: delete w/1\n";
    public static final String MESSAGE_SUCCESS = "Deleted workout: %1$s\n";

    private final int workoutIndex;

    public DeleteWorkoutCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui) throws GetJackDException {
        try {
            workouts.removeWorkout(workoutIndex);
            System.out.println(MESSAGE_SUCCESS);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MESSAGE_WORKOUT_NOT_FOUND);
        }
    }
}
