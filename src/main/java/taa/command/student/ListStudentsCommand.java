package taa.command.student;

//@@author hozhenhong99
import taa.command.Command;
import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.StudentList;

public class ListStudentsCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String[] LIST_STUDENT_ARGUMENT_KEYS = {KEY_MODULE_CODE};

    private static final String MESSAGE_NO_STUDENTS = "No students added yet!";

    private static final String MESSAGE_FORMAT_LIST_STUDENT_USAGE = "%s %s/<MODULE_CODE>";
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
     * Executes the list_student command and lists the students of a particular module.
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

        String message;
        StudentList studentList = module.getStudentList();
        if (studentList.getSize() == 0) {
            message = MESSAGE_NO_STUDENTS;
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, module, studentList);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_LIST_STUDENT_USAGE,
            COMMAND_LIST_STUDENTS,
            KEY_MODULE_CODE
        );
    }
}
