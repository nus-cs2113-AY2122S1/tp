package taa.command;

import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class EditStudentCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_NEW_ID = "i";
    private static final String KEY_NEW_NAME = "n";
    private static final String[] EDIT_STUDENT_ARGUMENT_KEYS = {
            KEY_MODULE_CODE,
            KEY_STUDENT_INDEX,
            KEY_NEW_ID,
            KEY_NEW_NAME
    };

    private static final String MESSAGE_FORMAT_FIND_STUDENT_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE> %s/<STUDENT_INDEX> %s/<NEW_ID> %s/<NEW_NAME>";
    private static final String MESSAGE_FORMAT_STUDENT_EDITED = "Student updated:\n  %s";

    public EditStudentCommand(String argument) {
        super(argument, EDIT_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Executes the edit_student command and edits the particulars of a student.
     *
     * @param moduleList The list of modules.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!checkArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        if (!Util.isStringInteger(studentIndexInput)) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = module.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        String newId = argumentMap.get(KEY_NEW_ID);
        String newName = argumentMap.get(KEY_NEW_NAME);
        student.setId(newId);
        student.setName(newName);

        storage.save(moduleList);

        ui.printMessage(String.format(MESSAGE_FORMAT_STUDENT_EDITED, student));
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_FIND_STUDENT_USAGE,
                COMMAND_EDIT_STUDENT,
                KEY_MODULE_CODE,
                KEY_STUDENT_INDEX,
                KEY_NEW_ID,
                KEY_NEW_NAME
        );
    }
}
