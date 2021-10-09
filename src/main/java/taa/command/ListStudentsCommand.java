package taa.command;

import taa.exception.TaaException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

import java.util.ArrayList;

public class ListStudentsCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String[] LIST_STUDENT_ARGUMENT_KEYS = {KEY_MODULE_CODE};

    private static final String MESSAGE_NO_STUDENTS = "No students added yet!";

    private static final String MESSAGE_FORMAT_LIST_STUDENT_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE>";
    private static final String MESSAGE_FORMAT_LIST_STUDENT_HEADER = "Here are the students in %s:";

    public ListStudentsCommand(String argument) {
        super(argument, LIST_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Lists the students taking a particular module.
     *
     * @param modules The list of modules
     * @param ui The ui instance to handle interactions with the user
     * @throws TaaException If the user inputs an invalid command
     */
    @Override
    public void execute(ModuleList modules, Ui ui) throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!checkArgumentMap()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = modules.getModule(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        ArrayList<Student> students = module.getStudents();
        if (students.isEmpty()) {
            ui.printMessage(MESSAGE_NO_STUDENTS);
            return;
        }

        String header = String.format(MESSAGE_FORMAT_LIST_STUDENT_HEADER, moduleCode);
        StringBuilder stringBuilder = new StringBuilder(header);
        for (int i = 0; i < students.size(); i += 1) {
            stringBuilder.append("\n");
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(students.get(i));
        }

        ui.printMessage(stringBuilder.toString());
    }

    @Override
    protected String getUsageMessage() {
        return String.format(MESSAGE_FORMAT_LIST_STUDENT_USAGE,
                COMMAND_LIST_STUDENTS,
                KEY_MODULE_CODE
        );
    }
}
