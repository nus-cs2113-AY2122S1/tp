package taa.attendance;

public class Attendance {
    private final String code;
    private final String studentIndex;
    private final String lessonIndex;
    private boolean hasAttend;

    public Attendance(String code, String studentIndex, String lessonIndex, boolean hasAttend) {
        this.code = code;
        this.studentIndex = studentIndex;
        this.lessonIndex = lessonIndex;
        this.hasAttend = hasAttend;
    }

    public String markAttendance() {
        return (hasAttend ? "1" : "0");
    }

    public String getLessonIndex() {
        return lessonIndex;
    }

    public String getLessonNum() {
        return Integer.toString(Integer.parseInt(lessonIndex) + 1);
    }


    @Override
    public String toString() {
        return String.format("%s %s %s %s", code, studentIndex, lessonIndex, hasAttend);
    }
}

