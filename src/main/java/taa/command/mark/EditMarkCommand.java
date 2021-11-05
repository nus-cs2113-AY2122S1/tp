package taa.command.mark;

//@@author jon-the-melon
import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class EditMarkCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String KEY_NEW_MARKS = "m";
    private static final String[] EDIT_MARKS_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_STUDENT_INDEX,
        KEY_ASSESSMENT_NAME,
        KEY_NEW_MARKS
    };

    private static final String MESSAGE_FORMAT_EDIT_MARKS_USAGE = "%s %s/<CLASS_ID> %s/<STUDENT_INDEX> "
        + "%s/<ASSESSMENT_NAME> %s/<NEW_MARKS>";
    private static final String MESSAGE_FORMAT_INVALID_MARKS = "Invalid Marks. "
        + "Marks must be between %d and %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_MARKS_EDITED = "Marks edited for %s: from %,.2f to %,.2f for %s";

    public EditMarkCommand(String argument) {
        super(argument, EDIT_MARKS_ARGUMENT_KEYS);
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

        String marksInput = argumentMap.get(KEY_NEW_MARKS);
        if (!Util.isStringDouble(marksInput)) {
            throw new TaaException(MESSAGE_INVALID_MARKS);
        }
    }

    /**
     * Executes the edit_marks command and edits the marks of a student's assessment.
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

        String marksInput = argumentMap.get(KEY_NEW_MARKS);
        assert Util.isStringDouble(marksInput);
        double marks = Double.parseDouble(marksInput);
        double maximumMarks = assessment.getMaximumMarks();
        if (!(assessment.isMarksValid(marks))) {
            throw new TaaException(String.format(MESSAGE_FORMAT_INVALID_MARKS, 0, maximumMarks));
        }

        if (student.getResults().get(assessmentName) == null) {
            throw new TaaException(MESSAGE_NO_MARKS);
        }

        editMark(ui, student, assessment, marks);
        storage.save(classList);
    }

    /**
     * Edits marks in the results hashmap of the student.
     *
     * @param ui The ui instance to handle interactions with the user.
     * @param student The student whose marks are to be edited.
     * @param assessment The assessment to edit marks for.
     */
    private void editMark(Ui ui, Student student, Assessment assessment, double newMarks) {
        double previousMark = student.getMarks(assessment.getName());
        student.deleteMark(assessment.getName());
        student.setMarks(assessment.getName(), newMarks);
        ui.printMessage(String.format(MESSAGE_FORMAT_MARKS_EDITED, student, previousMark, newMarks,
                assessment.getName()));
    }

    /**
     * Returns the usage message of the edit marks command.
     *
     * @return String which contains the usage message.
     */
    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_EDIT_MARKS_USAGE,
            COMMAND_EDIT_MARK,
            KEY_CLASS_ID,
            KEY_STUDENT_INDEX,
            KEY_ASSESSMENT_NAME,
            KEY_NEW_MARKS
        );
    }
}
