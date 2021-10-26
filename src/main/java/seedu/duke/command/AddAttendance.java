package seedu.duke.command;
import seedu.duke.Ui;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.attendance.Attendance;
import seedu.duke.AttendanceStorage;

public class AddAttendance {
    public AddAttendance(AttendanceList attendanceList, Attendance attendance) {
        attendanceList.addAttendance(attendance);
        AttendanceStorage.writeToAttendance(attendanceList, attendance);

        Ui.printAddedAttendanceMessage(attendance);
    }
}
