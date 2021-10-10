package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.assessment.AssessmentList;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

/**
 * Class that deals with the command for the setting of marks.
 */
public class SetMarksCommand extends Command{
    private static final String MESSAGE_MARKS_ADDED_FORMAT = "Marks set for %s: %,.4f for %s";
    private static final String[] ADD_ASSESSMENT_ARGUMENT_KEYS = {"c", "s", "a", "m"};

    public SetMarksCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    /**
     * Checks if the set marks command is valid. Throws an exception if not.
     * @param modules List of modules.
     * @throws CustomException When set marks command is invalid.
     */
    public void commandValidation(ModuleList modules) throws CustomException {
        Module module = modules.getModule(argumentMap.get("c"));
        AssessmentList list = module.getAssessments();
        int studentIndex = Integer.parseInt(argumentMap.get("s"));
        if (argument.isEmpty()) {
            // TODO Usage format message
            throw new CustomException("");
        }
        if (!checkArgumentMap()) {
            // TODO Invalid/missing arguments message
            throw new CustomException("");
        }
        if (argumentMap.get("c").contains(" ")) {
            // TODO Invalid module code message
            throw new CustomException("");
        }
        if ((studentIndex > module.getNumberOfStudents()) || (studentIndex < 0)) {
            // TODO non-existent student error message
            throw new CustomException("student does not exist!");
        }
        if (!list.isValidAssessment(argumentMap.get("a"))) {
            // TODO non-existent assessment error message
            throw new CustomException("assessment does not exist!");
        }
        if(Double.parseDouble(argumentMap.get("m")) < 0) {
            // TODO marks are negative
            throw new CustomException("Marks are negative!");
        }
    }

    /**
     * Sets marks for a student's assessment.
     *
     * @param modules Module list to access the module the student is enrolled in.
     * @param ui The ui instance to handle interactions with the user.
     * @throws CustomException when set marks command is invalid.
     */
    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        commandValidation(modules);
        String moduleCode = argumentMap.get("c");
        int studentIndex = Integer.parseInt(argumentMap.get("s"));
        String assessmentName = argumentMap.get("a");
        double marks = Double.parseDouble(argumentMap.get("m"));
        Module module = modules.getModule(moduleCode);
        Student student = module.getStudent(studentIndex);
        student.setMarks(assessmentName, marks);
        String studentName = student.getName();
        ui.printMessage(String.format(MESSAGE_MARKS_ADDED_FORMAT, studentName, marks, assessmentName));
    }
}
