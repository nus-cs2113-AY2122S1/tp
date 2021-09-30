package seedu.timetable;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class TimetableDto {
    private ArrayList<TimetableItem> monday;
    private ArrayList<TimetableItem> tuesday;
    private ArrayList<TimetableItem> wednesday;
    private ArrayList<TimetableItem> thursday;
    private ArrayList<TimetableItem> friday;
    private ArrayList<String> modulesTaken;
    private int totalMcTaken;
}

    public ArrayList<TimetableItem> getMonday() {
        return this.monday;
    }

    public ArrayList<TimetableItem> getTuesday() {
        return this.tuesday;
    }

    public ArrayList<TimetableItem> getWednesday() {
        return this.wednesday;
    }

    public ArrayList<TimetableItem> getThursday() {
        return this.thursday;
    }

    public ArrayList<TimetableItem> getFriday() {
        return this.friday;
    }

    public ArrayList<String> getModulesTaken() {
        return this.modulesTaken;
    }

    public int getTotalMcTaken() {
        return this.totalMcTaken;
    }
