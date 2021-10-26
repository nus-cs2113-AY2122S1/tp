package seedu.timetable;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.ui.TimetableUI.LineType;

public abstract class TimetableItem {

    protected String title;
    protected final DayOfWeek dayOfWeek;
    protected final String startTime;
    protected final String endTime;
    protected String type;
    protected String venue;

    public TimetableItem(String title, String day, String startTime, String endTime) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = parseDayOfWeek(day);
    }

    /**
     * Takes in a string e.g. "Sunday", "Monday", and formats it as a DayOfWeek Enum
     * for easier handling.
     * 
     * @param day String to be parsed as a DayOfWeek
     * @return DayOfWeek
     */
    private DayOfWeek parseDayOfWeek(String day) {
        switch (day) {
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

    /**
     * Getter for the title of the timetable.
     * 
     * @return title of timetable item
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String input) {
        title = input;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * Getter for the day of week the lesson is held on.
     * 
     * @return DayOfWeek Enum e.g. DayOfWeek.MONDAY
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Takes in a String in the time format "HHmm" e.g. 1800 And parses it into a
     * LocalTime object
     * 
     * @param time String in the format "HHmm" e.g. 1800
     * @return a LocalTime object representing the input time
     */
    private LocalTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(time, formatter);
    }

    /**
     * Getter for the starting hour of the lesson.
     * 
     * @return an Integer representing the hour in the day in 24H time format
     */
    public int getStartHour() {
        LocalTime startTime = parseTime(this.startTime);
        return startTime.getHour();
    }

    /**
     * Getter for the ending hour of the lesson.
     * 
     * @return an Integer representing the hour in the day in 24H time format
     */
    public int getEndHour() {
        LocalTime endTime = parseTime(this.endTime);
        return endTime.getHour();
    }

    public int duration() {
        return getEndHour() - getStartHour();
    }

    public boolean equals(TimetableItem item) {
        return Objects.equals(this,item);
    }
}
