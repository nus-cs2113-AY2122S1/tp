package seedu.command;

import seedu.exceptions.UniModsException;
import seedu.timetable.Timetable;

public class ClearCommand extends Command {

    private Timetable timetable;

    public ClearCommand(Timetable timetable) {
        this.timetable = timetable;
    }

    public void execute() {
        try {
            timetable.clearTimetable();
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
        }
    }
}
