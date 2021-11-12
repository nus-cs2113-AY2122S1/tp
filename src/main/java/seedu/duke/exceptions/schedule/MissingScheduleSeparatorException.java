package seedu.duke.exceptions.schedule;

public class MissingScheduleSeparatorException extends ScheduleException {
    @Override
    public String getMessage() {
        return "CLI.ckFit is having difficulties finding the separators..." + System.lineSeparator()
                + "Please enter in the format: add schedule [workout_description] /d [dd/mm/yyyy] /t [hh:mm]"
                + System.lineSeparator() + "Do remember to put spaces between your separators.";
    }
}
