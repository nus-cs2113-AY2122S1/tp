//@@author izdiyadfrhn

package seedu.duke.attendance.exception;

import seedu.duke.attendance.AttendanceList;

public class InvalidAttendanceException extends Exception {

    private AttendanceList entries;

    public InvalidAttendanceException(String message) {
        super(message);
    }

    public InvalidAttendanceException(String message, AttendanceList entries) {
        super(message);
        setEntries(entries);
    }

    public AttendanceList getEntries() {
        return entries;
    }

    public void setEntries(AttendanceList entries) {
        this.entries = entries;
    }
}
