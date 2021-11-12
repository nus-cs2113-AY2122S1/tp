package seedu.duke.exceptions.schedule;

public class GetActivityException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Error getting activity quantifiers.";
    }
}
