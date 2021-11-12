package seedu.duke.exceptions.workout;

public class NoWorkoutIndexException extends WorkoutException {
    @Override
    public String getMessage() {
        return "Please enter the workout index in the format: delete workout [index]";
    }
}
