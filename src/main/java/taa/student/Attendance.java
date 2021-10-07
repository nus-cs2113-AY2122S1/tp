package taa.student;

public class Attendance {
    private int lessonNum;
    private boolean hadAttend;

    public Attendance(int lessonNum) {
        this.lessonNum = lessonNum;
        this.hadAttend = false;
    }

    public int markAttendance() {
        return (hadAttend ? 1 : 0);
    }

    @Override
    public String toString() {
        return lessonNum + " - " + markAttendance();
    }
}
