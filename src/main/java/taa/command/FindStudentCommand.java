package taa.command;

import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;
import taa.student.StudentList;

import java.util.ArrayList;

public class FindStudentCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_KEYWORD = "k";
    private static final String[] FIND_STUDENT_ARGUMENT_KEYS = {KEY_MODULE_CODE, KEY_KEYWORD};

    private static final String MESSAGE_NO_STUDENTS_FOUND = "There are no students matching your keyword.";

    private static final String MESSAGE_FORMAT_FIND_STUDENT_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE> %s/<KEYWORD>";
    private static final String MESSAGE_FORMAT_STUDENT_FOUND_HEADER = "Here are the students in %s with \"%s\":";

    public FindStudentCommand(String argument) {
        super(argument, FIND_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Finds the students taking a particular module containing a particular keyword.
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

        if (!checkArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        String keyword = argumentMap.get(KEY_KEYWORD);
        StudentList studentList = module.getStudentList();
        ArrayList<Student> studentsFound = studentList.findStudents(keyword);
        if (studentsFound.isEmpty()) {
            throw new TaaException(MESSAGE_NO_STUDENTS_FOUND);
        }

        String header = String.format(MESSAGE_FORMAT_STUDENT_FOUND_HEADER, moduleCode, keyword);
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
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_FIND_STUDENT_USAGE,
                COMMAND_FIND_STUDENT,
                KEY_MODULE_CODE,
                KEY_KEYWORD
        );
    }
}
