package seedu.duke.exceptions.schedule;

public class ScheduleException extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred in ScheduleTracker";
    }
}
