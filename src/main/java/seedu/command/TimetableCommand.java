package seedu.command;

import seedu.timetable.TimeTable;

public class TimetableCommand extends Command {

    private TimeTable timeTable;

    public TimetableCommand(TimeTable timeTable) {
        this.timeTable = timeTable;
    }

    public void execute() {
        timeTable.showTimetable();
    }
}
