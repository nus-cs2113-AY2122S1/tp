package seedu.timetable;

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

    /**
     * Create Timetable DTO.
     * 
     * @param modulesTaken list of modules taken
     * @param totalMcTaken total mcs taken
     * @param schedule map of day to schedule items
     */
    public TimetableDto(ArrayList<String> modulesTaken, int totalMcTaken,
            HashMap<String, ArrayList<TimetableItem>> schedule) {
        this.modulesTaken = modulesTaken;
        this.totalMcTaken = totalMcTaken;
        for (String day : schedule.keySet()) {
            ArrayList<TimetableItem> daySchedule = schedule.get(day);
            switch (day) {
            case "Monday":
                this.monday = daySchedule;
                break;
            case "tuesday":
                this.tuesday = daySchedule;
                break;
            case "wednesday":
                this.wednesday = daySchedule;
                break;
            case "thursday":
                this.thursday = daySchedule;
                break;
            case "friday":
                this.friday = daySchedule;
                break;
            default:
                break;
            }
        }
    }

    /**
     * Get Monday schedule.
     * 
     * @return
     */
    public ArrayList<TimetableItem> getMonday() {
        return this.monday;
    }

    /**
     * Get Tuesday schedule.
     * 
     * @return
     */
    public ArrayList<TimetableItem> getTuesday() {
        return this.tuesday;
    }

    /**
     * Get Wednesday schedule.
     * 
     * @return
     */
    public ArrayList<TimetableItem> getWednesday() {
        return this.wednesday;
    }

    /**
     * Get Thrusday schedule.
     * 
     * @return
     */
    public ArrayList<TimetableItem> getThursday() {
        return this.thursday;
    }

    /**
     * Get Friday schedule.
     * 
     * @return
     */
    public ArrayList<TimetableItem> getFriday() {
        return this.friday;
    }

    /**
     * Get list of modules taken.
     * 
     * @return
     */
    public ArrayList<String> getModulesTaken() {
        return this.modulesTaken;
    }

    /**
     * Get total MCs taken.
     * 
     * @return
     */
    public int getTotalMcTaken() {
        return this.totalMcTaken;
    }

}
