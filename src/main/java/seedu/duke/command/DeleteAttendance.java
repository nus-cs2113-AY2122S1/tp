package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.attendance.Attendance;

/**
 * Deletes an Attendance from the AttendanceList.
 */
public class DeleteAttendance {

    /**
     * Constructor. Deletes an Attendance object from the AttendanceList given its index.
     * @param attendanceList AttendanceList containing all Attendance.
     * @param index Index of the Attendance to delete. Note that the actual index is -1 of given index.
     */
    public DeleteAttendance(AttendanceList attendanceList, int index) {
        try {
            assert index >= 1;
            Attendance toDelete = attendanceList.deleteAttendance(index); //index-1 is handled in deleteAttendance()
            Ui.printDeletedAttendanceMessage(toDelete);

            //Update save file
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such member number...");
        } catch (AssertionError e) {
            System.out.println("The index to delete must be an integer >= 1");
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