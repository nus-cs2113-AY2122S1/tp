package seedu.duke.exceptions.workout;

public class WorkoutNullArgumentException extends WorkoutException {
    public String getMessage() {
        return "Please enter arguments in the format: "
                + "add workout [workout_description] /c [calories] /d [date] /t [time]"
                + System.lineSeparator() + "If [date] and [time] not specified, "
                + "the system's current date and time will be taken instead.";
    }
}
