//@@author xingyuan123

package seedu.duke.command;

import seedu.duke.storage.AttendanceStorage;
import seedu.duke.attendance.AttendanceList;


public class DeleteAttendance {

    public DeleteAttendance(AttendanceList attendanceList, String trainingName, int attendanceIndex) {
        AttendanceStorage.deleteAttendance(attendanceList, trainingName,
                attendanceIndex);
        AttendanceStorage.handleDeleteAttendanceCsv(attendanceList, trainingName);
    }
}


