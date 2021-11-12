package seedu.duke.exceptions.schedule;

public class MissingActivityQuantifierException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Missing activity quantifier \"x\" detected." + System.lineSeparator()
                + "Please enter your [sets]x[reps] for your non-distance based workout activities.";
    }
}
