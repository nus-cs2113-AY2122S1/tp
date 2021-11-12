package seedu.duke.exceptions.schedule;

public class UnnecessaryQuantifierException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Unnecessary activity quantifier splitter \"x\" detected." + System.lineSeparator()
                + "Please enter [activity name]:[distance in metres] for distance based workout activities if your"
                + System.lineSeparator() + "activity name is either running/swimming/cycling."
                + System.lineSeparator() + "E.g. running:8000";
    }
}
