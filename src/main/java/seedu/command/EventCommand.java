package seedu.command;

import seedu.timetable.Timetable;
import seedu.timetable.TimetableUserItem;

import java.time.DayOfWeek;

public class EventCommand extends Command {
    private final TimetableUserItem task;
    private final Timetable tt;

    public EventCommand(TimetableUserItem event, Timetable timetable) {
        this.task = event;
        this.tt = timetable;
    }

    @Override
    public void execute() {
        DayOfWeek date = task.getDayOfWeek();
        tt.addEvent(date, task);
    }
}
