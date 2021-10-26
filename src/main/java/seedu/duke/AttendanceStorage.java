package seedu.duke;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class AttendanceStorage {
    /**
     * This method searches the current directory for a folder named DukeAttendance
     * If the folder exists, it will load the attendances into the attendance list
     * else it will create an empty folder called DukeAttendance
     */
    public static void setUpAttendanceStorage(AttendanceList attendanceList) {
        File currentDir = new File("");
        try {
            String DukeAttendanceFolderPath = currentDir.getCanonicalPath() + "/DukeAttendance"; //is / instead of \\ to make it OS independent. need to test it tho on UNIX
            //System.out.println(DukeAttendanceFolderPath);
            File DukeAttendanceFolder = new File(DukeAttendanceFolderPath);
            if (DukeAttendanceFolder.isDirectory()) {
                System.out.println("attendance file exists");
                loadAttendanceFiles(DukeAttendanceFolder, attendanceList);
            } else {
                System.out.println("attendance file does not exists");
                new File(DukeAttendanceFolderPath).mkdirs();
                System.out.println("attendance file created");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will load all attendances into the list
     *
     * @param DukeAttendanceFolder the folder path containing all the attendances
     */
    public static void loadAttendanceFiles(File DukeAttendanceFolder, AttendanceList attendanceList) {
        File[] dukeAttendanceFiles = DukeAttendanceFolder.listFiles();

        for (File file : dukeAttendanceFiles) {
            //System.out.println(file.getName()); //this can show all the names of attendance files
            loadIndividualAttendanceFile(file, attendanceList);
        }
    }

    public static void loadIndividualAttendanceFile(File attendanceCsvFile, AttendanceList attendanceList) {
        String fileName = attendanceCsvFile.getName();

        String name;
        String trainingName = getFileTrainingName(fileName);
        String attended;
        try {
            Scanner dukeAttendanceScanner = new Scanner(attendanceCsvFile);
            dukeAttendanceScanner.nextLine(); //skips the first header row
            while (dukeAttendanceScanner.hasNextLine()) {
                String fullAttendanceDetails = dukeAttendanceScanner.nextLine();
                //System.out.println(fullAttendanceDetails);
                String[] AttendanceDetails = fullAttendanceDetails.split("\\,", 2);


                name = AttendanceDetails[0];
                attended = AttendanceDetails[1];

                Attendance attendance = new Attendance(name, trainingName, attended);
                attendanceList.addAttendance(attendance);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method adds to the csv file if it already exists.
     * If the csv file does not exist, it will create a new csv file and add to it.
     *
     * @param attendance the attendance entry to be added
     */
    //assumption is that the attendance folder is created already
    public static void writeToAttendance(AttendanceList attendanceList, Attendance attendance) {
        String trainingName = attendance.getTrainingName();
        File currentDir = new File("");
        try {
            String DukeAttendanceFilePath = currentDir.getCanonicalPath() + "/DukeAttendance/" + trainingName + ".csv";

            System.out.println(DukeAttendanceFilePath);

            File DukeSpecificAttendanceFile = new File(DukeAttendanceFilePath);
            if (DukeSpecificAttendanceFile.exists()) {
                rewriteAttendanceCsv(attendanceList, DukeSpecificAttendanceFile, trainingName);
            } else {
                //create new file and write to it
                initializeAttendanceCsv(attendanceList, attendance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initializeAttendanceCsv(AttendanceList attendanceList, Attendance attendance) {
        String trainingName = attendance.getTrainingName();
        File currentDir = new File("");
        try {
            String DukeAttendanceFilePath = currentDir.getCanonicalPath() + "/DukeAttendance/" + trainingName + ".csv";
            File AttendanceCsvFile = new File(DukeAttendanceFilePath);
            AttendanceCsvFile.createNewFile();
            writeFirstAttendance(AttendanceCsvFile, attendance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFirstAttendance(File AttendanceCsvFile, Attendance attendance) {
        try (PrintWriter dukeAttendanceWriter = new PrintWriter(AttendanceCsvFile)) {
            dukeAttendanceWriter.write("name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("attendance");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write('\n');
            dukeAttendanceWriter.write(attendance.getMemberName());
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write(attendance.getAttended());
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * this method rewrites the entire specific csv file
     */
    public static void rewriteAttendanceCsv(AttendanceList attendanceList, File currentAttendanceFile, String trainingName) {
        int attendanceListSize = attendanceList.getAttendanceListSize();
        try (PrintWriter dukeAttendanceWriter = new PrintWriter(currentAttendanceFile)) {
            dukeAttendanceWriter.write("name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("attendance");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write('\n');
            for (int i = 1; i <= attendanceListSize; i++) {
                //if (attendanceList.getAttendanceTrainingName(i).equals(trainingName)) {
                dukeAttendanceWriter.write(attendanceList.getAttendanceMemberName(i));
                dukeAttendanceWriter.write(',');
                dukeAttendanceWriter.write(attendanceList.getAttendancePresentOrLate(i));
                dukeAttendanceWriter.write('\n');
                //}
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    /**
     * This method removes the extension from the file name
     *
     * @param filename full file name including extension
     */
    public static String getFileTrainingName(String filename) {
        int fileNameLength = filename.length();
        int NumberToRemove = fileNameLength - 4;
        String trainingName = filename.substring(0, NumberToRemove);
        return trainingName;
    }

    //this function cheesing cos it just goes into the attendance folder and read the csv names
    //haven't done

    /**
     * This method will list all the attendance training names
     */
    public static String getAllTrainingNames() {
        return "kk";
    }


}





