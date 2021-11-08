package taa.attendance;

//@@author daknam2001
import taa.ClassChecker;

import java.util.ArrayList;
import java.util.Comparator;

public class AttendanceList implements ClassChecker {
    private final ArrayList<Attendance> attendances;
    public static int ATTENDANCE_NOT_FOUND = -1;

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
     * Checks if an index is valid with respect to the attendance ArrayList.
     *
     * @param index The index to check.
     * @return true if valid, else false.
     */
    public boolean isValidIndex(int index) {
        return (index >= 0 && index < getSize());
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
     * Gets the index of a particular student's lesson attendance in attendance list.
     *
     * @param lessonNumInput The inputted lesson Num to search for.
     * @return A index of student's lesson attendance in attendance list.
     */
    public int getAttendanceIndex(String lessonNumInput) {
        for (Attendance attendance : attendances) {
            int lessonNumber = attendance.getLessonNumber();
            if (lessonNumber == Integer.parseInt(lessonNumInput)) {
                return attendances.indexOf(attendance);
            }
        }
        return ATTENDANCE_NOT_FOUND;
    }

    /**
     * Deletes a record of attendance from the attendance list.
     *
     * @param lessonNumInput The inputted lesson Num to delete.
     * @return An Attendance object if successfully deleted, else null.
     */
    public Attendance deleteAttendance(String lessonNumInput) {
        int attendanceIndex = getAttendanceIndex(lessonNumInput);
        if (isValidIndex(attendanceIndex)) {
            return attendances.remove(attendanceIndex);
        }
        return null;
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
        ArrayList<Attendance> duplicatedAttendances = new ArrayList<>();
        for (Attendance attendance : attendances) {
            if (lessonNumbers.contains(attendance.getLessonNumber())) {
                duplicatedAttendances.add(attendance);
            } else {
                lessonNumbers.add(attendance.getLessonNumber());
            }
        }

        for (Attendance attendance : duplicatedAttendances) {
            attendances.remove(attendance);
        }

        return true;
    }
}
