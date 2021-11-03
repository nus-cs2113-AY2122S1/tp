package seedu.timetable;

import seedu.module.Module;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class TimetableDto {
    private final int semester;
    private final int earliestHour;
    private final int latestHour;

    private final ArrayList<Module> modules;
    private final ArrayList<TimetableUserItem> events;
    private final PriorityQueue<Integer> earliestHours;
    private final PriorityQueue<Integer> latestHours;

    private final TimetableLesson[] mondayLesson = new TimetableLesson[24];
    private final TimetableLesson[] tuesdayLesson = new TimetableLesson[24];
    private final TimetableLesson[] wednesdayLesson = new TimetableLesson[24];
    private final TimetableLesson[] thursdayLesson = new TimetableLesson[24];
    private final TimetableLesson[] fridayLesson = new TimetableLesson[24];
    private final TimetableLesson[] saturdayLesson = new TimetableLesson[24];
    private final TimetableLesson[] sundayLesson = new TimetableLesson[24];

    private final TimetableUserItem[] mondayUserItems = new TimetableUserItem[24];
    private final TimetableUserItem[] tuesdayUserItems = new TimetableUserItem[24];
    private final TimetableUserItem[] wednesdayUserItems = new TimetableUserItem[24];
    private final TimetableUserItem[] thursdayUserItems = new TimetableUserItem[24];
    private final TimetableUserItem[] fridayUserItems = new TimetableUserItem[24];
    private final TimetableUserItem[] saturdayUserItems = new TimetableUserItem[24];
    private final TimetableUserItem[] sundayUserItems = new TimetableUserItem[24];

    /**
     * Data Transfer Object class for converting Timetable object into a format that can be stored
     * with Gson.
     * 
     * @param timetable The timetable object to convert
     */
    public TimetableDto(Timetable timetable) {
        this.semester = timetable.getSemester();
        this.earliestHour = timetable.getEarliestHour();
        this.latestHour = timetable.getLatesthour();
        this.earliestHours = timetable.getEarliestHours();
        this.latestHours = timetable.getLatestHours();
        this.modules = timetable.getModules();
        this.events = timetable.getEvents();
        splitTimetableItems(timetable.getMonday(), this.mondayLesson, this.mondayUserItems);
        splitTimetableItems(timetable.getTuesday(), this.tuesdayLesson, this.tuesdayUserItems);
        splitTimetableItems(timetable.getWednesday(), this.wednesdayLesson,
                this.wednesdayUserItems);
        splitTimetableItems(timetable.getThursday(), this.thursdayLesson, this.thursdayUserItems);
        splitTimetableItems(timetable.getFriday(), this.fridayLesson, this.fridayUserItems);
        splitTimetableItems(timetable.getSaturday(), this.saturdayLesson, this.saturdayUserItems);
        splitTimetableItems(timetable.getSunday(), this.sundayLesson, this.sundayUserItems);
    }

    /**
     * Splits TimetableItem array into 2 arrays depending on which child class of TimetableItem the
     * object belongs to.
     * 
     * @param items TimetableItem array
     * @param lessons TimetableLesson array to store TimetableLesson type objects
     * @param userItems TimetableUserItem array to store TimetableUserItem type objects
     */
    public void splitTimetableItems(TimetableItem[] items, TimetableLesson[] lessons,
            TimetableUserItem[] userItems) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] instanceof TimetableLesson) {
                lessons[i] = (TimetableLesson) items[i];
            } else if (items[i] instanceof TimetableUserItem) {
                userItems[i] = (TimetableUserItem) items[i];
            } else {
                lessons[i] = null;
                userItems[i] = null;
            }
        }
    }

    /**
     * Converts TimetableDTO to a Timetable object. Merges TimetableLesson array and
     * TimetableUserItem array into TimetableItem array that is used in Timetable object.
     *
     */
    public Timetable toTimetable() {
        return new Timetable(this);
    }

    /**
     * Merges TimetableLessons and TimetableUserItems into a TimetableItem array.
     * 
     * @param lessons TimetableLesson array
     * @param userItems TimetableUserItem array
     */
    public TimetableItem[] mergeTimetableItems(TimetableLesson[] lessons,
            TimetableUserItem[] userItems) {
        TimetableItem[] day = new TimetableItem[24];
        for (int i = 0; i < day.length; i++) {
            if (lessons[i] != null) {
                day[i] = lessons[i];
            } else if (userItems[i] != null) {
                day[i] = userItems[i];
            } else {
                day[i] = null;
            }
        }
        return day;
    }

    public int getSemester() {
        return semester;
    }

    public PriorityQueue<Integer> getLatestHours() {
        return latestHours;
    }

    public PriorityQueue<Integer> getEarliestHours() {
        return earliestHours;
    }

    public ArrayList<TimetableUserItem> getEvents() {
        return events;
    }

    public ArrayList<Module> getModules() {
        return modules;
    }

    public int getEarliestHour() {
        return earliestHour;
    }

    public int getLatestHour() {
        return latestHour;
    }

    public TimetableItem[] getMondayItems() {
        return mergeTimetableItems(this.mondayLesson, this.mondayUserItems);
    }

    public TimetableItem[] getTuesdayItems() {
        return mergeTimetableItems(this.tuesdayLesson, this.tuesdayUserItems);
    }

    public TimetableItem[] getWednesdayItems() {
        return mergeTimetableItems(this.wednesdayLesson,this.wednesdayUserItems);
    }

    public TimetableItem[] getThursdayItems() {
        return mergeTimetableItems(this.thursdayLesson,this.thursdayUserItems);
    }

    public TimetableItem[] getFridayItems() {
        return mergeTimetableItems(this.fridayLesson,this.fridayUserItems);
    }

    public TimetableItem[] getSaturdayItems() {
        return mergeTimetableItems(this.saturdayLesson,this.saturdayUserItems);
    }

    public TimetableItem[] getSundayItems() {
        return mergeTimetableItems(this.sundayLesson,this.sundayUserItems);
    }
}
