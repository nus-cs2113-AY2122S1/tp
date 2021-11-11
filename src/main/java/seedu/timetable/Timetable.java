package seedu.timetable;

import seedu.exceptions.UniModsException;
import seedu.logger.TimetableLogger;
import seedu.module.Lesson;
import seedu.module.Module;
import seedu.ui.TextUi;
import seedu.ui.TimetableUI;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.logging.Level;

/**
 * The Timetable Class, which will track all added modules and lessons that you
 * have signed up for. Each cell in the timetable will display the module code,
 * lesson type and venue of the lesson Each Timetable should be assigned a
 * semester number --> Academic Year semester (1 OR 2).
 */
public class Timetable implements Comparable<Timetable> {

    private static final int HOUR = 100;
    private static final int TIME_LIMIT = 23;
    private static final int ONE = 1;
    private static final int DEFAULT_START = 9;
    private static final int DEFAULT_END = 16;
    private static final int ZERO = 0;

    private int semester;
    private int earliestHour;
    private int latestHour;

    private boolean isEmpty = false;

    private final ArrayList<TimetableUserItem> events;
    private final ArrayList<Module> modules;
    private final PriorityQueue<Integer> earliestHours; //Min heap
    private final PriorityQueue<Integer> latestHours;

    private final TimetableLogger logger = new TimetableLogger();

    private TimetableItem[] monday = new TimetableItem[24];
    private TimetableItem[] tuesday = new TimetableItem[24];
    private TimetableItem[] wednesday = new TimetableItem[24];
    private TimetableItem[] thursday = new TimetableItem[24];
    private TimetableItem[] friday = new TimetableItem[24];
    private TimetableItem[] saturday = new TimetableItem[24];
    private TimetableItem[] sunday = new TimetableItem[24];

    private final Map<DayOfWeek, TimetableItem[]> schedules = new HashMap<>();

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
        events = new ArrayList<>();
        earliestHours = new PriorityQueue(24);
        earliestHours.add(DEFAULT_START);
        latestHours = new PriorityQueue(24, Collections.reverseOrder());
        latestHours.add(DEFAULT_END);

