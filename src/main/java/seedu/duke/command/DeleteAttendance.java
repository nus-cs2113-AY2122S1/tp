package seedu.duke.command;

import seedu.duke.AttendanceStorage;
import seedu.duke.attendance.AttendanceList;


public class DeleteAttendance {

    public DeleteAttendance(AttendanceList attendanceList, String trainingName, int attendanceIndex) {
        AttendanceStorage.deleteAttendance(attendanceList, trainingName,
                attendanceIndex);//deletes attendance from main list
        AttendanceStorage.handleDeleteAttendanceCsv(attendanceList, trainingName);//rewrite the csv file
        //if the csv file is the only one,delete the attendance
    }
}


