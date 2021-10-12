package taa.attendance;


import java.util.ArrayList;
import java.util.Arrays;

public class AttendanceList {
    private static final String MESSAGE_ATTENDANCE_LIST_HEADER = "Attendance List:";

    private final ArrayList<String> attendances;

    public AttendanceList() {
        this.attendances = new ArrayList<>();
    }

    public int getSize() {
        return attendances.size();
    }

    public ArrayList<String> getAttendances() {
        return attendances;
    }

    /**
     * Converts the array of attendances from all lessons for a student to a string
     *
     * @param individualAttendances the array of attendances from all lessons for a student
     * @return String representation of the array of attendances from all lessons for a student
     */
    public String individualAttendancesToString(String[] individualAttendances) {
        return Arrays.toString(individualAttendances);
    }

    /**
     * Adds the array of attendances from all lessons for a student to the AttendanceList
     *
     * @param individualAttendances the array of attendances from all lessons for a student
     */
    public void addIndividualAttendances(String[] individualAttendances) {
        String studentAttendances = individualAttendancesToString(individualAttendances);
        String formattedStudentAttendances = formatStudentAttendances(studentAttendances);
        attendances.add(formattedStudentAttendances);
    }

    private String formatStudentAttendances(String studentAttendances) {
        studentAttendances = studentAttendances.replace("[", " | ");
        studentAttendances = studentAttendances.replace("]", " |");
        studentAttendances = studentAttendances.replace(",", " |");
        return studentAttendances;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(MESSAGE_ATTENDANCE_LIST_HEADER);
        for (int i = 0; i < getSize(); i += 1) {
            stringBuilder.append("\n");
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(attendances.get(i));
        }

        return stringBuilder.toString();
    }

}

