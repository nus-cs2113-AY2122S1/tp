package taa.command;

import taa.Ui;
import taa.attendance.AttendanceList;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.storage.Storage;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class ListAttendanceCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String[] LIST_ATTENDANCE_ARGUMENT_KEYS = {KEY_MODULE_CODE, KEY_STUDENT_INDEX};

    private static final String MESSAGE_FORMAT_LIST_ATTENDANCE_USAGE = "Usage: %s %s/<MODULE_CODE> %s/<STUDENT_INDEX>";
    protected static final String MESSAGE_FORMAT_NO_ATTENDANCE = "There is no recorded attendance for %s.";
    private static final String MESSAGE_FORMAT_OUTPUT = "Attendance for %s:\n%s";

    public ListAttendanceCommand(String argument) {
        super(argument, LIST_ATTENDANCE_ARGUMENT_KEYS);
    }

    /** Lists out the attendance for all students in a module.
     * @param moduleList The list of modules
     * @param ui         The ui instance to handle interactions with the user
     * @throws TaaException If the user inputs an invalid command
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
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
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = module.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        String message;
        AttendanceList attendanceList = student.getAttendanceList();
        if (attendanceList.getSize() == 0) {
            message = String.format(MESSAGE_FORMAT_NO_ATTENDANCE, student);
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, student, attendanceList);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_LIST_ATTENDANCE_USAGE,
                COMMAND_LIST_ATTENDANCE,
                KEY_MODULE_CODE,
                KEY_STUDENT_INDEX
        );
    }
}
