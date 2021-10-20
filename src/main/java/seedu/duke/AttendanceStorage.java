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
     * This method sets up the duke Attendance csv file.
     * It firsts tries to find if the file exists in the current directory.
     * If the file exists, it will call the loadDukeAttendanceFile method.
     * If not it will create a new duke member csv file in the current directory.
     *
     * @param attendance the list of attendees
     */
    public static void setupAttendanceFile(AttendanceList attendance) {
        File dukeAttendanceFile = new File("dukeAttendance.csv");
        if (!dukeAttendanceFile.exists()) {
            try {
                dukeAttendanceFile.createNewFile();
                initializeAttendanceFile(dukeAttendanceFile);
                System.out.println("file create at " + dukeAttendanceFile.getCanonicalPath());

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                System.out.println("file loaded from " + dukeAttendanceFile.getCanonicalPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            loadDukeAttendanceFile(dukeAttendanceFile, attendance);
        }
    }

    /**
     * This method loads the duke attendance file and writes to the current member list. Only happens on start-up.
     *
     * @param  dukeAttendanceFile CSV file to read data from.
     * @param  attendance to write to.
     */
    public static void loadDukeAttendanceFile(File dukeAttendanceFile, AttendanceList attendance) {
        String name;
        String studentNumber;
        String trainingName;
        String venue;
        String time;
        try  {
            Scanner dukeAttendanceScanner = new Scanner(dukeAttendanceFile);
            dukeAttendanceScanner.nextLine(); //skips the first header row
            while (dukeAttendanceScanner.hasNextLine()) {
                String fullAttendanceDetails = dukeAttendanceScanner.nextLine();
                System.out.println(fullAttendanceDetails);
                String[] memberDetails = fullAttendanceDetails.split("\\,", 5);

                name = memberDetails[0]; //used this to prevent magic numbers
                studentNumber = memberDetails[1];
                trainingName =  memberDetails[2];
                venue = memberDetails[3];
                time = memberDetails[4];

                TrainingSchedule schedule = new TrainingSchedule(trainingName,venue,time);
                Member member = new Member(name,studentNumber);
                Attendance attendance1 = new Attendance(member, schedule);
                attendance.addAttendance(attendance1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method initializes the headers of duke attendance file.
     *
     * @param dukeAttendanceFile the attendance file
     */
    public static void initializeAttendanceFile(File dukeAttendanceFile) {
        try (PrintWriter dukeAttendanceWriter = new PrintWriter(dukeAttendanceFile)) {
            dukeAttendanceWriter.write("name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("student number");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("training name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("venue");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("time");
            dukeAttendanceWriter.write('\n');
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * This method rewrites the entire duke attendance file.
     *
     * @param dukeAttendanceFile  the member file
     * @param attendance     the current attendance list
     */
    /*public static void writeMemberFile(File dukeAttendanceFile, AttendanceList attendance) {
        int attendanceListSize = attendance.getAttendanceSize();
        try (PrintWriter dukeAttendanceWriter = new PrintWriter(dukeAttendanceFile)) {
            dukeAttendanceWriter.write("name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("student number");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("trainingName");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("venue");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("time");
            dukeAttendanceWriter.write('\n');
            for (int i = 1; i <= attendanceListSize; i++) {
                dukeAttendanceWriter.write(AttendanceList.getAttendanceName(i));
                dukeAttendanceWriter.write(',');
                dukeAttendanceWriter.write(AttendanceList.getStudentNumber(i));
                dukeAttendanceWriter.write(',');
                dukeAttendanceWriter.write(AttendanceList.getTrainingName(i));
                dukeAttendanceWriter.write(',');
                dukeAttendanceWriter.write(AttendanceList.getVenue(i));
                dukeAttendanceWriter.write(',');
                dukeAttendanceWriter.write(AttendanceList.getTime(i));
                dukeAttendanceWriter.write('\n');
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }*/

}