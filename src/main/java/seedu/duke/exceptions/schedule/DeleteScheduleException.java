package seedu.duke.exceptions.schedule;

public class DeleteScheduleException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Failed to delete that scheduled workout! Please enter an Integer within range.";
    }
}
