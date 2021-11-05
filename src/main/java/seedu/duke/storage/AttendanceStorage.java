package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import seedu.duke.Ui;
import seedu.duke.attendance.Attendance;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.command.exception.InvalidAddAttendanceException;
import seedu.duke.command.exception.InvalidAddMemberException;

public class AttendanceStorage {

    static String duplicateAttendanceErrorExcelMessage = "Duplicates name found in one of the attendance CSV files."
            + "Please fix this before running the program again.";
    static String invalidAttendanceNameErrorExcelMessage = "Invalid name found in one of the attendance CSV files."
            + "Please fix this before running the program again.";
    static String invalidAttendanceStatusEntry = "Invalid name found in one of the attendance CSV files"
            + "Please fix this before running the program again.";

    /**
     * Searches the current directory for a folder named DukeAttendance. If the folder exists, it will load
     * the attendances into the attendance list. Else it will create an empty folder called Attendance.
     *
     * @param attendanceList the current AttendanceList.
     */
    public static void setUpAttendanceStorage(AttendanceList attendanceList) {
        File currentDir = new File("");
        try {
            String attendanceFolderPath = currentDir.getCanonicalPath() + "/Attendance";
            File attendanceFolder = new File(attendanceFolderPath);
            if (attendanceFolder.isDirectory()) {
                System.out.println("CCA Attendance file found & loaded");
                loadAttendanceFiles(attendanceFolder, attendanceList);
            } else {
                System.out.println("CCA Attendance file not detected. Creating.");
                new File(attendanceFolderPath).mkdirs();
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
            verifyIndividualAttendanceFile(file);
            loadIndividualAttendanceFile(file, attendanceList);
        }
    }

    /**
     * Verifies a single CSV file inputs to see if it is valid.
     *
     * @param attendanceCsvFile the current CSV file with attendances to be verified.
     */
    private static void verifyIndividualAttendanceFile(File attendanceCsvFile) {
        verifyAttendanceDetailsValid(attendanceCsvFile);
        verifyNoDuplicates(attendanceCsvFile);
    }

    /**
     * Verifies that there are no duplicate names in the current attendance CSV file.
     *
     * @param attendanceCsvFile the current CSV file with attendances to be verified.
     */
    private static void verifyNoDuplicates(File attendanceCsvFile) {
        String attendanceName;
        List<String> pendingAttendanceName = new ArrayList<String>();
        try {
            Scanner attendanceScanner = new Scanner(attendanceCsvFile);
            attendanceScanner.nextLine();
            while (attendanceScanner.hasNextLine()) {
                String fullAttendanceDetails = attendanceScanner.nextLine();
                String[] attendanceDetails = fullAttendanceDetails.split("\\,", 2);
                attendanceName = attendanceDetails[0];
                pendingAttendanceName.add(attendanceName);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyTrainingFile();
        }
        try {
            checkDuplicates(pendingAttendanceName);
        } catch (InvalidAddAttendanceException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Checks for any duplicates in the list.
     *
     * @param pendingList current list of Strings to check.
     * @throws InvalidAddMemberException if there are any duplicates.
     */
    private static void checkDuplicates(List<String> pendingList) throws InvalidAddAttendanceException {
        for (int i = 0; i < pendingList.size(); i++) {
            for (int j = i + 1; j < pendingList.size(); j++) {
                if (pendingList.get(i).equals(pendingList.get(j))) {
                    throw new InvalidAddAttendanceException(duplicateAttendanceErrorExcelMessage);
                }
            }
        }
    }

    /**
     * Verifies that all entries in CSV file is valid.
     *
     * @param attendanceCsvFile the current CSV file with attendances to be verified.
     */
    private static void verifyAttendanceDetailsValid(File attendanceCsvFile) {
        String name;
        String attendanceStatus;
        try {
            Scanner attendanceScanner = new Scanner(attendanceCsvFile);
            attendanceScanner.nextLine();
            while (attendanceScanner.hasNextLine()) {
                String fullAttendanceDetails = attendanceScanner.nextLine();
                String[] attendanceDetails = fullAttendanceDetails.split("\\,", 2);
                name = attendanceDetails[0];
                verifyMemberName(name);
                attendanceStatus = attendanceDetails[1];
                verifyAttendanceStatus(attendanceStatus);
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found!");
        } catch (NoSuchElementException e) {
            Ui.printEmptyMembersFile();
        } catch (InvalidAddAttendanceException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Checks if attendance status is valid.
     *
     * @param attendanceStatus should be either 1 or 0.
     */
    private static void verifyAttendanceStatus(String attendanceStatus) throws InvalidAddAttendanceException {
        int attendanceIntegerValue;
        try {
            attendanceIntegerValue = Integer.parseInt(attendanceStatus);
            boolean validAttendanceStatus = (attendanceIntegerValue == 1 || attendanceIntegerValue == 0);
            if (!validAttendanceStatus) {
                throw new InvalidAddAttendanceException(invalidAttendanceStatusEntry);
            }
        } catch (NumberFormatException e) {
            System.out.println(invalidAttendanceStatusEntry);
            System.exit(0);
        }
    }

    /**
     * Checks if member name is valid.
     *
     * @param name the member name to be checked.
     * @throws InvalidAddMemberException when member name is invalid.
     */
    private static void verifyMemberName(String name) throws InvalidAddAttendanceException {
        boolean nameEmpty = (name == null || name.trim().isEmpty());
        boolean nameContainDigits = name.matches(".*\\d.*");
        boolean validName = !nameEmpty && !nameContainDigits;
        if (!validName) {
            throw new InvalidAddAttendanceException(invalidAttendanceNameErrorExcelMessage);
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
            String attendanceFilePath = currentDir.getCanonicalPath() + "/Attendance/" + trainingName + ".csv";
            File specificAttendanceFile = new File(attendanceFilePath);
            if (specificAttendanceFile.exists()) {
                rewriteAttendanceCsv(attendanceList, specificAttendanceFile, trainingName);
            } else {
                initializeAttendanceCsv(attendanceList, attendance);
            }
        } catch (IOException e) {
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
        assert index >= 1;
        int count = 1;
        for (int i = 1; i <= attendanceList.getAttendanceListSize(); i++) {
            if (attendanceList.getAttendanceTrainingName(i).equals(trainingName)) {
                if (count == index) {
                    Attendance toDelete = attendanceList.deleteAttendance(i);
                    Ui.printDeletedAttendanceMessage(toDelete);
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
     * @param trainingName   name of training.
     */
    public static void handleDeleteAttendanceCsv(AttendanceList attendanceList, String trainingName) {
        File currentDir = new File("");
        try {
            String attendanceFilePath = currentDir.getCanonicalPath() + "/Attendance/" + trainingName + ".csv";
            File specificAttendanceFile = new File(attendanceFilePath);
            rewriteAttendanceCsv(attendanceList, specificAttendanceFile, trainingName);
        } catch (IOException e) {
            System.out.println("I/O error has occurred");
        }
    }
}
