package seedu.timetable;

import seedu.exceptions.UniModsException;
import seedu.module.Module;
import seedu.ui.TextUi;
import seedu.ui.TimetableUI;

import java.time.DayOfWeek;
import java.util.ArrayList;

/**
 * The Timetable Class, which will track all added modules and lessons that you have signed up for.
 * Each cell in the timetable will display the module code, lesson type and venue of the lesson
 * Each Timetable should be assigned a semester number --> Academic Year semester (1 OR 2).
 */
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

    /**
     * Creates a Timetable assigned to a specific semester of the Academic Year.
     *
     * @param semester Semester 1 OR 2 of the Academic Year
     */
    public Timetable(int semester) {
        this.modules = new ArrayList<>();
        this.semester = semester;
        this.earliestHour = DEFAULT_START;
        this.latestHour = DEFAULT_END;
    }

    /**
     * Adds a Timetable Lesson to the timetable
     * and adds the corresponding module to an internal list if not already added.
     * This can be a Lecture, Tutorial or Laboratory
     *
     * @param timetableLesson lesson to be added to the timetable
     * @see TimetableLesson
     */
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

    /**
     * Adds a lesson to a specific day schedule
     * E.g. addLessonToSchedule(lesson, monday) will add the lesson to the monday schedule
     *
     * @param timetableLesson Lesson to be added to a day's schedule
     * @param schedule        Day's schedule (i.e monday/tuesday/.. etc) to add the lesson to
     */
    private void addLessonToSchedule(TimetableLesson timetableLesson, TimetableLesson[] schedule) {
        int start = timetableLesson.getStartHour();
        int end = timetableLesson.getEndHour();
        for (int i = start; i < end; i++) {
            schedule[i] = timetableLesson;
        }
        addModuleToList(timetableLesson.getModule());
    }

    /**
     * Adds the lesson's module to the internal tracking list.
     * This is to be displayed later
     *
     * @param module Module to be added
     * @see Module
     */
    private void addModuleToList(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    /**
     * Deletes the module from the list of modules taken by the user.
     *
     * @param module Module to be Deleted
     * @see Module
     */
    public void deleteModuleFromList(Module module) throws UniModsException {
        String moduleCode = module.getModuleCode();
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getModuleCode().equals(moduleCode)) {
                modules.remove(modules.get(i));
                TextUi.printModuleDeleted(moduleCode);
                deleteFromLessons(module);
                return;
            }
        }
        throw new UniModsException(TextUi.ERROR_MODULE_NOT_FOUND);
    }

    /**
     * Calls the function deleteFromSchedule to delete the module from the timetable's daily plan.
     *
     * @param module Module to be Deleted
     * @see Module
     */
    public void deleteFromLessons(Module module) {
        deleteFromSchedule(module, monday);
        deleteFromSchedule(module, tuesday);
        deleteFromSchedule(module, wednesday);
        deleteFromSchedule(module, thursday);
        deleteFromSchedule(module, friday);
        deleteFromSchedule(module, saturday);
        deleteFromSchedule(module, sunday);

    }

    /**
     * Sets the TimetableLesson for the particular time slot to be null where the timetableLesson
     * is scheduled for the module to be deleted.
     * @param schedule Schedule contains the lessons of the user for a particular day of the week.
     * @param module   Module to be Deleted
     * @see Module
     */
    public void deleteFromSchedule(Module module, TimetableLesson[] schedule) {
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] != null && schedule[i].getModuleCode().equals(module.getModuleCode())) {
                schedule[i] = null;
            }
        }

    }

    /**
     * Removes all modules from the list of modules taken by the user.
     * Calls the clearTimetableFromLessons() function to delete all the scheduled lessons.
     */
    public void clearTimetable() throws UniModsException {
        if (modules.size() > 0) {
            modules.clear();
            clearTimetableFromLessons();
            TextUi.printTimetableCleared();
        } else {
            throw new UniModsException(TextUi.ERROR_EMPTY_TIMETABLE);
        }
    }


    /**
     * Calls the function clearTimetableFromLessons() to clear the scheduled lessons from the timetable's daily plan.
     */
    public void clearTimetableFromLessons() {
        clearSchedule(monday);
        clearSchedule(tuesday);
        clearSchedule(wednesday);
        clearSchedule(thursday);
        clearSchedule(friday);
        clearSchedule(saturday);
        clearSchedule(sunday);
    }
    
    /**
     * Clears the TimetableLesson for all non-null time slots .
     *
     * @param schedule Schedule contains the lessons of the user for a particular day of the week.
     */
    public void clearSchedule(TimetableLesson[] schedule) {
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] != null) {
                schedule[i] = null;
            }
        }
    }

    /**
     * Displays the timetable over Command Line Interface
     * Draws a grid where the Horizontal Axis represents the hour timing
     * and the Vertical Axis represents the Day (MON/TUES/WED/... etc.)
     * Displays the lessons in each grid cell that had been added to the timetable
     *
     * <p>Also displays all the modules taken and the total number of MCs taken for the
     * timetable
     */
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

    public TimetableLesson getLesson(DayOfWeek day, int startHour) {
        switch (day) {
        case MONDAY:
            return monday[startHour];
        case TUESDAY:
            return tuesday[startHour];
        case WEDNESDAY:
            return wednesday[startHour];
        case THURSDAY:
            return thursday[startHour];
        case FRIDAY:
            return friday[startHour];
        case SATURDAY:
            return saturday[startHour];
        case SUNDAY:
            return sunday[startHour];
        default:
            return null;
        }
    }

    public Integer getSemester() {
        return semester;
    }
}
