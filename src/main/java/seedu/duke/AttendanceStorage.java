package seedu.duke;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.member.Member;
import seedu.duke.member.MemberList;
import seedu.duke.training.TrainingSchedule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class AttendanceStorage {

    /**
     *This method generates a csv file with all the members name
     *the csv file name will be called based on the trainingName variable passed in
     *
     * @params trainingName name of the training
     */
    public static void generateFullList(String trainingName ,MemberList memberList){
        String attendanceFileName = trainingName + ".csv";
        File dukeAttendanceFile = new File(attendanceFileName);
        if (!dukeAttendanceFile.exists()) {
            try {
                dukeAttendanceFile.createNewFile();
                initializeAttendanceFile(dukeAttendanceFile,memberList);
                System.out.println("file created");
                assert dukeAttendanceFile != null : "duke attendance file should be created";
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("existing file with the same name exists. please use a different name");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This method initializes the headers of duke attendance file with all members names.
     *
     * @param dukeAttendanceFile the attendance file
     */
    public static void initializeAttendanceFile(File dukeAttendanceFile,MemberList memberList) {
        int memberListSize = memberList.getMemberListSize();

        try (PrintWriter dukeAttendanceWriter = new PrintWriter(dukeAttendanceFile)) {
            dukeAttendanceWriter.write("name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("training name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("Attendance");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write('\n');
            for (int i = 1; i <= memberListSize; i++) {
                dukeAttendanceWriter.write(memberList.getMemberName(i));
                dukeAttendanceWriter.write('\n');
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }




}