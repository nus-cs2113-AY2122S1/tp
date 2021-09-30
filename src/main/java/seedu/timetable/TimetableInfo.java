package seedu.timetable;

import java.util.ArrayList;
import java.util.HashMap;

public class TimetableInfo {
    private HashMap<String, ArrayList<TimetableItem>> schedule;
    private ArrayList<String> modulesTaken;
    private int totalMcTaken;

    /**
     * Create empty Timetable info.
     */
    public TimetableInfo() {
        schedule = new HashMap<>();
        modulesTaken = new ArrayList<>();
        totalMcTaken = 0;
        schedule.put("Monday", new ArrayList<>());
        schedule.put("Tuesday", new ArrayList<>());
        schedule.put("Wednesday", new ArrayList<>());
        schedule.put("Thursday", new ArrayList<>());
        schedule.put("Friday", new ArrayList<>());
    }

    /**
     * Create Timetable info from TimetableDto.
     * 
     * @param dto timetable dto
     */

    public TimetableInfo(TimetableDto dto) {
        schedule = new HashMap<>();
        modulesTaken = dto.getModulesTaken();
        totalMcTaken = dto.getTotalMcTaken();

        schedule.put("Monday", dto.getMonday());
        schedule.put("Tuesday", dto.getTuesday());
        schedule.put("Wednesday", dto.getWednesday());
        schedule.put("Thursday", dto.getThursday());
        schedule.put("Friday", dto.getFriday());
    }

    /**
     * Create timetable DTO from info.
     * 
     * @return
     */
    public TimetableDto toDto() {
        return new TimetableDto(modulesTaken, totalMcTaken, schedule);
    }

    /**
     * Get schedule.
     * 
     * @return
     */
    public HashMap<String, ArrayList<TimetableItem>> getSchedule() {
        return this.schedule;
    }

    /**
     * set schedule.
     * 
     * @return
     */
    public void setSchedule(HashMap<String, ArrayList<TimetableItem>> schedule) {
        this.schedule = schedule;
    }

    /**
     * Get list modules taken.
     * 
     * @return
     */
    public ArrayList<String> getModulesTaken() {
        return this.modulesTaken;
    }

    /**
     * Set list modules taken.
     * 
     * @return
     */
    public void setModulesTaken(ArrayList<String> modulesTaken) {
        this.modulesTaken = modulesTaken;
    }

    /**
     * Get total MCs taken.
     * 
     * @return
     */
    public int getTotalMcTaken() {
        return this.totalMcTaken;
    }

    /**
     * Set total MCs taken.
     * 
     * @return
     */
    public void setTotalMcTaken(int totalMcTaken) {
        this.totalMcTaken = totalMcTaken;
    }

}
