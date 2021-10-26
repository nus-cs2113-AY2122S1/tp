package seedu.command;

import seedu.timetable.Timetable;

public class TimetableCommand extends Command {

    private Timetable timeTable;
    private boolean showUserItemsOnly;

    public TimetableCommand(Timetable timeTable, boolean showUserItemsOnly) {
        this.timeTable = timeTable;
        this.showUserItemsOnly = showUserItemsOnly;
    }

    public void execute() {
        timeTable.showTimetable(showUserItemsOnly);
    }
}
