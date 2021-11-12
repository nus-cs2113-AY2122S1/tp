package seedu.duke.exceptions.schedule;

public class MissingScheduleDescriptionException extends ScheduleException {
    @Override
    public String getMessage() {
        return "I am sorry... it appears the description is missing." + System.lineSeparator()
                + "Please enter a description for your scheduled workout!";
    }
}
