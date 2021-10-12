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

    /**
     * Gets the list of attendances. Note: This returns a new ArrayList instance.
     *
     * @return A new ArrayList containing all the attendances.
     */
    public ArrayList<Attendance> getAttendances() {
        return new ArrayList<>(attendances);
    }

    /**
     * Gets an Attendance object with a particular lesson number from the attendances ArrayList.
     *
     * @param lessonNumber The lesson number of the attendance.
     * @return An Attendance object if found, else null.
     */
    public Attendance getAttendance(int lessonNumber) {
        for (Attendance attendance : attendances) {
            if (attendance.getLessonNumber() == lessonNumber) {
                return attendance;
            }
        }

        return null;
    }

    /**
     * Adds an Attendance object to the list of attendances. The attendances ArrayList is sorted after adding.
     *
     * @param attendance The attendance object to add.
     */
    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
        sortAttendances();
    }

    /**
     * Sorts the Attendance objects in the attendances ArrayList in ascending order based on lesson number.
     */
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

    /**
     * Checks if the variables in the class are valid. Filters out duplicate attendance with the same lesson number.
     *
     * @return Always returns true.
     */
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
