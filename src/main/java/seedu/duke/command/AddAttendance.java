package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.attendance.Attendance;
import seedu.duke.storage.AttendanceStorage;
import seedu.duke.command.exception.InvalidAddAttendanceException;

/**
 * Adds an Attendance object to the AttendanceList ArrayList.
 */
public class AddAttendance {

    String invalidMemberNameErrorMessage = "Blank member name provided. Please input a proper member name.";
    String invalidTrainingNameErrorMessage = "Blank training name provided. Please input a proper training name.";
    String invalidAttendedErrorMessage = "Invalid status provided. Please enter 1 for present, 0 for absent.";

    String validAttendedRegex = "^[1|0]";

    private String memberName;
    private String trainingName;
    private String presentOrAbsent;

    /**
     * Constructor. Adds Attendance to the AttendanceList and saves it to hard disk.
     *
     * @param attendanceList Contains all attendance objects added.
     * @param attendance Attendance object to be added.
     */
    public AddAttendance(AttendanceList attendanceList, Attendance attendance) {
        try {
            boolean validAttendance = verifyAttendanceDetails(attendance);
            if (validAttendance) {
                attendanceList.addAttendance(attendance);
                Ui.printAddedAttendanceMessage(attendance);
                AttendanceStorage.writeToAttendance(attendanceList, attendance);
            }
        } catch (InvalidAddAttendanceException e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Verify attendance details. Ensure member name, training name and status is in the correct format else throw
     * exception
     *
     * @param attendance Attendance to be added to AttendanceList.
     * @return true if all parameters given are valid
     * @throws InvalidAddAttendanceException If there is an error with any of the parameters given.
     */
    public boolean verifyAttendanceDetails(Attendance attendance) throws InvalidAddAttendanceException {
        memberName = attendance.getMemberName();
        trainingName = attendance.getTrainingName();
        presentOrAbsent = attendance.getAttended();

        boolean validMemberName = memberName != null && !memberName.trim().isEmpty();
        if (!validMemberName) {
            throw new InvalidAddAttendanceException(invalidMemberNameErrorMessage);
        }

        boolean validTrainingName = trainingName != null && !trainingName.trim().isEmpty();
        if (!validTrainingName) {
            throw new InvalidAddAttendanceException(invalidTrainingNameErrorMessage);
        }

        boolean validAttended = presentOrAbsent.matches(validAttendedRegex);
        if (!validAttended) {
            throw new InvalidAddAttendanceException(invalidAttendedErrorMessage);
        }

        return true;
    }
}
