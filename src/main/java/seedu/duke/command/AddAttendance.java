package seedu.duke.command;

import seedu.duke.Ui;
import seedu.duke.attendance.AttendanceList;
import seedu.duke.attendance.Attendance;
import seedu.duke.member.MemberList;
import seedu.duke.storage.AttendanceStorage;
import seedu.duke.command.exception.InvalidAddAttendanceException;
import seedu.duke.training.TrainingList;

/**
 * Adds an Attendance object to the AttendanceList ArrayList.
 */
public class AddAttendance {

    String emptyMemberNameErrorMessage = "Blank member name provided. Please input a valid member name.";
    String invalidMemberNameErrorMessage = "Invalid member. Please input an existing member.";
    String emptyTrainingNameErrorMessage = "Blank training name provided. Please input a valid training name.";
    String invalidTrainingNameErrorMessage = "Invalid training. Please input an existing training.";
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
    public AddAttendance(AttendanceList attendanceList, Attendance attendance, MemberList members, 
                         TrainingList trainings) {
        try {
            boolean validAttendance = verifyAttendanceDetails(attendance, members, trainings);
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
    public boolean verifyAttendanceDetails(Attendance attendance, MemberList members, TrainingList trainings)
        throws InvalidAddAttendanceException {
        memberName = attendance.getMemberName();
        trainingName = attendance.getTrainingName();
        presentOrAbsent = attendance.getAttended();

        boolean filledMemberName = memberName != null && !memberName.trim().isEmpty();
        if (!filledMemberName) {
            throw new InvalidAddAttendanceException(emptyMemberNameErrorMessage);
        }

        MemberList membersThatMatchFind = members.findMember(memberName);
        boolean validMemberName = filledMemberName && !membersThatMatchFind.getMemberList().isEmpty();
        if (!validMemberName) {
            throw new InvalidAddAttendanceException(invalidMemberNameErrorMessage);
        }

        boolean filledTrainingName = trainingName != null && !trainingName.trim().isEmpty();
        if (!filledTrainingName) {
            throw new InvalidAddAttendanceException(emptyTrainingNameErrorMessage);
        }

        TrainingList trainingsThatMatchFind = trainings.findTraining(trainingName);
        boolean validTrainingName = filledTrainingName && !trainingsThatMatchFind.getTrainingList().isEmpty();
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
