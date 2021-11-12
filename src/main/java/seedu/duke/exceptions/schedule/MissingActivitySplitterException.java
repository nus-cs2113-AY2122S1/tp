package seedu.duke.exceptions.schedule;

public class MissingActivitySplitterException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Missing activity splitter \":\" detected." + System.lineSeparator()
                + "Please enter [activity name]:[sets]x[reps] "
                + "or [activity name]:[distance in metres] for your workout activities";
    }
}
