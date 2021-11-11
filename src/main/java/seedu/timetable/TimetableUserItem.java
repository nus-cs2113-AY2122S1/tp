package seedu.timetable;

public class TimetableUserItem extends TimetableItem {
    private static final int TIME = 100;

    private final String day;

    /**
     * Creates timetable user item for user created tasks.
     * 
     * @param title Title of the tasks
     * @param day Day of week where tasks occurs
     * @param startTime start time of the tasks
     * @param endTime end time of the tasks
     * @param location location of the tasks
     */
    public TimetableUserItem(String title, String day, String startTime, String endTime,
            String location) {
        super(title, day, startTime, endTime);
        this.type = "TASK";
        this.venue = location;
        this.day = day;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TimetableUserItem) {
            TimetableUserItem userItem = (TimetableUserItem) obj;
            return this.getTitle().equals(userItem.getTitle())
                    && this.getType().equals(userItem.getType());
        }
        return false;
    }

    public String getDescription() {
        return getTitle();
    }

    public boolean isLocation() {
        return getLocation().length() > 0;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString() {
        String output = "Alright!! Event: " + getTitle() + " on " + getDay() + ", from "
                + getStartTime() + " to " + getEndTime();
        if (isLocation()) {
            output = output.concat(" at " + getLocation());
        }
        output = output.concat(" has been added to your timetable");
        return output;
    }

    public String getLocation() {
        return venue;
    }
}
