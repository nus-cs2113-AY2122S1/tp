//@@author izdiyadfrhn

package seedu.duke.attendance;

import java.util.ArrayList;

import seedu.duke.attendance.exception.InvalidAttendanceException;

public class AttendanceList {

    private final ArrayList<Attendance> attendanceList;

    public AttendanceList() {
        attendanceList = new ArrayList<Attendance>();
    }

    public AttendanceList(ArrayList<Attendance> inputAttendanceList) {
        this.attendanceList = inputAttendanceList;
    }

    public ArrayList<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public int getAttendanceListSize() {
        return attendanceList.size();
    }

    public String getAttendanceMemberName(int index) {
        return attendanceList.get(index - 1).memberName;
    }

    public String getAttendanceTrainingName(int index) {
        return attendanceList.get(index - 1).trainingName;
    }

    public String getAttendancePresentOrLate(int index) {
        return attendanceList.get(index - 1).attended;
    }

    /**
     * Adds attendance entry as request by user.
     *
     * @param attendance attendance entry to be added
     */
    public void addAttendance(Attendance attendance) {
        attendanceList.add(attendance);
    }

    /**
     * Deletes attendance entry based on index input by user. Index must be valid.
     *
     * @param attendanceNumber index of the Attendance entry user wishes to delete.
     * @return Attendance entry to be deleted
     * @throws IndexOutOfBoundsException When an invalid member is selected to be deleted
     * @throws InvalidAttendanceException When unable to find entry with the same details
     */
    public Attendance deleteAttendance(int attendanceNumber) throws IndexOutOfBoundsException,
            InvalidAttendanceException {
        try {
            int index = attendanceNumber - 1;
            Attendance entry = attendanceList.get(index);
            Attendance removedEntry = new Attendance(entry);
            for (int i = attendanceNumber; i < this.getAttendanceListSize(); i++) {
                Attendance attendanceToChangeIndex = attendanceList.get(i);
                attendanceToChangeIndex.setIndex(i);
            }
            attendanceList.remove(index);
            return removedEntry;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }
}