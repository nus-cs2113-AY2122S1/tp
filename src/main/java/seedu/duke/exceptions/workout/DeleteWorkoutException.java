package seedu.duke.exceptions.workout;

public class DeleteWorkoutException extends WorkoutException {
    @Override
    public String getMessage() {
        return "Failed to delete that workout! Please enter an Integer within range.";
    }
}
