package seedu.timetable;

public class TimetableUserItem extends TimetableItem {

    private String description;

    public TimetableUserItem(String title, String day, String startTime, String endTime, String description) {
        super(title, day, startTime, endTime);
        this.description = description;
    }
}
