package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

public class ListStudentsCommand extends Command {

    private static final String[] LIST_STUDENT_ARGUMENT_KEYS = {"c"};
    private static final String MESSAGE_NO_STUDENTS = "No students added yet!";
    private static final String MESSAGE_LIST_STUDENT_FORMAT = "Here are the students in %s";

    public ListStudentsCommand(String argument) {
        super(argument, LIST_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Lists the students taking a particular module.
     *
     * @param modules The list of modules
     * @param ui The ui instance to handle interactions with the user
     * @throws CustomException If the user inputs an invalid command
     */
    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        if (argument.isEmpty()) {
            // TODO Usage format message
            throw new CustomException("");
        }

        if (!checkArgumentMap()) {
            // TODO Invalid/missing arguments message
            throw new CustomException("");
        }

        String moduleCode = argumentMap.get("c");
        if (moduleCode.contains(" ")) {
            // TODO Invalid module code message
            throw new CustomException("");
        }

        Module module = modules.getModule(moduleCode);
        if (module == null) {
            // TODO module not found message
            throw new CustomException("Module not found");
        }

        if (module.getStudents().size() == 0) {
            ui.printMessage(MESSAGE_NO_STUDENTS);
        } else {
            ui.printMessage(String.format(MESSAGE_LIST_STUDENT_FORMAT, moduleCode));
            for (int i = 0; i < module.getStudents().size(); i += 1) {
                System.out.println(i + 1 + ". " + module.getStudents().get(i));
            }
        }
    }
}