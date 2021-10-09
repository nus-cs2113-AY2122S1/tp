package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

public class EditStudentCommand extends Command {

    private static final String[] EDIT_STUDENT_ARGUMENT_KEYS = {"c", "n", "s", "i"};
    private static final String MESSAGE_STUDENT_EDITED_FORMAT = "Student details updated:\n %d. %s, %s";

    public EditStudentCommand(String argument) {
        super(argument, EDIT_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Edits the name and student ID of a given student.
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
        String studentName = argumentMap.get("s");
        String studentID = argumentMap.get("i");
        Student student = modules.getModule(moduleCode).getStudents().get(studentIndex);
        student.setName(studentName);
        student.setStudentID(studentID);

        ui.printMessage(String.format(MESSAGE_STUDENT_EDITED_FORMAT, (studentIndex + 1), studentName, studentID));
    }
}