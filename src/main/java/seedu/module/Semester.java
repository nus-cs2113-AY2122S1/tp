package seedu.module;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Semester {

    private static final int MILLISECONDS_TO_SECONDS = 1000;
    private static final int SECONDS_TO_MINUTES = 60;

    private int semester;
    private Date examDate;
    private int examDuration;
    private ArrayList<Lesson> timetable;

    public ArrayList<Lesson> getTimetable() {
        return timetable;
    }

    public Lesson getLesson(int idx) {
        return timetable.get(idx);
    }

    public int getSemester() {
        return semester;
    }

    public String getExamInfo() {
        Date end = new Date(examDate.getTime() + (long)examDuration * MILLISECONDS_TO_SECONDS * SECONDS_TO_MINUTES);
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        String date = dateFormat.format(examDate.getTime());
        String startTime = timeFormat.format(examDate.getTime());
        String endTime = timeFormat.format(end.getTime());
        return String.format("%s %s - %s", date, startTime, endTime);
    }

    public Date getExamDate() {
        return examDate;
    }

    public int getExamDuration() {
        return examDuration;
    }

    public void changeTimetable(ArrayList<Lesson> lessons) {
        timetable = lessons;
    }

    @Override
    public String toString() {
        return String.valueOf(semester);
    }

    public boolean isLectureExist() {
        for (Lesson lesson : timetable) {
            if (lesson.getLessonType().equals("Lecture")) {
                return true;
            }
        }
        return false;
    }

    public boolean isTutorialExist() {
        for (Lesson lesson : timetable) {
            if (lesson.getLessonType().equals("Tutorial")) {
                return true;
            }
        }
        return false;
    }

    public boolean isLabExist() {
        for (Lesson lesson : timetable) {
            if (lesson.getLessonType().equals("Laboratory")) {
                return true;
            }
        }
        return false;
    }
}
