package seedu.duke.attendance;

import java.util.ArrayList;

public class AttendanceList {

    private final ArrayList<Attendance> attendanceList;

    public AttendanceList() {
        attendanceList = new ArrayList<Attendance>();
    }

    public AttendanceList(ArrayList<Attendance> attendanceList) { this.attendanceList = attendanceList; }

    public ArrayList<Attendance> getAttendanceList() { return attendanceList; }

    public void addAttendance(Attendance attendance) {
        attendanceList.add(attendance);
    }
}