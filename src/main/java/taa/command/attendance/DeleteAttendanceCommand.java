package taa.command.attendance;

//@@author daknam2001
import taa.Taa;
import taa.Ui;
import taa.attendance.Attendance;
import taa.attendance.AttendanceList;
import taa.attendance.StudentIndexArray;
import taa.command.Command;
import taa.exception.TaaException;
import taa.student.StudentList;
import taa.teachingclass.TeachingClass;
import taa.teachingclass.ClassList;
import taa.storage.Storage;
import taa.student.Student;
import taa.util.Util;

public class DeleteAttendanceCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_LESSON_NUMBER = "l";
    private static final int MAX_LESSON_NUMBER = 1000;
    private static final String[] DELETE_ATTENDANCE_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_STUDENT_INDEX,
        KEY_LESSON_NUMBER
    };

    private static final String MESSAGE_FORMAT_DELETE_ATTENDANCE_USAGE =
        "%s %s/<CLASS_ID> %s/<STUDENT_INDEX> %s/<LESSON_NUMBER>";

    private static final String MESSAGE_FORMAT_ATTENDANCE_DELETED = "Attendance removed for:\n%s";
    private boolean doesStudentExist = true;

    public DeleteAttendanceCommand(String argument) {
        super(argument, DELETE_ATTENDANCE_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }


        String lessonNumInput = argumentMap.get(KEY_LESSON_NUMBER);
        if (!Util.isStringInteger(lessonNumInput)) {
            throw new TaaException(MESSAGE_INVALID_LESSON_NUMBER);
        }
        if (Integer.parseInt(lessonNumInput) > MAX_LESSON_NUMBER) {
            throw new TaaException(MESSAGE_LESSON_NUMBER_EXCEEDS_MAX);
        }
    }

    /**
     * Executes the delete_attendance command and deletes a student's attendance from the attendanceList.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String classId = argumentMap.get(KEY_CLASS_ID);
        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        String lessonNumInput = argumentMap.get(KEY_LESSON_NUMBER);
        assert Util.isStringInteger(lessonNumInput);

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        StudentIndexArray studentIndexes = new StudentIndexArray();
        studentIndexes.setStudentIndexes(studentIndexInput);

        StringBuilder stringBuilder = new StringBuilder();

        checkIndexOutOfBounds(teachingClass, studentIndexes, Integer.parseInt(lessonNumInput));

        if (!doesStudentExist) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        } else {
            for (int i = 0; i < studentIndexes.getSize(); i++) {
                int studentIndex = studentIndexes.getStudentIndex(i) - 1;

                Student student = teachingClass.getStudentList().getStudentAt(studentIndex);
                assert studentIndex >= 0 && studentIndex < teachingClass.getStudentList().getSize();

                AttendanceList attendanceList = student.getAttendanceList();
                Attendance attendance = attendanceList.deleteAttendance(lessonNumInput);
                if (attendance == null) {

                }
                buildString(stringBuilder, i, student, attendance);
            }
        }

        storage.save(classList);

        ui.printMessage(String.format(MESSAGE_FORMAT_ATTENDANCE_DELETED, stringBuilder));
    }

    /**
     * Checks whether the inputted student indexes are out of bounds.
     *
     * @param teachingClass  The teachingClass object.
     * @param studentIndexes The studentIndexes object, containing the array of inputted indexes.
     * @return
     */
    private void checkIndexOutOfBounds(TeachingClass teachingClass, StudentIndexArray studentIndexes,
                                       int lessonNumber) throws TaaException {
        for (int i = 0; i < studentIndexes.getSize(); i++) {
            int studentIndex = studentIndexes.getStudentIndex(i) - 1;

            StudentList studentList = teachingClass.getStudentList();
            Student student = studentList.getStudentAt(studentIndex);
            if (student == null) {
                doesStudentExist = false;
                return;
            }
            Attendance attendance = student.getAttendanceList().getAttendance(lessonNumber);
            if (attendance == null){
                throw new TaaException(String.format(MESSAGE_INVALID_LESSON_NUMBER_WITH_INFO, studentIndex + 1,
                        student.getName()));
            }
        }
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
            MESSAGE_FORMAT_DELETE_ATTENDANCE_USAGE,
            COMMAND_DELETE_ATTENDANCE,
            KEY_CLASS_ID,
            KEY_STUDENT_INDEX,
            KEY_LESSON_NUMBER
        );
    }
}
