package seedu.timetable;

import seedu.module.Module;
import seedu.ui.TimetableUI;

import java.util.ArrayList;

public class Timetable {

    private static final int DEFAULT_START = 9;
    private static final int DEFAULT_END = 12;

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

    public void addClass(TimetableLesson modTimetableLesson) {

        switch (modTimetableLesson.getDayOfWeek()) {
        case MONDAY:
            addClassToSchedule(modTimetableLesson, monday);
            break;
        case TUESDAY:
            addClassToSchedule(modTimetableLesson, tuesday);
            break;
        case WEDNESDAY:
            addClassToSchedule(modTimetableLesson, wednesday);
            break;
        case THURSDAY:
            addClassToSchedule(modTimetableLesson, thursday);
            break;
        case FRIDAY:
            addClassToSchedule(modTimetableLesson, friday);
            break;
        case SATURDAY:
            addClassToSchedule(modTimetableLesson, saturday);
            break;
        case SUNDAY:
            addClassToSchedule(modTimetableLesson, sunday);
            break;
        default:
            break;
        }

        if (modTimetableLesson.getStartHour() < earliestHour) {
            earliestHour = modTimetableLesson.getStartHour();
        }

        if (modTimetableLesson.getEndHour() > latestHour) {
            latestHour = modTimetableLesson.getEndHour();
        }
    }

    private void addClassToSchedule(TimetableLesson modTimetableLesson, TimetableLesson[] schedule) {
        int start = modTimetableLesson.getStartHour();
        int end = modTimetableLesson.getEndHour();
        for (int i = start; i < end; i++) {
            schedule[i] = modTimetableLesson;
        }
        addModuleToList(modTimetableLesson.getModule());
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