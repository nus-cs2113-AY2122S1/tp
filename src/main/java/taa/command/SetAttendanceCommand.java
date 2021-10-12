package taa.command;

import taa.attendance.AttendanceList;
import taa.exception.TaaException;
import taa.Ui;
import taa.attendance.Attendance;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class SetAttendanceCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_LESSON_NUMBER = "l";
    private static final String KEY_PRESENT = "p";
    private static final String[] SET_ATTENDANCE_ARGUMENT_KEYS = {
        KEY_MODULE_CODE,
        KEY_STUDENT_INDEX,
        KEY_LESSON_NUMBER,
        KEY_PRESENT
    };

    private static final String MESSAGE_INVALID_LESSON_NUMBER = "Invalid lesson number.";

    private static final String MESSAGE_FORMAT_INVALID_PRESENT = "Invalid present value.\n"
            + "Possible values: %s (Present), %s (Absent)";
    private static final String MESSAGE_FORMAT_SET_ATTENDANCE = "Attendance set for %s:\n  %s";

    private static final int MIN_LESSON_NUMBER = 1;
    private static final String PRESENT_VALUE = "1";
    private static final String ABSENT_VALUE = "0";

    private static final String MESSAGE_FORMAT_SET_ATTENDANCE_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE> %s/<STUDENT_INDEX> %s/<LESSON_NUMBER> %s/<PRESENT>";

    public SetAttendanceCommand(String argument) {
        super(argument, SET_ATTENDANCE_ARGUMENT_KEYS);
    }

    /**
     * Sets the attendance of a student to 1 or 0.
     *
     * @param moduleList The list of modules
     * @param ui         The ui instance to handle interactions with the user
     * @throws TaaException If the user inputs an invalid command
     */
    public void execute(ModuleList moduleList, Ui ui) throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!checkArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        if (!Util.isStringInteger(studentIndexInput)) {
            throw new TaaException(MESSAGE_INVALID_LESSON_INDEX);
        }
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = module.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        String lessonNumberInput = argumentMap.get(KEY_LESSON_NUMBER);
        if (!Util.isStringInteger(lessonNumberInput)) {
            throw new TaaException(MESSAGE_INVALID_LESSON_NUMBER);
        }

        int lessonNumber = Integer.parseInt(lessonNumberInput);
        if (lessonNumber < MIN_LESSON_NUMBER) {
            throw new TaaException(MESSAGE_INVALID_LESSON_NUMBER);
        }

        String present = argumentMap.get(KEY_PRESENT);
        boolean isPresent;
        switch (present) {
        case PRESENT_VALUE:
            isPresent = true;
            break;

        case ABSENT_VALUE:
            isPresent = false;
            break;

        default:
            throw new TaaException(String.format(MESSAGE_FORMAT_INVALID_PRESENT, PRESENT_VALUE, ABSENT_VALUE));
        }

        AttendanceList attendanceList = student.getAttendanceList();
        Attendance attendance = attendanceList.getAttendance(lessonNumber);
        if (attendance == null) {
            attendance = new Attendance(lessonNumber, isPresent);
            attendanceList.addAttendance(attendance);
        } else {
            attendance.setPresent(isPresent);
        }

        ui.printMessage(String.format(MESSAGE_FORMAT_SET_ATTENDANCE, student.getName(), attendance));
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_SET_ATTENDANCE_USAGE,
                COMMAND_SET_ATTENDANCE,
                KEY_MODULE_CODE,
                KEY_STUDENT_INDEX,
                KEY_LESSON_NUMBER,
                KEY_PRESENT
        );
    }
}
