package taa.command.mark;

//@@author jon-the-melon
import taa.Taa;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.storage.Storage;
import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.teachingclass.ClassList;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

/**
 * Class that deals with the command for the setting of marks.
 */
public class SetMarkCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String KEY_MARKS = "m";
    private static final String[] SET_MARKS_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_STUDENT_INDEX,
        KEY_ASSESSMENT_NAME,
        KEY_MARKS
    };

    private static final String MESSAGE_FORMAT_SET_MARKS_USAGE = "%s %s/<CLASS_ID> %s/<STUDENT_INDEX> "
        + "%s/<ASSESSMENT_NAME> %s/<MARKS>";
    private static final String MESSAGE_FORMAT_INVALID_MARKS = "Invalid Marks. "
        + "Marks must be between %d and %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_MARKS_ADDED = "Marks set for %s: %,.2f for %s";

    public SetMarkCommand(String argument) {
        super(argument, SET_MARKS_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        if (!Util.isStringInteger(studentIndexInput)) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        String marksInput = argumentMap.get(KEY_MARKS);
        if (!Util.isStringDouble(marksInput, Taa.DECIMAL_PLACES)) {
            throw new TaaException(MESSAGE_INVALID_MARKS);
        }
    }

    /**
     * Executes the set_marks command and sets the marks of a student's assessment.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {

        String classId = argumentMap.get(KEY_CLASS_ID);
        TeachingClass teachingClass = classList.getClassWithId(classId);
        if (teachingClass == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        assert Util.isStringInteger(studentIndexInput);
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;
        StudentList studentList = teachingClass.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        AssessmentList assessmentList = teachingClass.getAssessmentList();
        String assessmentName = argumentMap.get(KEY_ASSESSMENT_NAME);
        Assessment assessment = assessmentList.getAssessment(assessmentName);
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }
        if (student.marksExist(assessmentName)) {
            throw new TaaException(MESSAGE_ALREADY_MARKED);
        }

        String marksInput = argumentMap.get(KEY_MARKS);
        assert Util.isStringDouble(marksInput, Taa.DECIMAL_PLACES);
        double marks = Double.parseDouble(marksInput);
        if (!(assessment.isMarksValid(marks))) {
            double maxMarks = assessment.getMaximumMarks();
            throw new TaaException(String.format(MESSAGE_FORMAT_INVALID_MARKS, 0, maxMarks));
        }

        setMarks(ui, student, assessment, marks);
        storage.save(classList);
    }

    /**
     * Sets the marks for a student's assessment.
     *
     * @param ui             The ui instance to handle interactions with the user.
     * @param student        The student instance to set the mark for.
     * @param assessment     The assessment to be marked.
     * @param marks          The marks to set for the assessment.
     */
    private void setMarks(Ui ui, Student student, Assessment assessment, double marks) {
        assert marks >= 0;
        student.setMarks(assessment.getName(), marks);
        ui.printMessage(String.format(MESSAGE_FORMAT_MARKS_ADDED, student, marks, assessment.getName()));
    }

    /**
     * Returns the usage message of the set marks command.
     *
     * @return String which contains the usage message.
     */
    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_SET_MARKS_USAGE,
            COMMAND_SET_MARKS,
            KEY_CLASS_ID,
            KEY_STUDENT_INDEX,
            KEY_ASSESSMENT_NAME,
            KEY_MARKS
        );
    }
}
