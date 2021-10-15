package seedu.module;

import com.google.gson.annotations.JsonAdapter;

public class Lesson {
    private static final String GAP = ", ";
    private String classNo;
    private String startTime;
    private String endTime;

    @JsonAdapter(WeeksAdapterFactory.class)
    private Weeks weeks;

    private String venue;
    private String day;
    private String lessonType; //placeholder
    private int size;

    public Lesson(String classNo, String startTime, String endTime, String venue,
                  String lessonType, String day) {
        this.classNo = classNo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.venue = venue;
        this.lessonType = lessonType;
        this.day = day;
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
}
