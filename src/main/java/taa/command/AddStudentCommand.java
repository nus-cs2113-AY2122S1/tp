package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

public class AddStudentCommand extends Command {

    private static final String MESSAGE_STUDENT_ADDED_FORMAT = "Student %s, %s has been added to %s";
    private static final String[] ADD_STUDENT_ARGUMENT_KEYS = {"c","s","i"};

    public AddStudentCommand(String argument) {
        super(argument, ADD_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Adds a students to a particular module.
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

        String studentName = argumentMap.get("s");
        String studentID = argumentMap.get("i");
        Student student = new Student(studentName, studentID);
        Module module = modules.getModule(moduleCode);
        if (module == null) {
            // TODO module not found message
            throw new CustomException("Module not found");
        }
        module.addStudent(student);

        ui.printMessage(String.format(MESSAGE_STUDENT_ADDED_FORMAT, studentName, studentID, moduleCode));
    }
}
