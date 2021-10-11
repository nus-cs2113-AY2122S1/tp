package seedu.duke.attendance;

import java.util.ArrayList;
import java.util.SplittableRandom;

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

    /**
     * Add attendance entry as request by user.
     *
     * @param attendance attendance entry to be added
     */
    public void addAttendance(Attendance attendance) {
        attendanceList.add(attendance);
    }

    /**
     * Delete attendance entry as request by user.
     *
     * @param attendanceNumber member number given by user
     * @throws IndexOutOfBoundsException When an invalid member is selected to be deleted
     */
    public Attendance deleteAttendance(int attendanceNumber) throws IndexOutOfBoundsException {
        try {
            int index = attendanceNumber - 1;
            Attendance entry = attendanceList.get(index);
            attendanceList.remove(index);
            return entry;
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    public int getAttendanceSize() { //added by xy
        return attendanceList.size();
    }

    /*public String getStudentNumber(int index ) {
         AttendanceList.get(index);
    }

    public String getTrainingName(int index ) {

    }

    public String getVenue(int index ) {

    }

    public String getTime(int index ) {

    }
    */

}