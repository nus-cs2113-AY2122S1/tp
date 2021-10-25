package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.attendance.Attendance;
import seedu.duke.AttendanceStorage;

/**
 * Adds an Attendance object to the AttendanceList ArrayList.
 */
public class AddAttendance {

    /**
     * Constructor. Adds Attendance to the AttendanceList and saves it to hard disk.
     *
     * @param attendanceList Contains all attendance objects added.
     * @param attendance Attendance object to be added.
     */
    public AddAttendance(AttendanceList attendanceList, Attendance attendance) {
        attendanceList.addAttendance(attendance);
        AttendanceStorage.writeToAttendance(attendanceList, attendance);

        Ui.printAddedAttendanceMessage(attendance);
    }
}
