package taa.command;

import taa.Ui;
import taa.attendance.AttendanceList;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

import java.util.ArrayList;

public class ListAttendanceCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String[] LIST_ATTENDANCE_ARGUMENT_KEYS = {KEY_MODULE_CODE};

    private static final String MESSAGE_ATTENDANCE_LIST_HEADER = "Attendance List:";

    private static final String MESSAGE_FORMAT_LIST_ATTENDANCE_USAGE = "Usage: %s %s/<MODULE_CODE>";
    private static final int SPACE_FOR_NAME = 25;
    private static final int BEGIN_INDEX = 0;
    private static final int SPACE_FOR_TRIMMED_NAME = 22;
    private static final String LESSONS_TITLE = "LESSON NUMBER";

    public ListAttendanceCommand(String argument) {
        super(argument, LIST_ATTENDANCE_ARGUMENT_KEYS);
    }

    /**
     * @param moduleList The list of modules
     * @param ui         The ui instance to handle interactions with the user
     * @throws TaaException If the user inputs an invalid command
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui) throws TaaException {
        checkIfArgumentIsEmpty();
        checkIfArgumentIsMissing();

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        checkIfModuleListIsEmpty(module);

        ArrayList<Student> students = module.getStudents();
        checkIfStudentsIsEmpty(students);

        ArrayList<String> attendances = module.getAttendances();
        checkIfAttendancesIsEmpty(attendances);

        StringBuilder stringBuilder = new StringBuilder(MESSAGE_ATTENDANCE_LIST_HEADER);
        buildString(stringBuilder, module, students, attendances);

        ui.printMessage(stringBuilder.toString());
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

    private void checkIfAttendancesIsEmpty(ArrayList<String> attendances) throws TaaException {
        if (attendances.isEmpty()) {
            throw new TaaException(MESSAGE_ATTENDANCE_LIST_EMPTY);
        }
    }

    private void buildString(StringBuilder stringBuilder, Module module, ArrayList<Student> students,
                             ArrayList<String> attendances) {
        stringBuilder.append("\n");
        stringBuilder.append(padWithWhiteSpace(LESSONS_TITLE, LESSONS_TITLE.length()));
        stringBuilder.append(module.getFormattedLessons());
        for (int i = 0; i < attendances.size(); i += 1) {
            stringBuilder.append("\n");
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            String trimmedStudentName = trimName(students.get(i).getName(), i + 1);
            stringBuilder.append(trimmedStudentName);
            stringBuilder.append(attendances.get(i));
        }
    }

    /**
     * Trims the student's name if it is too long
     *
     * @param studentName name of the student
     * @param number      the length of the list number
     * @return truncated student name or student name with whitespace
     */
    private String trimName(String studentName, int number) {
        int lengthOfDot = 2;
        int lengthOfNumber = ((number / 10) + 1) + lengthOfDot;
        int lengthOfNumberAndName = lengthOfNumber + studentName.length();
        if (lengthOfNumberAndName > SPACE_FOR_NAME) {
            return studentName.substring(BEGIN_INDEX, SPACE_FOR_TRIMMED_NAME - lengthOfNumber) + "...";
        }
        return padWithWhiteSpace(studentName, lengthOfNumberAndName);
    }

    /**
     * Pads a string with whitespace
     *
     * @param stringToPad    string to pad with whitespace
     * @param lengthOfString length of the string to pad with whitespace
     * @return string with whitespace
     */
    private String padWithWhiteSpace(String stringToPad, int lengthOfString) {
        int amountToPad = SPACE_FOR_NAME - lengthOfString;
        for (int i = 0; i < amountToPad; i++) {
            stringToPad = stringToPad + " ";
        }
        return stringToPad;
    }


    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_LIST_ATTENDANCE_USAGE,
                COMMAND_LIST_ATTENDANCE,
                KEY_MODULE_CODE
        );
    }

}
