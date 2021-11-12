package seedu.duke.exceptions.workout;

public class WorkoutException extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred in WorkoutTracker";
    }
}
