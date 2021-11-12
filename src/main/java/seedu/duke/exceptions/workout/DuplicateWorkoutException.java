package seedu.duke.exceptions.workout;

public class DuplicateWorkoutException extends WorkoutException {
    @Override
    public String getMessage() {
        return "Failed to add that workout. " + System.lineSeparator()
                + "A duplicate workout with the same arguments is already in your list.";
    }
}
