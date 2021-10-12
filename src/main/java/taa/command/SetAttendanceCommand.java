package taa.command;

import taa.attendance.AttendanceList;
import taa.exception.TaaException;
import taa.Ui;
import taa.attendance.Attendance;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;
import taa.util.Util;

import java.util.ArrayList;

public class SetAttendanceCommand extends Command {
    private static final String MESSAGE_FORMAT_SET_ATTENDANCE =
            "Attendance set:\n  %s - %s\nfor Lesson %s";

    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_NUM = "s";
    private static final String KEY_LESSON_NUM = "l";
    private static final String KEY_ATTENDANCE_MARK = "attend";
    private static final String ATTENDANCE_MARK_PRESENT = "1";
    private static final String ATTENDANCE_MARK_ABSENT = "0";
    private static final String[] SET_ATTENDANCE_ARGUMENT_KEYS = {KEY_MODULE_CODE, KEY_STUDENT_NUM, KEY_LESSON_NUM
            , KEY_ATTENDANCE_MARK};

    private static final int MIN_STUDENT_NUM = 1;
    private static final int MAX_LESSON_INDEX = 12;
    private static final int MIN_LESSON_INDEX = 0;

    private static final String MESSAGE_FORMAT_SET_ATTENDANCE_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE> %s/<STUDENT_INDEX> %s/<LESSON_INDEX> %s/<ATTENDANCE>";


    public SetAttendanceCommand(String argument) {
        super(argument, SET_ATTENDANCE_ARGUMENT_KEYS);
    }

    /**
     * Sets the attendance of a student to 1 or 0
     *
     * @param moduleList The list of modules
     * @param ui         The ui instance to handle interactions with the user
     * @throws TaaException If the user inputs an invalid command
     */
    public void execute(ModuleList moduleList, Ui ui) throws TaaException {
        checkIfArgumentIsEmpty();
        checkIfArgumentIsMissing();

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        checkIfModuleListIsEmpty(module);

        ArrayList<Student> students = module.getStudentList().getStudents();
        checkIfStudentsIsEmpty(students);

        String studentNum = argumentMap.get(KEY_STUDENT_NUM);
        checkIfStudentNumIsInteger(studentNum);
        checkIfStudentNumOutOfBounds(module, studentNum);

        int studentIndex = Integer.parseInt(studentNum) - 1;
        Student student = module.getStudentList().getStudentAt(studentIndex);

        String lessonNum = argumentMap.get(KEY_LESSON_NUM);
        checkIfLessonNumIsInteger(lessonNum);

        int lessonIndex = Integer.parseInt(lessonNum) - 1;
        checkIfLessonIndexOutOfBounds(lessonIndex);

        boolean hasAttend = hasAttend();
        Attendance attendance = new Attendance(moduleCode, Integer.toString(studentIndex),
                Integer.toString(lessonIndex), hasAttend);
        student.setAttendance(attendance);

        AttendanceList attendances = module.getAttendanceList();
        module.addAttendances(attendances, student);

        ui.printMessage(String.format(MESSAGE_FORMAT_SET_ATTENDANCE,
                student.getName(), attendance.markAttendance(), attendance.getLessonNum()));
    }

    private void checkIfArgumentIsEmpty() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
    }

    private void checkIfArgumentIsMissing() throws TaaException {
        if (!checkArgumentMap()) {
            throw new TaaException(getMissingArgumentMessage());
        }
    }

    private void checkIfModuleListIsEmpty(Module module) throws TaaException {
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }
    }

    private void checkIfStudentsIsEmpty(ArrayList<Student> students) throws TaaException {
        if (students.isEmpty()) {
            throw new TaaException(MESSAGE_NO_STUDENTS);
        }
    }

    private void checkIfStudentNumIsInteger(String studentNum) throws TaaException {
        boolean isStudentNumAnInteger = Util.isStringInteger(studentNum);
        if (!isStudentNumAnInteger) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
    }

    private void checkIfStudentNumOutOfBounds(Module module, String studentNum) throws TaaException {
        if (Integer.parseInt(studentNum) > module.getStudentList().getSize() || Integer.parseInt(studentNum) < MIN_STUDENT_NUM) {
            throw new TaaException(MESSAGE_STUDENT_INDEX_OUT_OF_BOUNDS);
        }
    }

    private void checkIfLessonNumIsInteger(String lessonNum) throws TaaException {
        boolean isLessonNumAnInteger = Util.isStringInteger(lessonNum);
        if (!isLessonNumAnInteger) {
            throw new TaaException(MESSAGE_INVALID_LESSON_INDEX);
        }
    }

    private void checkIfLessonIndexOutOfBounds(int lessonIndex) throws TaaException {
        if (lessonIndex > MAX_LESSON_INDEX || lessonIndex < MIN_LESSON_INDEX) {
            throw new TaaException(MESSAGE_LESSON_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Returns whether a student has attended a lesson, based on the attendance mark the user inputs
     *
     * @return whether a student has attended a lesson
     * @throws TaaException If the user enters an invalid attendance mark
     */
    private boolean hasAttend() throws TaaException {
        if (argumentMap.get(KEY_ATTENDANCE_MARK).equals(ATTENDANCE_MARK_PRESENT)) {
            return true;
        } else if (argumentMap.get(KEY_ATTENDANCE_MARK).equals(ATTENDANCE_MARK_ABSENT)) {
            return false;
        } else {
            throw new TaaException(MESSAGE_INVALID_ATTENDANCE);
        }
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_SET_ATTENDANCE_USAGE,
                COMMAND_SET_ATTENDANCE,
                KEY_MODULE_CODE,
                KEY_STUDENT_NUM,
                KEY_LESSON_NUM,
                KEY_ATTENDANCE_MARK
        );
    }
}
