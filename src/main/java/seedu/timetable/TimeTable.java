package seedu.timetable;

import seedu.module.Module;
import seedu.ui.TimetableUI;

import java.util.ArrayList;

public class TimeTable {

    private static final int DEFAULT_START = 9;
    private static final int DEFAULT_END = 12;

    private int semester;
    private int earliestHour;
    private int latestHour;

    private ArrayList<Module> modules;

    private Class[] monday = new Class[24];
    private Class[] tuesday = new Class[24];
    private Class[] wednesday = new Class[24];
    private Class[] thursday = new Class[24];
    private Class[] friday = new Class[24];
    private Class[] saturday = new Class[24];
    private Class[] sunday = new Class[24];

    public TimeTable(int semester) {
        this.modules = new ArrayList<>();
        this.semester = semester;
        this.earliestHour = DEFAULT_START;
        this.latestHour = DEFAULT_END;
    }

    public void addClass(Class modClass) {

        switch (modClass.getDayOfWeek()) {
        case MONDAY:
            addClassToSchedule(modClass, monday);
            break;
        case TUESDAY:
            addClassToSchedule(modClass, tuesday);
            break;
        case WEDNESDAY:
            addClassToSchedule(modClass, wednesday);
            break;
        case THURSDAY:
            addClassToSchedule(modClass, thursday);
            break;
        case FRIDAY:
            addClassToSchedule(modClass, friday);
            break;
        case SATURDAY:
            addClassToSchedule(modClass, saturday);
            break;
        case SUNDAY:
            addClassToSchedule(modClass, sunday);
            break;
        default:
            break;
        }

        if (modClass.getStartHour() < earliestHour) {
            earliestHour = modClass.getStartHour();
        }

        if (modClass.getEndHour() > latestHour) {
            latestHour = modClass.getEndHour();
        }
    }

    private void addClassToSchedule(Class modClass, Class[] schedule) {
        int start = modClass.getStartHour();
        int end = modClass.getEndHour();
        for (int i = start; i < end; i++) {
            schedule[i] = modClass;
        }
        addModuleToList(modClass.getModule());
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