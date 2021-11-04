package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;

public class AttendanceStorage {

    /**
     * Searches the current directory for a folder named DukeAttendance. If the folder exists, it will load
     * the attendances into the attendance list. Else it will create an empty folder called Attendance.
     *
     * @param attendanceList the current AttendanceList.
     */
    public static void setUpAttendanceStorage(AttendanceList attendanceList) {
        File currentDir = new File("");
        try {
            String AttendanceFolderPath = currentDir.getCanonicalPath() + "/Attendance";
            File AttendanceFolder = new File(AttendanceFolderPath);
            if (AttendanceFolder.isDirectory()) {
                loadAttendanceFiles(AttendanceFolder, attendanceList);
            } else {
                new File(AttendanceFolderPath).mkdirs();
            }
        } catch (IOException e) {
            System.out.println("I/O error has occurred");
        }
    }

    /**
     * loads all attendances from all CSV files in the Attendance folder of current directory into the list.
     *
     * @param dukeAttendanceFolder the folder path containing all the attendances.
     * @param attendanceList       the current AttendanceList.
     */
    public static void loadAttendanceFiles(File dukeAttendanceFolder, AttendanceList attendanceList) {
        File[] dukeAttendanceFiles = dukeAttendanceFolder.listFiles();
        for (File file : dukeAttendanceFiles) {
            loadIndividualAttendanceFile(file, attendanceList);
        }
    }

    /**
     * loads attendances from an individual CSV into the current attendance list.
     *
     * @param attendanceCsvFile the current CSV file with attendances to be loaded.
     * @param attendanceList    the current AttendanceList.
     */
    public static void loadIndividualAttendanceFile(File attendanceCsvFile, AttendanceList attendanceList) {
        String fileName = attendanceCsvFile.getName();
        String name;
        String trainingName = getFileTrainingName(fileName);
        String attended;
        try {
            Scanner dukeAttendanceScanner = new Scanner(attendanceCsvFile);
            dukeAttendanceScanner.nextLine();
            while (dukeAttendanceScanner.hasNextLine()) {
                String fullAttendanceDetails = dukeAttendanceScanner.nextLine();
                String[] attendanceDetails = fullAttendanceDetails.split("\\,", 2);
                name = attendanceDetails[0];
                attended = attendanceDetails[1];
                Attendance attendance = new Attendance(name, trainingName, attended);
                attendanceList.addAttendance(attendance);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }

    /**
     * Adds to the CSV file if training name of attendance corresponding to it already exists. 
     * If the csv file does not exist, it will create a new CSV file and add to it.
     *
     * @param attendance     the attendance entry to be added.
     * @param attendanceList the current AttendanceList.
     */
    public static void writeToAttendance(AttendanceList attendanceList, Attendance attendance) {
        String trainingName = attendance.getTrainingName();
        File currentDir = new File("");
        try {
            String AttendanceFilePath = currentDir.getCanonicalPath() + "/Attendance/" + trainingName + ".csv";
            File SpecificAttendanceFile = new File(AttendanceFilePath);
            if (SpecificAttendanceFile.exists()) {
                rewriteAttendanceCsv(attendanceList, SpecificAttendanceFile, trainingName);
            } else {
                initializeAttendanceCsv(attendanceList, attendance);
            }
        } catch ( IOException e) {
            System.out.println("I/O error has occurred");
        }
    }

    /**
     * Initializes a new Attendance CSV file inside Attendance folder based on training name of attendance passed in.
     *
     * @param attendanceList the current AttendanceList.
     * @param attendance     the attendance to be written in the file.
     */
    public static void initializeAttendanceCsv(AttendanceList attendanceList, Attendance attendance) {
        String trainingName = attendance.getTrainingName();
        File currentDir = new File("");
        try {
            String dukeAttendanceFilePath = currentDir.getCanonicalPath() + "/Attendance/" + trainingName + ".csv";
            File attendanceCsvFile = new File(dukeAttendanceFilePath);
            attendanceCsvFile.createNewFile();
            writeFirstAttendance(attendanceCsvFile, attendance);
        } catch (IOException e) {
            System.out.println("I/O error has occurred");
        }
    }

    /**
     * Writes the headings of the attendance file along with a single entry afterwards.
     *
     * @param attendanceCsvFile the CSV file to write to.
     * @param attendance        the attendance to be written in the file.
     */
    public static void writeFirstAttendance(File attendanceCsvFile, Attendance attendance) {
        try (PrintWriter dukeAttendanceWriter = new PrintWriter(attendanceCsvFile)) {
            dukeAttendanceWriter.write("name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("attendance");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write('\n');
            dukeAttendanceWriter.write(attendance.getMemberName());
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write(attendance.getAttended());
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }

    /**
     * Rewrites the entire specific csv file.
     *
     * @param attendanceList        the current attendance list
     * @param currentAttendanceFile the current attendance file
     */
    public static void rewriteAttendanceCsv(AttendanceList attendanceList, File currentAttendanceFile,
                                            String trainingName) {
        int attendanceListSize = attendanceList.getAttendanceListSize();
        try (PrintWriter dukeAttendanceWriter = new PrintWriter(currentAttendanceFile)) {
            dukeAttendanceWriter.write("name");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write("attendance");
            dukeAttendanceWriter.write(',');
            dukeAttendanceWriter.write('\n');
            for (int i = 1; i <= attendanceListSize; i++) {
                if (attendanceList.getAttendanceTrainingName(i).equals(trainingName)) {
                    dukeAttendanceWriter.write(attendanceList.getAttendanceMemberName(i));
                    dukeAttendanceWriter.write(',');
                    dukeAttendanceWriter.write(attendanceList.getAttendancePresentOrLate(i));
                    dukeAttendanceWriter.write('\n');
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        }
    }


    /**
     * Removes the extension from the file name.
     * Returns the training Name of the CSV file.
     *
     * @param filename full file name including extension.
     */
    public static String getFileTrainingName(String filename) {
        int fileNameLength = filename.length();
        int numberToRemove = fileNameLength - 4;
        String trainingName = filename.substring(0, numberToRemove);
        return trainingName;
    }


    /**
     * Deletes the attendance entry from the current attendance list based on its training name and index.
     *
     * @param attendanceList the current attendance list.
     * @param trainingName   name of training.
     * @param index          index of attendance.
     */
    public static void deleteAttendance(AttendanceList attendanceList, String trainingName, int index) {
        System.out.println("trying to delete");
        System.out.println(trainingName);
        System.out.println(index);
        int count = 1;
        for (int i = 1; i <= attendanceList.getAttendanceListSize(); i++) {
            if (attendanceList.getAttendanceTrainingName(i).equals(trainingName)) {
                if (count == index) {
                    attendanceList.deleteAttendance(i);
                    break;
                } else {
                    count++;
                }
            }
        }
    }

    /**
     * Rewrites the entire CSV file after an attendance with the corresponding training name is deleted.
     *
     * @param attendanceList the current attendance list.
     * @param trainingName
     */
    public static void handleDeleteAttendanceCsv(AttendanceList attendanceList, String trainingName)  {
        File currentDir = new File("");
        try {
            String AttendanceFilePath = currentDir.getCanonicalPath() + "/Attendance/" + trainingName + ".csv";
            File SpecificAttendanceFile = new File(AttendanceFilePath);
            rewriteAttendanceCsv(attendanceList, SpecificAttendanceFile, trainingName);
        } catch( IOException e) {
            System.out.println("I/O error has occurred");
        }
    }
}
