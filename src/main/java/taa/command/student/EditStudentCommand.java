package taa.command.student;

//@@author hozhenhong99
import taa.teachingclass.TeachingClass;
import taa.teachingclass.ClassList;
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class EditStudentCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_NEW_ID = "i";
    private static final String KEY_NEW_NAME = "n";
    private static final String[] EDIT_STUDENT_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_STUDENT_INDEX,
        KEY_NEW_ID,
        KEY_NEW_NAME
    };

    private static final String MESSAGE_FORMAT_FIND_STUDENT_USAGE = "%s %s/<CLASS_ID> %s/<STUDENT_INDEX> "
        + "%s/<NEW_ID> %s/<NEW_NAME>";
    private static final String MESSAGE_FORMAT_STUDENT_EDITED = "Student updated:\n  %s";
    public static final String MESSAGE_ID_ALREADY_EXISTS = "A student with that ID already exists";

    public EditStudentCommand(String argument) {
        super(argument, EDIT_STUDENT_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        if (!Util.isStringInteger(studentIndexInput)) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
    }

    /**
     * Executes the edit_student command and edits the particulars of a student.
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

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        assert Util.isStringInteger(studentIndexInput);
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = teachingClass.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
        assert studentIndex >= 0 && studentIndex < teachingClass.getStudentList().getSize();

        String newId = argumentMap.get(KEY_NEW_ID);
        String newName = argumentMap.get(KEY_NEW_NAME);
        Student existingStudent = studentList.getStudentWithId(newId);
        if (existingStudent != null && existingStudent != student) {
            throw new TaaException(MESSAGE_ID_ALREADY_EXISTS);
        }

        student.setId(newId);
        student.setName(newName);

        storage.save(classList);
        ui.printMessage(String.format(MESSAGE_FORMAT_STUDENT_EDITED, student));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_FIND_STUDENT_USAGE,
            COMMAND_EDIT_STUDENT,
            KEY_CLASS_ID,
            KEY_STUDENT_INDEX,
            KEY_NEW_ID,
            KEY_NEW_NAME
        );
    }
}
