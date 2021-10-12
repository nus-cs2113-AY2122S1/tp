package taa.attendance;


import taa.ClassChecker;

import java.util.ArrayList;
import java.util.Comparator;

public class AttendanceList implements ClassChecker {
    private final ArrayList<Attendance> attendances;

    public AttendanceList() {
        this.attendances = new ArrayList<>();
    }

    public int getSize() {
        return attendances.size();
    }

    public ArrayList<Attendance> getAttendances() {
        return new ArrayList<>(attendances);
    }

    public Attendance getAttendance(int lessonNumber) {
        for (Attendance attendance : attendances) {
            if (attendance.getLessonNumber() == lessonNumber) {
                return attendance;
            }
        }

        return null;
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
        sortAttendances();
    }

    private void sortAttendances() {
        attendances.sort(Comparator.comparingInt(Attendance::getLessonNumber));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < getSize(); i += 1) {
            if (i > 0) {
                stringBuilder.append("\n");
            }

            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(attendances.get(i));
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean verify() {
        ArrayList<Integer> lessonNumbers = new ArrayList<>();
        for (Attendance attendance : attendances) {
            if (lessonNumbers.contains(attendance.getLessonNumber())) {
                attendances.remove(attendance);
            } else {
                lessonNumbers.add(attendance.getLessonNumber());
            }
        }

        return true;
    }
}
