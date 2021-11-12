package seedu.duke.exceptions.workout;

public class MissingWorkoutDescriptionException extends WorkoutException {
    @Override
    public String getMessage() {
        return "I am sorry... it appears the description is missing." + System.lineSeparator()
                + "Please enter a workout description!";
    }
}
