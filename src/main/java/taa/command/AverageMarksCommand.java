package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.assessment.AssessmentList;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

/**
 * Class that deals with the outputting of the average marks.
 */
public class AverageMarksCommand extends Command{
    private static final String MESSAGE_AVERAGE_MARKS_FORMAT = "Average marks for %s is %,.4f";
    private static final String[] ADD_ASSESSMENT_ARGUMENT_KEYS = {"c", "a"};

    public AverageMarksCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    /**
     * Checks if the set marks command is valid. Throws an exception if not.
     * @param modules List of modules.
     * @throws CustomException When average marks command is invalid.
     */
    public void commandValidation(ModuleList modules) throws CustomException {
        Module module = modules.getModule(argumentMap.get("c"));
        AssessmentList list = module.getAssessments();
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
        if (!list.isValidAssessment(argumentMap.get("a"))) {
            // TODO non-existent assessment error message
            throw new CustomException("assessment does not exist!");
        }
    }

    /**
     * Outputs the average marks of an assessment in a module.
     *
     * @param modules List of modules.
     * @param ui The ui instance to handle interactions with the user.
     * @throws CustomException When average marks command is invalid.
     */
    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        commandValidation(modules);
        Module module = modules.getModule(argumentMap.get("c"));
        String assessmentName = argumentMap.get("a");
        double classSize = module.getNumberOfStudents();
        double totalMarks = 0;
        for (Student student : module.getStudents()) {
            totalMarks += student.getMarks(assessmentName);
        }
        double averageMarks = totalMarks / classSize;
        ui.printMessage(String.format(MESSAGE_AVERAGE_MARKS_FORMAT, assessmentName, averageMarks));
    }
}
