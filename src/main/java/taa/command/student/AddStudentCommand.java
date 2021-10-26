package taa.command.student;

//@@author hozhenhong99
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;
import taa.student.StudentList;

public class AddStudentCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_ID = "i";
    private static final String KEY_STUDENT_NAME = "n";
    private static final String[] ADD_STUDENT_ARGUMENT_KEYS = {KEY_MODULE_CODE, KEY_STUDENT_ID, KEY_STUDENT_NAME};

    private static final String MESSAGE_FORMAT_ADD_STUDENT_USAGE = "%s %s/<MODULE_CODE> %s/<STUDENT_ID> "
        + "%s/<STUDENT_NAME>";
    private static final String MESSAGE_STUDENT_ADDED_FORMAT = "Student has been added to %s:\n  %s";

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
     * Executes the add_student command and adds a student to a particular module.
     *
     * @param moduleList The list of modules.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModuleWithCode(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        String studentID = argumentMap.get(KEY_STUDENT_ID);
        String studentName = argumentMap.get(KEY_STUDENT_NAME);
        Student student = new Student(studentID, studentName);

        StudentList studentList = module.getStudentList();
        studentList.addStudent(student);

        storage.save(moduleList);
        ui.printMessage(String.format(MESSAGE_STUDENT_ADDED_FORMAT, moduleCode, student));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_ADD_STUDENT_USAGE,
            COMMAND_ADD_STUDENT,
            KEY_MODULE_CODE,
            KEY_STUDENT_ID,
            KEY_STUDENT_NAME
        );
    }
}
