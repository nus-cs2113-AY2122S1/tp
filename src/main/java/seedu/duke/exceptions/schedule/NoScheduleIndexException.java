package seedu.duke.exceptions.schedule;

public class NoScheduleIndexException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Please enter the schedule index in the format: delete schedule [index]";
    }
}
