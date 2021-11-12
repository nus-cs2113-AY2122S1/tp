package seedu.duke.exceptions.workout;

public class MissingWorkoutCalorieSeparatorException extends WorkoutException {
    @Override
    public String getMessage() {
        return "CLI.ckFit is having difficulties finding the calorie separator /c" + System.lineSeparator()
                + "Please minimally have the format: add workout [workout_description] /c [calories]"
                + System.lineSeparator() + "Do remember to put spaces between your separators!";
    }
}
