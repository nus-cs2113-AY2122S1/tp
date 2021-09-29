package seedu.module;

import java.util.ArrayList;
import java.util.Date;

public class Semester {
    private int semester;
    private Date examDate;
    private int examDuration;
    ArrayList<Lesson> timetable;

    @Override
    public String toString() {
        return String.valueOf(semester);
    }
}
