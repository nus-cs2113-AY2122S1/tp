package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import static seedu.duke.parser.Parser.WORKOUT_KEYWORD;

public class DeleteWorkoutCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the workout corresponding to the workout index.\n"
            + "\tParameters: " + WORKOUT_KEYWORD + "WORKOUT_INDEX\n"
            + "\tExample: delete " + WORKOUT_KEYWORD + "1\n";
    public static final String MESSAGE_SUCCESS = "Deleted workout: %1$s";

    private final int workoutIndex;

    public DeleteWorkoutCommand(int workoutIndex) {
        this.workoutIndex = workoutIndex;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        try {
            workouts.removeWorkout(workoutIndex);
            ui.showToUser(MESSAGE_SUCCESS);
            String jsonString = storage.convertToJSON(workouts);
            storage.saveData(jsonString);
        } catch (IndexOutOfBoundsException e) {
            ui.showToUser(MESSAGE_WORKOUT_NOT_FOUND);
        }
    }
}
