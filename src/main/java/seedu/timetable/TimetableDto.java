package seedu.timetable;

import java.util.ArrayList;

import seedu.module.Module;

public class TimetableDto {
    private int semester;
    private int earliestHour;
    private int latestHour;

    private ArrayList<Module> modules;

    private TimetableLesson[] mondayLesson = new TimetableLesson[24];
    private TimetableLesson[] tuesdayLesson = new TimetableLesson[24];
    private TimetableLesson[] wednesdayLesson = new TimetableLesson[24];
    private TimetableLesson[] thursdayLesson = new TimetableLesson[24];
    private TimetableLesson[] fridayLesson = new TimetableLesson[24];
    private TimetableLesson[] saturdayLesson = new TimetableLesson[24];
    private TimetableLesson[] sundayLesson = new TimetableLesson[24];

    private TimetableUserItem[] mondayUserItems = new TimetableUserItem[24];
    private TimetableUserItem[] tuesdayUserItems = new TimetableUserItem[24];
    private TimetableUserItem[] wednesdayUserItems = new TimetableUserItem[24];
    private TimetableUserItem[] thursdayUserItems = new TimetableUserItem[24];
    private TimetableUserItem[] fridayUserItems = new TimetableUserItem[24];
    private TimetableUserItem[] saturdayUserItems = new TimetableUserItem[24];
    private TimetableUserItem[] sundayUserItems = new TimetableUserItem[24];

    public TimetableDto(Timetable timetable) {
        this.semester = timetable.getSemester();
        this.earliestHour = timetable.getEarliestHour();
        this.latestHour = timetable.getLatesthour();
        this.modules = timetable.getModules();
        splitTimetableItems(timetable.getMonday(), this.mondayLesson, this.mondayUserItems);
        splitTimetableItems(timetable.getTuesday(), this.tuesdayLesson, this.tuesdayUserItems);
        splitTimetableItems(timetable.getWednesday(), this.wednesdayLesson, this.wednesdayUserItems);
        splitTimetableItems(timetable.getThursday(), this.thursdayLesson, this.thursdayUserItems);
        splitTimetableItems(timetable.getFriday(), this.fridayLesson, this.fridayUserItems);
        splitTimetableItems(timetable.getSaturday(), this.saturdayLesson, this.saturdayUserItems);
        splitTimetableItems(timetable.getSunday(), this.sundayLesson, this.sundayUserItems);
    }

    public void splitTimetableItems(TimetableItem[] items, TimetableLesson[] lessons, TimetableUserItem[] userItems) {
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

    public Timetable toTimetable() {
        TimetableItem[] monday = mergeTimetableItems(this.mondayLesson, this.mondayUserItems);
        TimetableItem[] tuesday = mergeTimetableItems(this.tuesdayLesson, this.tuesdayUserItems);
        TimetableItem[] wednesday = mergeTimetableItems(this.wednesdayLesson, this.wednesdayUserItems);
        TimetableItem[] thursday = mergeTimetableItems(this.thursdayLesson, this.thursdayUserItems);
        TimetableItem[] friday = mergeTimetableItems(this.fridayLesson, this.fridayUserItems);
        TimetableItem[] saturday = mergeTimetableItems(this.saturdayLesson, this.saturdayUserItems);
        TimetableItem[] sunday = mergeTimetableItems(this.sundayLesson, this.sundayUserItems);
        return new Timetable(this.semester, this.earliestHour, this.latestHour, this.modules, monday, tuesday,
                wednesday, thursday, friday, saturday, sunday);
    }

    public TimetableItem[] mergeTimetableItems(TimetableLesson[] lessons, TimetableUserItem[] userItems) {
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

}
