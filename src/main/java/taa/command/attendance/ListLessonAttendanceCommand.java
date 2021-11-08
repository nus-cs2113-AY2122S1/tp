package taa.command.attendance;

//@@author daknam2001
import taa.Ui;
import taa.attendance.Attendance;
import taa.attendance.LessonAttendanceList;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.student.StudentList;
import taa.util.Util;

public class ListLessonAttendanceCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_LESSON_NUMBER = "l";
    private static final int MAX_LESSON_NUMBER = 1000;
    private static final String[] LIST_LESSON_ATTENDANCE_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_LESSON_NUMBER};

    private static final String MESSAGE_FORMAT_LIST_LESSON_ATTENDANCE_USAGE = "%s %s/<CLASS_ID> %s/<LESSON_NUMBER>";
    protected static final String MESSAGE_FORMAT_NO_ATTENDANCE = "There is no recorded attendance for Lesson %s.";
    private static final String MESSAGE_FORMAT_OUTPUT = "Class attendance for Lesson %s:\n%s";

    public ListLessonAttendanceCommand(String argument) {
        super(argument, LIST_LESSON_ATTENDANCE_ARGUMENT_KEYS);
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
     * Executes the list_lesson_attendance command and list all the attendance records of a lesson.
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

        String lessonNumberInput = argumentMap.get(KEY_LESSON_NUMBER);
        assert Util.isStringInteger(lessonNumberInput);
        int lessonNumber = Integer.parseInt(lessonNumberInput);
        if (lessonNumber < Attendance.getMinLessonNumber()) {
            throw new TaaException(MESSAGE_INVALID_LESSON_NUMBER);
        }

        StudentList studentList = teachingClass.getStudentList();
        LessonAttendanceList lessonAttendances = new LessonAttendanceList();
        lessonAttendances.setLessonAttendances(studentList, lessonNumber);

        String message;
        if (lessonAttendances.getSize() == 0) {
            message = String.format(MESSAGE_FORMAT_NO_ATTENDANCE, lessonNumberInput);
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, lessonNumberInput, lessonAttendances);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
                MESSAGE_FORMAT_LIST_LESSON_ATTENDANCE_USAGE,
                COMMAND_LIST_LESSON_ATTENDANCE,
            KEY_CLASS_ID,
                KEY_LESSON_NUMBER
        );
    }
}
