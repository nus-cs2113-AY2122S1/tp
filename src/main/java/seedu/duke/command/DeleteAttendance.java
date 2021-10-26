package seedu.duke.command;

import seedu.duke.Ui;

import java.io.File;
import java.io.IOException;

import seedu.duke.attendance.AttendanceList;
import seedu.duke.attendance.Attendance;
import seedu.duke.Parser;

import seedu.duke.AttendanceStorage;


public class DeleteAttendance {
    public DeleteAttendance(AttendanceList attendanceList,String trainingName , int attendanceIndex){
        AttendanceStorage.deleteAttendance(attendanceList,trainingName,attendanceIndex);//deletes attendance from main list
        AttendanceStorage.handleDeleteAttendanceCsv(attendanceList,trainingName);//rewrite the csv file
        //if the csv file is the only one,delete the attendance
    }
}


