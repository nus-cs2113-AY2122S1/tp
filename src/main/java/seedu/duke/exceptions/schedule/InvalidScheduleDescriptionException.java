package seedu.duke.exceptions.schedule;

public class InvalidScheduleDescriptionException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Invalid schedule description detected!" + System.lineSeparator()
                + "Please enter a valid description for your workout!";
    }
}
