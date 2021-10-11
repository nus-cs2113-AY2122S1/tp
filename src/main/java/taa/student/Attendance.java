package taa.student;

import taa.ClassChecker;

public class Attendance implements ClassChecker {
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

    @Override
    public boolean verify() {
        if (lessonNum < 0) {
            return false;
        }

        return true;
    }
}
