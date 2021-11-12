package seedu.duke.exceptions.workout;

public class NegativeWorkoutCalorieException extends WorkoutException {
    @Override
    public String getMessage() {
        return "Negative calories detected... Please enter a positive integer for your calories burned";
    }
}
