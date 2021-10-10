package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.assessment.AssessmentList;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

/**
 * Class that deals with the command for the listing of marks.
 */
public class ListMarksCommand extends Command{
    private static final String MESSAGE_LIST_MARKS_HEADER = "Here is the list of students and " +
            "their marks for %s:";
    private static final String MARKS_LIST_EMPTY = "There are no marks inputted!";
    private static final String[] ADD_ASSESSMENT_ARGUMENT_KEYS = {"c", "a"};

    public ListMarksCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    /**
     * Checks if the set marks command is valid. Throws an exception if not.
     * @param modules List of modules.
     * @throws CustomException When list marks command is invalid.
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
     * Returns a string with the student name and the marks associated with the student.
     * @param studentName Name of the student.
     * @param marks Marks of the student.
     * @return String with the name and marks of the student separated with a vertical line.
     */
    public String marksToString(String studentName, double marks) {
        return studentName + " | " + marks;
    }

    /**
     * Lists the student and the marks they attained for an assessment.
     *
     * @param modules List of modules.
     * @param ui The ui instance to handle interactions with the user.
     * @throws CustomException When list marks command is invalid.
     */
    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        commandValidation(modules);
        Module module = modules.getModule(argumentMap.get("c"));
        String assessmentName = argumentMap.get("a");
        StringBuilder marksList = new StringBuilder(String.format(MESSAGE_LIST_MARKS_HEADER, assessmentName));
        for (Student student : module.getStudents()) {
            marksList.append("\n");
            marksList.append(marksToString(student.getName(), student.getMarks(assessmentName)));
        }
        ui.printMessage(marksList.toString());
    }
}
