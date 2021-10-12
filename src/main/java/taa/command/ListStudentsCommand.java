package taa.command;

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

    private static final String MESSAGE_FORMAT_LIST_STUDENT_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE>";

    public ListStudentsCommand(String argument) {
        super(argument, LIST_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Lists the students taking a particular module.
     *
     * @param moduleList The list of modules
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!checkArgumentMap()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        StudentList studentList = module.getStudentList();
        if (studentList.getSize() == 0) {
            ui.printMessage(MESSAGE_NO_STUDENTS);
            return;
        }

        ui.printMessage(studentList.toString());
    }

    @Override
    protected String getUsageMessage() {
        return String.format(MESSAGE_FORMAT_LIST_STUDENT_USAGE,
                COMMAND_LIST_STUDENTS,
                KEY_MODULE_CODE
        );
    }
}
