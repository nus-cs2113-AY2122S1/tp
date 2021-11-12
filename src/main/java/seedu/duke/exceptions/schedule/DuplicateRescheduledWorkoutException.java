package seedu.duke.exceptions.schedule;

public class DuplicateRescheduledWorkoutException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Failed to reschedule that scheduled workout. " + System.lineSeparator()
                + "A duplicate scheduled workout with the same arguments is already in your schedule.";
    }
}
