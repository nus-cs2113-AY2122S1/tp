package seedu.duke.exceptions.schedule;

public class DuplicateScheduledWorkoutException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Failed to add that scheduled workout. " + System.lineSeparator()
                + "A duplicate scheduled workout with the same arguments is already in your schedule.";
    }
}
