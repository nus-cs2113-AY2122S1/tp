package seedu.duke.exceptions.schedule;

public class InvalidActivityFormatException extends ScheduleException {
    @Override
    public String getMessage() {
        return "There was an issue getting your activity breakdown." + System.lineSeparator()
                + "Please enter a positive integer [distance in metres] for distance based "
                + "activities(swimming/running/cycling)." + System.lineSeparator() + "E.g. running:8000" + ""
                + System.lineSeparator() + "Enter two positive integers in the format [set]x[reps] for everything else."
                + System.lineSeparator() + "E.g. bench press:3x12" + System.lineSeparator()
                + "For multiple activities please separate them by \",\"";
    }
}
