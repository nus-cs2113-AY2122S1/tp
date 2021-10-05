package seedu.timetable;

import seedu.module.Module;
import seedu.ui.TimetableUI;

import java.util.ArrayList;

public class Timetable {

    private static final int DEFAULT_START = 9;
    private static final int DEFAULT_END = 16;

    private int semester;
    private int earliestHour;
    private int latestHour;

    private ArrayList<Module> modules;

    private TimetableLesson[] monday = new TimetableLesson[24];
    private TimetableLesson[] tuesday = new TimetableLesson[24];
    private TimetableLesson[] wednesday = new TimetableLesson[24];
    private TimetableLesson[] thursday = new TimetableLesson[24];
    private TimetableLesson[] friday = new TimetableLesson[24];
    private TimetableLesson[] saturday = new TimetableLesson[24];
    private TimetableLesson[] sunday = new TimetableLesson[24];

    public Timetable(int semester) {
        this.modules = new ArrayList<>();
        this.semester = semester;
        this.earliestHour = DEFAULT_START;
        this.latestHour = DEFAULT_END;
    }

    public void addLesson(TimetableLesson timetableLesson) {

        switch (timetableLesson.getDayOfWeek()) {
        case MONDAY:
            addLessonToSchedule(timetableLesson, monday);
            break;
        case TUESDAY:
            addLessonToSchedule(timetableLesson, tuesday);
            break;
        case WEDNESDAY:
            addLessonToSchedule(timetableLesson, wednesday);
            break;
        case THURSDAY:
            addLessonToSchedule(timetableLesson, thursday);
            break;
        case FRIDAY:
            addLessonToSchedule(timetableLesson, friday);
            break;
        case SATURDAY:
            addLessonToSchedule(timetableLesson, saturday);
            break;
        case SUNDAY:
            addLessonToSchedule(timetableLesson, sunday);
            break;
        default:
            break;
        }

        if (timetableLesson.getStartHour() < earliestHour) {
            earliestHour = timetableLesson.getStartHour();
        }

        if (timetableLesson.getEndHour() > latestHour) {
            latestHour = timetableLesson.getEndHour();
        }
    }

    private void addLessonToSchedule(TimetableLesson timetableLesson, TimetableLesson[] schedule) {
        int start = timetableLesson.getStartHour();
        int end = timetableLesson.getEndHour();
        for (int i = start; i < end; i++) {
            schedule[i] = timetableLesson;
        }
        addModuleToList(timetableLesson.getModule());
    }

    private void addModuleToList(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    public void showTimetable() {
        TimetableUI.printScheduleHours(earliestHour, latestHour);
        TimetableUI.printDaySchedule("MON", monday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("TUE", tuesday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("WED", wednesday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("THU", thursday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("FRI", friday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("SAT", saturday, earliestHour, latestHour);
        TimetableUI.printDaySchedule("SUN", sunday, earliestHour, latestHour);
        TimetableUI.printModules(modules);
    }

}