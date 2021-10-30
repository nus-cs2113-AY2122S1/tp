package taa.command.student;

//@@author hozhenhong99
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.classmodel.ClassObject;
import taa.classmodel.ClassList;
import taa.student.Student;
import taa.student.StudentList;

import java.util.ArrayList;

public class FindStudentCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_KEYWORD = "k";
    private static final String[] FIND_STUDENT_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_KEYWORD};

    private static final String MESSAGE_NO_STUDENTS_FOUND = "There are no students matching your keyword.";

    private static final String MESSAGE_FORMAT_FIND_STUDENT_USAGE = "%s %s/<CLASS_ID> %s/<KEYWORD>";
    private static final String MESSAGE_FORMAT_STUDENT_FOUND_HEADER = "Here are the students in %s with \"%s\":";

    public FindStudentCommand(String argument) {
        super(argument, FIND_STUDENT_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }
    }

    /**
     * Executes the find_student command and finds students in the class based on a keyword.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String classId = argumentMap.get(KEY_CLASS_ID);
        ClassObject classObject = classList.getClassWithId(classId);
        if (classObject == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        String keyword = argumentMap.get(KEY_KEYWORD);
        StudentList studentList = classObject.getStudentList();
        ArrayList<Student> studentsFound = studentList.findStudents(keyword);
        if (studentsFound.isEmpty()) {
            throw new TaaException(MESSAGE_NO_STUDENTS_FOUND);
        }

        String header = String.format(MESSAGE_FORMAT_STUDENT_FOUND_HEADER, classId, keyword);
        StringBuilder stringBuilder = new StringBuilder(header);
        for (int i = 0; i < studentsFound.size(); i += 1) {
            stringBuilder.append("\n");
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(studentsFound.get(i));
        }

        ui.printMessage(stringBuilder.toString());
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_FIND_STUDENT_USAGE,
            COMMAND_FIND_STUDENT,
            KEY_CLASS_ID,
            KEY_KEYWORD
        );
    }
}
