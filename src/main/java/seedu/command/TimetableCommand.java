package seedu.command;

import seedu.timetable.Timetable;

public class TimetableCommand extends Command {

    public static final String commandSyntax = "timetable";
    public static final String commandAction =
            "Displays the timetable in a weekly grid format";

    private final Timetable timeTable;
    private final boolean showUserItemsOnly;

    public TimetableCommand(Timetable timeTable, boolean showUserItemsOnly) {
        this.timeTable = timeTable;
        this.showUserItemsOnly = showUserItemsOnly;
    }

    public void execute() {
        timeTable.showTimetable(showUserItemsOnly);
    }
}
