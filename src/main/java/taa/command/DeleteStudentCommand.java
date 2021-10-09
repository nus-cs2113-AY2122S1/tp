package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

public class DeleteStudentCommand extends Command {

    private static final String[] DELETE_STUDENT_ARGUMENT_KEYS = {"c", "n"};
    private static final String MESSAGE_STUDENT_DELETED_FORMAT = "%s, %s has been removed from %s";

    public DeleteStudentCommand(String argument) {
        super(argument, DELETE_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Deletes a student from a module.
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

        // TODO exceptions for number format and array out of bounds
        int studentIndex = Integer.parseInt(argumentMap.get("n")) - 1;
        Student student = modules.getModule(moduleCode).getStudents().get(studentIndex);
        String studentName = student.getName();
        String studentID = student.getStudentID();
        modules.getModule(moduleCode).getStudents().remove(studentIndex);
        ui.printMessage(String.format(MESSAGE_STUDENT_DELETED_FORMAT, studentName, studentID, moduleCode));
    }
}
