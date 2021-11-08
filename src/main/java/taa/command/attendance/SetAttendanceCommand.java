package taa.command.attendance;

//@@author daknam2001

import taa.attendance.AttendanceList;
import taa.attendance.StudentIndexArray;
import taa.teachingclass.ClassList;
import taa.command.Command;
import taa.exception.TaaException;
import taa.Ui;
import taa.attendance.Attendance;
import taa.teachingclass.TeachingClass;
import taa.storage.Storage;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class SetAttendanceCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_LESSON_NUMBER = "l";
    private static final String KEY_PRESENT = "p";
    private static final int MAX_LESSON_NUMBER = 1000;
    private static final String[] SET_ATTENDANCE_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_STUDENT_INDEX,
        KEY_LESSON_NUMBER,
        KEY_PRESENT
    };

    private static final String MESSAGE_FORMAT_INVALID_PRESENT = "Invalid present value.\n"
            + "Possible values: %s (Present), %s (Absent)";
    private static final String MESSAGE_FORMAT_SET_ATTENDANCE = "Attendance set for:\n%s";

    private static final String PRESENT_VALUE = "1";
    private static final String ABSENT_VALUE = "0";

    private static final String MESSAGE_FORMAT_SET_ATTENDANCE_USAGE =
            "%s %s/<CLASS_ID> %s/[<STUDENT_INDEX> | <START_STUDENT_INDEX>-<END_STUDENT_INDEX> | "
            + "<STUDENT_INDEX>,<STUDENT_INDEX>,...] %s/<LESSON_NUMBER> %s/<PRESENT>";


    public SetAttendanceCommand(String argument) {
        super(argument, SET_ATTENDANCE_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String lessonNumberInput = argumentMap.get(KEY_LESSON_NUMBER);
        if (!Util.isStringInteger(lessonNumberInput)) {
            throw new TaaException(MESSAGE_INVALID_LESSON_NUMBER);
        }
        if (Integer.parseInt(lessonNumberInput) > MAX_LESSON_NUMBER) {
            throw new TaaException(MESSAGE_LESSON_NUMBER_EXCEEDS_MAX);
        }
    }

    /**
     * Executes the set_attendance command and sets the attendance of a student.
     *
     * @param classList The list of classes.
     * @param ui        The ui instance to handle interactions with the user.
     * @param storage   The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String classId = argumentMap.get(KEY_CLASS_ID);
        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        String lessonNumberInput = argumentMap.get(KEY_LESSON_NUMBER);
        assert Util.isStringInteger(lessonNumberInput);
        int lessonNumber = Integer.parseInt(lessonNumberInput);
        if (lessonNumber < Attendance.getMinLessonNumber()) {
            throw new TaaException(MESSAGE_INVALID_LESSON_NUMBER);
        }

        String present = argumentMap.get(KEY_PRESENT);
        boolean isPresent = setIsPresent(present);

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        StudentIndexArray studentIndexes = new StudentIndexArray();
        studentIndexes.setStudentIndexes(studentIndexInput);

        StringBuilder stringBuilder = new StringBuilder();

        if (isIndexOutOfBounds(teachingClass, studentIndexes)) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        } else {
            for (int i = 0; i < studentIndexes.getSize(); i++) {
                int studentIndex = studentIndexes.getStudentIndex(i) - 1;

                StudentList studentList = teachingClass.getStudentList();
                assert studentIndex >= 0 && studentIndex < teachingClass.getStudentList().getSize();
                Student student = studentList.getStudentAt(studentIndex);

                AttendanceList attendanceList = student.getAttendanceList();
                assert lessonNumber > 0 && lessonNumber <= 1000;
                Attendance attendance = attendanceList.getAttendance(lessonNumber);
                if (attendance == null) {
                    attendance = new Attendance(lessonNumber, isPresent);
                    attendanceList.addAttendance(attendance);
                } else {
                    attendance.setPresent(isPresent);
                }

                buildString(stringBuilder, i, student, attendance);
            }
        }
        storage.save(classList);

        ui.printMessage(String.format(MESSAGE_FORMAT_SET_ATTENDANCE, stringBuilder));
    }

    /**
     * Sets the boolean variable isPresent based on the user input for present value.
     * @param present The present value of the student. Can be 1 or 0.
     * @return True if present value is 1. False if present value is 0.
     * @throws TaaException If the user inputs an invalid format for present value.
     */
    private boolean setIsPresent(String present) throws TaaException {
        switch (present) {
        case PRESENT_VALUE:
            return true;

        case ABSENT_VALUE:
            return false;

        default:
            throw new TaaException(String.format(MESSAGE_FORMAT_INVALID_PRESENT, PRESENT_VALUE, ABSENT_VALUE));
        }
    }

    /**
     * Checks whether the inputted student indexes are out of bounds.
     *
     * @param teachingClass  The teachingClass object.
     * @param studentIndexes The studentIndexes object, containing the array of inputted indexes.
     * @return True if student indexes are out of bounds, False otherwise.
     */
    private boolean isIndexOutOfBounds(TeachingClass teachingClass, StudentIndexArray studentIndexes) {
        for (int i = 0; i < studentIndexes.getSize(); i++) {
            int studentIndex = studentIndexes.getStudentIndex(i) - 1;

            StudentList studentList = teachingClass.getStudentList();
            Student student = studentList.getStudentAt(studentIndex);
            if (student == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Builds a string.
     *
     * @param stringBuilder The stringBuilder object.
     * @param i             The index in the StudentIndexes ArrayList.
     * @param student       The student object.
     * @param attendance    The attendance object.
     */
    private void buildString(StringBuilder stringBuilder, int i, Student student, Attendance attendance) {
        if (i > 0) {
            stringBuilder.append("\n");
        }
        stringBuilder.append("  ");
        stringBuilder.append(student.getName());
        stringBuilder.append(" - ");
        stringBuilder.append(attendance);
    }

    @Override
    protected String getUsage() {
        return String.format(
                MESSAGE_FORMAT_SET_ATTENDANCE_USAGE,
                COMMAND_SET_ATTENDANCE,
                KEY_CLASS_ID,
                KEY_STUDENT_INDEX,
                KEY_LESSON_NUMBER,
                KEY_PRESENT
        );
    }
}
