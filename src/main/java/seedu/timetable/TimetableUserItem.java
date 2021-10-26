package seedu.timetable;

import seedu.ui.TimetableUI.LineType;

import java.time.DayOfWeek;
import java.util.ArrayList;

public class TimetableUserItem extends TimetableItem {

    private static final int DIVISOR = 100;
    private static final int ONE = 1;

    private final String description;
    private final String day;

    public TimetableUserItem(String title, String day, String startTime, String endTime, String description) {
        super(title, day, startTime, endTime);
        this.description = description;
        this.day = day;
    }

    public String printTypeInfo(LineType type) {
        String str = "|   ";
        switch (type) {
        default:
            str += "";
        }
        return str;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDescription() {
        return description.length() > 0;
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

    public ArrayList<Integer> getStartToEndTime() {
        int length = ((Integer.parseInt(getEndTime())) - Integer.parseInt(getStartTime())) / DIVISOR;
        ArrayList<Integer> time = new ArrayList<>();
        int startTime = Integer.parseInt(getStartTime()) / DIVISOR;
        time.add(startTime);
        for (int i = 1; length > i; i++) {
            startTime += ONE;
            time.add(startTime);
        }
        return time;
    }

    public DayOfWeek getParsedDay() {
        switch (getDay()) {
        case "Monday":
            return DayOfWeek.MONDAY;
        case "Tuesday":
            return DayOfWeek.TUESDAY;
        case "Wednesday":
            return DayOfWeek.WEDNESDAY;
        case "Thursday":
            return DayOfWeek.THURSDAY;
        case "Friday":
            return DayOfWeek.FRIDAY;
        case "Saturday":
            return DayOfWeek.SATURDAY;
        case "Sunday":
            return DayOfWeek.SUNDAY;
        default:
            return null;
        }
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
