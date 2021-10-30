package taa.command.student;

//@@author hozhenhong99
import taa.classmodel.ClassList;
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.classmodel.ClassObject;
import taa.student.StudentList;

public class ListStudentsCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String[] LIST_STUDENT_ARGUMENT_KEYS = {KEY_CLASS_ID};

    private static final String MESSAGE_NO_STUDENTS = "No students added yet!";

    private static final String MESSAGE_FORMAT_LIST_STUDENT_USAGE = "%s %s/<CLASS_ID>";
    private static final String MESSAGE_FORMAT_OUTPUT = "Students for %s:\n%s";

    public ListStudentsCommand(String argument) {
        super(argument, LIST_STUDENT_ARGUMENT_KEYS);
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
     * Executes the list_student command and lists the students of a particular class.
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

        String message;
        StudentList studentList = classObject.getStudentList();
        if (studentList.getSize() == 0) {
            message = MESSAGE_NO_STUDENTS;
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, classObject, studentList);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_LIST_STUDENT_USAGE,
            COMMAND_LIST_STUDENTS,
            KEY_CLASS_ID
        );
    }
}
