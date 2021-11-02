package taa.command.student;

//@@author hozhenhong99
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.student.Student;
import taa.student.StudentList;

public class AddStudentCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_ID = "i";
    private static final String KEY_STUDENT_NAME = "n";
    private static final String[] ADD_STUDENT_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_STUDENT_ID, KEY_STUDENT_NAME};

    private static final String MESSAGE_FORMAT_ADD_STUDENT_USAGE = "%s %s/<CLASS_ID> %s/<STUDENT_ID> "
        + "%s/<STUDENT_NAME>";
    private static final String MESSAGE_STUDENT_ADDED_FORMAT = "Student has been added to %s:\n  %s";
    public static final String MESSAGE_ID_ALREADY_EXISTS = "A student with that ID already exists";

    public AddStudentCommand(String argument) {
        super(argument, ADD_STUDENT_ARGUMENT_KEYS);
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
     * Executes the add_student command and adds a student to a particular class.
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

        String studentID = argumentMap.get(KEY_STUDENT_ID);
        String studentName = argumentMap.get(KEY_STUDENT_NAME);
        Student student = new Student(studentID, studentName);

        StudentList studentList = teachingClass.getStudentList();
        for (Student students : studentList.getStudents()) {
            if (students.getId().equals(studentID)) {
                throw new TaaException(MESSAGE_ID_ALREADY_EXISTS);
            }
        }
        studentList.addStudent(student);

        storage.save(classList);
        String message = String.format(MESSAGE_STUDENT_ADDED_FORMAT, classId, student);
        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_ADD_STUDENT_USAGE,
            COMMAND_ADD_STUDENT,
            KEY_CLASS_ID,
            KEY_STUDENT_ID,
            KEY_STUDENT_NAME
        );
    }
}
