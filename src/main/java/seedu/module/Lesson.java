package seedu.module;

import com.google.gson.annotations.JsonAdapter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Lesson {
    private static final String GAP = ", ";
    private static final int DIVISOR = 100;
    private static final int ONE = 1;
    private final String classNo;
    private final String startTime;
    private final String endTime;

    @JsonAdapter(WeeksAdapterFactory.class)
    private Weeks weeks;

    private final String venue;
    private final String day;
    private final String lessonType; //placeholder
    private final int size;

    public Lesson(String classNo, String startTime, String endTime, String venue,
                  String lessonType, String day) {
        this.classNo = classNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.lessonType = lessonType;
        this.day = day;
        this.size = 0;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getClassNo() {
        return classNo;
    }

    public String getDay() {
        return day;
    }

    public String getLessonType() {
        return lessonType;
    }

    public String getVenue() {
        return venue;
    }

    public String getWeeks() {
        return weeks.toString();
    }

    public String lessonDetails() {
        return getDay() + GAP + getStartTime() + "-" + getEndTime() + GAP + getClassNo() + GAP + getVenue();
    }

    public int getSize() {
        return size;
    }

    private LocalTime parseTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(time, formatter);
    }

    public int getStartHour() {
        LocalTime startTime = parseTime(this.startTime);
        return startTime.getHour();
    }

    public int getEndHour() {
        LocalTime endTime = parseTime(this.endTime);
        return endTime.getHour();
    }
}
