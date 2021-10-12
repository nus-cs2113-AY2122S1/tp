package taa.command;

import taa.storage.Storage;
import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

/**
 * Class that deals with the command for the setting of marks.
 */
public class SetMarksCommand extends Command {
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
    private static final String MESSAGE_FORMAT_INVALID_MARKS = "Invalid Marks. "
            + "Marks must be between %,.2f and %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_MARKS_ADDED = "Marks set for %s: %,.2f for %s";

    public SetMarksCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    /**
     * Checks for errors before calling the function that sets marks for a student's assessment.
     *
     * @param moduleList Module list to access the module the student is enrolled in.
     * @param ui The ui instance to handle interactions with the user.
     * @param storage The storage instance to handle saving.
     * @throws TaaException when set marks command is invalid.
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
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

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        if (!Util.isStringInteger(studentIndexInput)) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = module.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            ui.printMessage(MESSAGE_INVALID_STUDENT_INDEX);
            return;
        }

        AssessmentList assessmentList = module.getAssessmentList();
        String assessmentName = argumentMap.get(KEY_ASSESSMENT_NAME);
        Assessment assessment = assessmentList.getAssessment(assessmentName);
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }

        String marksInput = argumentMap.get(KEY_MARKS);
        if (!Util.isStringDouble(marksInput)) {
            double[] marksRange = Student.getMarksRange();
            throw new TaaException(String.format(MESSAGE_FORMAT_INVALID_MARKS, marksRange[0], marksRange[1]));
        }

        double marks = Double.parseDouble(marksInput);
        if (!Student.isMarksWithinRange(marks)) {
            double[] marksRange = Student.getMarksRange();
            throw new TaaException(String.format(MESSAGE_FORMAT_INVALID_MARKS, marksRange[0], marksRange[1]));
        }

        setMarks(ui, student, assessment, marks);

        storage.save(moduleList);
    }

    /**
     * Sets the marks for a student's assessment.
     *
     * @param ui The ui instance to handle interactions with the user.
     * @param student The student instance to set the mark for.
     * @param assessment The assessment instance.
     * @param marks The marks to set for the assessment.
     */
    private void setMarks(Ui ui, Student student, Assessment assessment, double marks) {
        String assessmentName = assessment.getName();

        student.setMarks(assessmentName, marks);
        String studentName = student.getName();
        ui.printMessage(String.format(MESSAGE_FORMAT_MARKS_ADDED, studentName, marks, assessmentName));
    }

    /**
     * Returns the usage message of the set marks command.
     * @return String which contains the usage message.
     */
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
}
