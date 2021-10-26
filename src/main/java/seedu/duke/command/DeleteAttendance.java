package seedu.duke.command;

import seedu.duke.Ui;

import java.io.File;
import java.io.IOException;

import seedu.duke.attendance.AttendanceList;
import seedu.duke.attendance.Attendance;
import seedu.duke.Parser;

import seedu.duke.AttendanceStorage;

public class DeleteAttendance {
    public static String getName(AttendanceList filteredAttendenceList, int index) {
        return filteredAttendenceList.getAttendanceMemberName(index);
    }

    public DeleteAttendance(AttendanceList attendanceList, String entry, AttendanceList filteredAttendanceList,
                            int index) {
        File currentDir = new File("");
        String[] trainingNameAndLabel = entry.trim().split("/t");

        try {
            assert index >= 1;
            //index-1 is handled in deleteAttendance()
            String name = getName(filteredAttendanceList, index);
            String trainingName = Parser.getTrainingName(trainingNameAndLabel[1].trim());
            System.out.println(Parser.getIndexFromFullList(name, trainingName,
                    attendanceList));
            Attendance toDelete = attendanceList.deleteAttendance(Parser.getIndexFromFullList(name, trainingName,
                    attendanceList));

            System.out.println("===================");
            System.out.println(trainingName);
            String DukeAttendanceFilePath = currentDir.getCanonicalPath() + "\\DukeAttendance\\" + trainingName.trim() + ".csv";
            System.out.println(DukeAttendanceFilePath);
            File DukeSpecificAttendanceFile = new File(DukeAttendanceFilePath);

            if (DukeSpecificAttendanceFile.exists()) {
                AttendanceStorage.rewriteAttendanceCsv(filteredAttendanceList, DukeSpecificAttendanceFile,
                        trainingName);
            } else {
                System.out.println("AAAAAAAAAAAAAAAAAAAAAA");
            }
            Ui.printDeletedAttendanceMessage(toDelete);

            //Update save file
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such member number...");
        } catch (AssertionError e) {
            System.out.println("The index to delete must be an integer >= 1");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}

/* DELETE HERE
    /**
     * Removes an entry from a AttendanceList based on input index.
     *
     * @param attendanceList AttendanceList containing all Attendance entries.
     * @param query          String input that contains the integer index of the entry to remove.
     */

    /* DELETE HERE
    public static void deleteAttendance(AttendanceList attendanceList, String query) {
        try {
            int attNumber = getAttendanceIndex(query);
            Attendance entry = attendanceList.deleteAttendance(attNumber);
            assert entry != null : "entry should not be empty";
            Ui.printDeletedAttendanceMessage(entry);
        } catch (IndexOutOfBoundsException exception) {
            System.out.println("There is no such attendance number...");
        } catch (NumberFormatException e) {
            System.out.println("Please input a proper number...");
        }
    }
    DELETE HERE*/