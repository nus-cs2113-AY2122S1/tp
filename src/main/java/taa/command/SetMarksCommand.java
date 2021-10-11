package taa.command;


import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;

/**
 * Class that deals with the command for the setting of marks.
 */
public class SetMarksCommand extends Command{
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String KEY_MARKS = "m";
    private static final String[] ADD_ASSESSMENT_ARGUMENT_KEYS = {
            KEY_MODULE_CODE,
            KEY_STUDENT_INDEX,
            KEY_ASSESSMENT_NAME,
            KEY_MARKS
    };
    private static final String MESSAGE_FORMAT_SET_MARKS_USAGE = "Usage: %s "
            + "%s/<MODULE_CODE> %s/<STUDENT_INDEX> %s/<ASSESSMENT_NAME> %s/<MARKS>";
    private static final String MESSAGE_MARKS_ADDED_FORMAT = "Marks set for %s: %,.4f for %s";

    public SetMarksCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    /**
     * Sets marks for a student's assessment.
     *
     * @param moduleList Module list to access the module the student is enrolled in.
     * @param ui The ui instance to handle interactions with the user.
     * @throws TaaException when set marks command is invalid.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui) throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }
        if (!checkArgumentMap()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModule(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        int studentIndex = Integer.parseInt(argumentMap.get(KEY_STUDENT_INDEX));
        if ((studentIndex > module.getNumberOfStudents()) || (studentIndex <= 0)) {
            // TODO non-existent student error message
            throw new TaaException("No such student!");
        }

        AssessmentList list = module.getAssessments();
        Assessment assessment = list.getAssessment(argumentMap.get(KEY_ASSESSMENT_NAME));
        if (assessment == null) {
            // TODO non-existent assessment error message
            throw new TaaException("Assessment does not exist!");
        }
        if(Double.parseDouble(argumentMap.get(KEY_MARKS)) < 0) {
            // TODO marks are negative
            throw new TaaException("Marks cannot be negative!");
        }
        setMarks(ui, module, studentIndex);
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_SET_MARKS_USAGE,
                COMMAND_SET_MARKS,
                KEY_MODULE_CODE,
                KEY_STUDENT_INDEX,
                KEY_ASSESSMENT_NAME,
                KEY_MARKS
        );
    }

    private void setMarks(Ui ui, Module module, int studentIndex) {
        String assessmentName = argumentMap.get(KEY_ASSESSMENT_NAME);
        double marks = Double.parseDouble(argumentMap.get(KEY_MARKS));
        Student student = module.getStudent(studentIndex - 1);
        student.setMarks(assessmentName, marks);
        String studentName = student.getName();
        ui.printMessage(String.format(MESSAGE_MARKS_ADDED_FORMAT, studentName, marks, assessmentName));
    }
}
