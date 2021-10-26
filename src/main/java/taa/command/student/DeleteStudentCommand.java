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
import taa.util.Util;

public class DeleteStudentCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String[] DELETE_STUDENT_ARGUMENT_KEYS = {KEY_MODULE_CODE, KEY_STUDENT_INDEX};

    private static final String MESSAGE_FORMAT_DELETE_STUDENT_USAGE = "%s %s/<MODULE_CODE> %s/<STUDENT_INDEX>";
    private static final String MESSAGE_FORMAT_STUDENT_DELETED = "Student removed from %s:\n  %s";

    public DeleteStudentCommand(String argument) {
        super(argument, DELETE_STUDENT_ARGUMENT_KEYS);
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
     * Executes the delete_student command and deletes a student from the module.
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

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        assert Util.isStringInteger(studentIndexInput);
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = module.getStudentList();
        assert studentIndex >= 0 && studentIndex < module.getStudentList().getSize();
        Student student = studentList.deleteStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        storage.save(moduleList);
        ui.printMessage(String.format(MESSAGE_FORMAT_STUDENT_DELETED, moduleCode, student));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_DELETE_STUDENT_USAGE,
            COMMAND_DELETE_STUDENT,
            KEY_MODULE_CODE,
            KEY_STUDENT_INDEX
        );
    }
}
