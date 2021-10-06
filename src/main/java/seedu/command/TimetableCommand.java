package seedu.command;

import seedu.timetable.Timetable;

public class TimetableCommand extends Command {

    private Timetable timeTable;

    public TimetableCommand(Timetable timeTable) {
        this.timeTable = timeTable;
    }

    public void execute() {
        timeTable.showTimetable();
    }
}