        addSchedulesToMap();
        checkIfEmpty();
    }

    public Timetable(TimetableDto timetableDto) {
        this.semester = timetableDto.getSemester();
        this.earliestHour = timetableDto.getEarliestHour();
        this.latestHour = timetableDto.getLatestHour();
        this.earliestHours = new PriorityQueue<>(24);
        this.latestHours = new PriorityQueue(24, Collections.reverseOrder());
        this.modules = timetableDto.getModules();
        this.events = timetableDto.getEvents();
        this.monday = timetableDto.getMondayItems();
        this.tuesday = timetableDto.getTuesdayItems();
        this.wednesday = timetableDto.getWednesdayItems();
        this.thursday = timetableDto.getThursdayItems();
        this.friday = timetableDto.getFridayItems();
        this.saturday = timetableDto.getSaturdayItems();
        this.sunday = timetableDto.getSundayItems();

        addSchedulesToMap();
        checkIfEmpty();
        addHoursFromDto(timetableDto);
    }

    private void checkIfEmpty() {
        isScheduleEmpty(monday);
        isScheduleEmpty(tuesday);
        isScheduleEmpty(wednesday);
        isScheduleEmpty(thursday);
        isScheduleEmpty(friday);
        isScheduleEmpty(saturday);
        isScheduleEmpty(sunday);
    }

    private void addSchedulesToMap() {
        schedules.put(DayOfWeek.MONDAY, monday);
        schedules.put(DayOfWeek.TUESDAY, tuesday);
        schedules.put(DayOfWeek.WEDNESDAY, wednesday);
        schedules.put(DayOfWeek.THURSDAY, thursday);
        schedules.put(DayOfWeek.FRIDAY, friday);
        schedules.put(DayOfWeek.SATURDAY, saturday);
        schedules.put(DayOfWeek.SUNDAY, sunday);
    }

    private void addHoursFromDto(TimetableDto dto) {
        earliestHours.addAll(dto.getEarliestHours());
        latestHours.addAll(dto.getLatestHours());
    }

    private void isScheduleEmpty(TimetableItem[] schedule) {
        for (TimetableItem item : schedule) {
            if (item != null) {
                isEmpty = false;
                return;
            }
        }
    }

    /**
     * Adds a Timetable Lesson to the timetable and adds the corresponding module to
     * an internal list if not already added. This can be a Lecture, Tutorial or
     * Laboratory
     *
     * @param day  Day that item should be added to schedule
     * @param item Item that should be added to schedule
     * @see TimetableLesson
     */
    public void addItem(DayOfWeek day, TimetableItem item) {
        addItemToSchedule(item, schedules.get(day));
    }

    public void addEvent(DayOfWeek day, TimetableUserItem event) {
        addEventToSchedule(event, schedules.get(day));
        logger.log(Level.INFO, String.format("%s added to timetable", event.getTitle()));
    }

    public void addLesson(Module module, Integer semester, Lesson lesson) {
        TimetableLesson timetableLesson = new TimetableLesson(module, semester, lesson);
        addItem(timetableLesson.getDayOfWeek(), timetableLesson);
        addModuleToList(module);

        logger.log(Level.INFO, String.format("%s added to timetable",
                timetableLesson.getTitle() + ", " + timetableLesson.getLessonType()));
    }

    /**
     * Resizes the starting hour and ending hour of the displayed timetable.
     * If the item has an earlier starting hour, the starting hour = item's starting hour
     * And same with ending hour
     */
    private void adjustStartAndEndHours() {
        if (earliestHours.peek() != null) {
            earliestHour = earliestHours.peek();
        } else {
            earliestHour = DEFAULT_START;
        }

        if (latestHours.peek() != null) {
            latestHour = latestHours.peek();
        } else {
            latestHour = DEFAULT_END;
        }

        assert earliestHour < latestHour : "Earliest hour of the day is should be earlier than latest hour of the day";
    }


    /**
     * Adds a timetable item to a specific day schedule E.g.
     * addLessonToSchedule(lesson, monday) will add the lesson to the monday
     * schedule
     *
     * @param timetableItem Item to be added to a day's schedule
     * @param schedule      Day's schedule (i.e monday/tuesday/.. etc) to add the
     *                      lesson to
     */
    private void addItemToSchedule(TimetableItem timetableItem, TimetableItem[] schedule) {
        int start = timetableItem.getStartHour();
        int end = timetableItem.getEndHour();
        for (int i = start; i < end; i++) {
            schedule[i] = timetableItem;
            earliestHours.add(start);
            latestHours.add(end);
        }
        adjustStartAndEndHours();
        isEmpty = false;
    }

    /**
     * Adds a timetable event to a specific day schedule.
     *
     * @param timetableUserItem Event to be added to a day's schedule
     * @param schedule          Day's schedule (i.e monday/tuesday/.. etc) to add the
     *                          lesson to
     */
    private void addEventToSchedule(TimetableUserItem timetableUserItem, TimetableItem[] schedule) {
        int start = Integer.parseInt(timetableUserItem.getStartTime());
        int end = Integer.parseInt(timetableUserItem.getEndTime());
        start /= HOUR;
        end /= HOUR;
        end = endTimeEqualizer(end);
        for (int i = start; i <= end; i++) {
            schedule[i] = timetableUserItem;
            earliestHours.add(start);
            latestHours.add(end);
        }
        if (end == TIME_LIMIT) {
            latestHours.add(end + ONE);
        }
        adjustStartAndEndHours();
        isEmpty = false;
    }

    /**
     * Function is used to find the true end time of an event in order to account for 2400.
     *
     * @param end the hour in which the event ends
     * @return the true end time
     */
    private int endTimeEqualizer(int end) {
        int result = end;
        if (end != TIME_LIMIT) {
            result--;
        }
        return result;
    }

    /**
     * Adds the lesson's module to the internal tracking list. This is to be
     * displayed later
     *
     * @param module Module to be added
     * @see Module
     */
    public void addModuleToList(Module module) {
        if (!modules.contains(module)) {
            modules.add(module);
        }
    }

    /**
     * Deletes the module from the list of modules taken by the user.
     *
     * @param moduleCode Module Code of the module to be Deleted
     *                   E.g. CS2113T
     * @see Module
     */
    //@@author aditichadha1310
    public void deleteModuleFromList(String moduleCode) throws UniModsException {
        for (int i = 0; i < modules.size(); i++) {
            if (modules.get(i).getModuleCode().toUpperCase(Locale.ROOT).equals(moduleCode)) {
                modules.remove(modules.get(i));
                return;
            }
        }
    }

    /**
     * Calls the function deleteLessonFromSchedule to delete the item from the
     * timetable's daily plan.
     *
     * @param title Title of the Item to be deleted.
     *              Module Code for modules, Descriptions for Personal tasks.
     * @see Module
     */
    //@@author aditichadha1310
    public void deleteFromSchedules(String title) {
        int countOfDeleted = 0;
        countOfDeleted += deleteItemFromSchedule(title, monday);
        countOfDeleted += deleteItemFromSchedule(title, tuesday);
        countOfDeleted += deleteItemFromSchedule(title, wednesday);
        countOfDeleted += deleteItemFromSchedule(title, thursday);
        countOfDeleted += deleteItemFromSchedule(title, friday);
        countOfDeleted += deleteItemFromSchedule(title, saturday);
        countOfDeleted += deleteItemFromSchedule(title, sunday);
        if (countOfDeleted > 0) {
            TextUi.printModuleDeleted(title);
        } else {
            System.out.println(TextUi.ERROR_MODULE_NOT_FOUND);
        }
    }

    /**
     * Sets the TimetableItem for the particular time slot to be null where the
     * timetableitem is scheduled for the module to be deleted.
     *
     * @param schedule Schedule contains the lessons of the user for a particular
     *                 day of the week.
     * @param title    Tilte of TimetableItem to be Deleted
     * @see TimetableItem
     */
    //@@author aditichadha1310
    private int deleteItemFromSchedule(String title, TimetableItem[] schedule) {
        int countOfDeleted = 0;
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] != null && schedule[i].getTitle().toUpperCase(Locale.ROOT).equals(title)) {
                countOfDeleted += 1;
                latestHours.remove(schedule[i].getEndHour());
                earliestHours.remove(schedule[i].getStartHour());
                schedule[i] = null;
            }
        }
        adjustStartAndEndHours();
        return countOfDeleted;
    }

    public void editEventFromSchedule(TimetableUserItem event, String input) {
        DayOfWeek day = DayOfWeek.valueOf(event.getDay().toUpperCase(Locale.ROOT));
        TimetableItem[] schedule = schedules.get(day);
        String title = event.getTitle();
        for (TimetableItem timetableItem : schedule) {
            if (timetableItem != null && timetableItem.getTitle().equals(title)) {
                timetableItem.setTitle(input);
            }
        }
    }

    /**
     * Removes all modules from the list of modules taken by the user. Calls the
     * clearTimetableFromLessons() function to delete all the scheduled lessons.
     */
    //@@author aditichadha1310
    public void clearTimetable() throws UniModsException {
        if (modules.size() == ZERO && events.size() == ZERO) {
            throw new UniModsException(TextUi.ERROR_EMPTY_TIMETABLE);
        }
        modules.clear();
        events.clear();
        clearTimetableFromLessons();
        earliestHours.clear();
        latestHours.clear();
        earliestHours.add(DEFAULT_START);
        latestHours.add(DEFAULT_END);
        adjustStartAndEndHours();
        TextUi.printTimetableCleared();
        logger.log(Level.INFO, "All modules removed from timetable");
    }

    /**
     * Calls the function clearTimetableFromLessons() to clear the scheduled lessons
     * from the timetable's daily plan.
     */
    //@@author aditichadha1310
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
     * @param schedule Schedule contains the lessons of the user for a particular
     *                 day of the week.
     */
    public void clearSchedule(TimetableItem[] schedule) {
        assert (schedule.length > 0);
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] != null) {
                schedule[i] = null;
            }
        }
    }

    /**
     * Displays the timetable over Command Line Interface Draws a grid where the
     * Horizontal Axis represents the hour timing and the Vertical Axis represents
     * the Day (MON/TUES/WED/... etc.) Displays the lessons in each grid cell that
     * had been added to the timetable
     *
     * <p>Also displays all the modules taken and the total number of MCs taken for the
     * timetable
     */
    public void showTimetable(boolean showUserItemsOnly) {
        TimetableUI.printScheduleHours(earliestHour, latestHour);
        TimetableUI.printDaySchedule("MON", monday, earliestHour, latestHour, showUserItemsOnly);
        TimetableUI.printDaySchedule("TUE", tuesday, earliestHour, latestHour, showUserItemsOnly);
        TimetableUI.printDaySchedule("WED", wednesday, earliestHour, latestHour, showUserItemsOnly);
        TimetableUI.printDaySchedule("THU", thursday, earliestHour, latestHour, showUserItemsOnly);
        TimetableUI.printDaySchedule("FRI", friday, earliestHour, latestHour, showUserItemsOnly);
        TimetableUI.printDaySchedule("SAT", saturday, earliestHour, latestHour, showUserItemsOnly);
        TimetableUI.printDaySchedule("SUN", sunday, earliestHour, latestHour, showUserItemsOnly);
        TimetableUI.printModules(modules, this.semester);
    }

    public TimetableItem getItem(String day, int startHour) {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase(Locale.ROOT));
        return schedules.get(dayOfWeek)[startHour];
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        assert (semester > 0 && semester < 5);
        this.semester = semester;
    }

    public int getEarliestHour() {
        return earliestHour;
    }

    public int getLatesthour() {
        return latestHour;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public PriorityQueue<Integer> getEarliestHours() {
        return earliestHours;
    }

    public PriorityQueue<Integer> getLatestHours() {
        return latestHours;
    }

    public TimetableItem[] getMonday() {
        return this.monday;
    }

    public TimetableItem[] getTuesday() {
        return this.tuesday;
    }

    public TimetableItem[] getWednesday() {
        return this.wednesday;
    }

    public TimetableItem[] getThursday() {
        return this.thursday;
    }

    public TimetableItem[] getFriday() {
        return this.friday;
    }

    public TimetableItem[] getSaturday() {
        return this.saturday;
    }

    public TimetableItem[] getSunday() {
        return this.sunday;
    }

    public ArrayList<TimetableUserItem> getEvents() {
        return events;
    }

    @Override
    public int compareTo(Timetable timetable) {
        int flag = 0;
        boolean isSemesterSame = this.getSemester() == timetable.getSemester();
        boolean areModulesSame = this.modules.equals(timetable.modules);
        if (isSemesterSame && areModulesSame) {
            flag = 1;
        }
        return flag;
    }

    public boolean isConflict(String day, int start, int end) {
        for (int i = start; i < end; i++) {
            if (isItemExist(day, i)) {
                return true;
            }
        }
        return false;
    }

    public boolean isConflict(Lesson lesson) {
        return isConflict(lesson.getDay(), lesson.getStartHour(), lesson.getEndHour());
    }

    public boolean isConflict(TimetableUserItem userItem) {
        return isConflict(userItem.getDay(), userItem.getStartHour(), userItem.getEndHour());
    }

    public boolean isItemExist(String day, int time) {
        return getItem(day, time) != null;
    }

    public void addToEvents(TimetableUserItem timetableUserItem) {
        events.add(timetableUserItem);
    }

    //@@author aditichadha1310
    public void removeFromSchedules(String title) {
        deleteItemFromSchedule(title, monday);
        deleteItemFromSchedule(title, tuesday);
        deleteItemFromSchedule(title, wednesday);
        deleteItemFromSchedule(title, thursday);
        deleteItemFromSchedule(title, friday);
        deleteItemFromSchedule(title, saturday);
        deleteItemFromSchedule(title, sunday);
    }
}
