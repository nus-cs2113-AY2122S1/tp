package seedu.duke.exceptions.schedule;

public class ScheduleNullArgumentException extends ScheduleException {
    @Override
    public String getMessage() {
        return "Please enter arguments in the format: add schedule [workout_description] "
                + "/d [dd/mm/yyyy] /t [hh:mm]" + System.lineSeparator() + "Add /a followed by "
                + "[activity_name]:[distance] "
                + "or [activity_name]:[sets]x[reps] to schedule a more detailed workout!";
    }
}
