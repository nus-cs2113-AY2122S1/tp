package seedu.module;

import java.util.ArrayList;
import java.util.Date;

public class Semester {
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
