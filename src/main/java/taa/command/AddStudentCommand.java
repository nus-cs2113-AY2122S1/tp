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
        for (int i = 0; i < modules.getSize(); i += 1) {
            //TODO find module in modules and add student to module
        }

        ui.printMessage(String.format(MESSAGE_STUDENT_ADDED_FORMAT, studentName, studentID, moduleCode));
    }
}
