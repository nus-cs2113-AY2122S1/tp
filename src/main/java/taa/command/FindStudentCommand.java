package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

import java.util.Locale;

public class FindStudentCommand extends Command {

    private static final String[] FIND_STUDENT_ARGUMENT_KEYS = {"c","k"};
    private static final String MESSAGE_FOUND_STUDENT_FORMAT = "Here are the students in %s with '%s'";
    public static final String MESSAGE_NO_STUDENTS_FOUND = "No Students matching your keyword found";

    public FindStudentCommand(String argument) {
        super(argument, FIND_STUDENT_ARGUMENT_KEYS);
    }

    /**
     * Finds the students taking a particular module containing a particular keyword.
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

        boolean isFound = false;
        Student student;
        String keyword = argumentMap.get("k").toLowerCase();
        for (int i = 0; i < module.getStudents().size(); i += 1) {
            student = module.getStudents().get(i);
            if (student.getName().toLowerCase().contains(keyword)
                    || student.getStudentID().toLowerCase().contains(keyword)) {
                if (!isFound) {
                    ui.printMessage(String.format(MESSAGE_FOUND_STUDENT_FORMAT, moduleCode, keyword));
                    isFound = true;
                }
                System.out.println(i + 1 + ". " + student);
            }
        }
        if (!isFound) {
            ui.printMessage(MESSAGE_NO_STUDENTS_FOUND);
        }
    }
}