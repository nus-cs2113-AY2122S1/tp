package seedu.timetable;

import seedu.ui.TimetableUI.LineType;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class TimetableUserItem extends TimetableItem {


    private final String day;

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
            return this.getTitle().equals(userItem.getTitle()) && this.getType().equals(userItem.getType());
        }
        return false;
    }

    public String getDescription() {
        return getTitle();
    }

    public boolean isDescription() {
        return getTitle().length() > 0;
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
        String output = "Event: " + getTitle() + " on " + getDay() + ", from "
                + startTime + " to " + endTime;
        if (isDescription()) {
            output = output.concat(" at " + getDescription());
        }
        return output;
    }


}
