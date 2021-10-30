package taa.command.mark;

import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.classmodel.ClassList;
import taa.classmodel.ClassObject;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.student.Student;
import taa.student.StudentList;
import taa.util.Util;

public class DeleteMarkCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_STUDENT_INDEX = "s";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String[] DELETE_MARK_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_STUDENT_INDEX,
        KEY_ASSESSMENT_NAME
    };
    private static final String MESSAGE_FORMAT_DELETE_MARKS_USAGE = "%s %s/<CLASS_ID> %s/<STUDENT_INDEX> "
        + "%s/<ASSESSMENT_NAME>";
    private static final String MESSAGE_FORMAT_MARKS_DELETED = "Marks for student '%s' have been deleted for"
        + " assessment '%s'";

    public DeleteMarkCommand(String argument) {
        super(argument, DELETE_MARK_ARGUMENT_KEYS);
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
    }

    /**
     * Executes the delete_marks command and deletes the marks of a student's assessment.
     *
     * @param classList The list of classes.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ClassList classList, Ui ui, Storage storage) throws TaaException {
        String classId = argumentMap.get(KEY_CLASS_ID);
        ClassObject classObject = classList.getClassWithId(classId);
        if (classObject == null) {
            throw new TaaException(MESSAGE_CLASS_NOT_FOUND);
        }

        String studentIndexInput = argumentMap.get(KEY_STUDENT_INDEX);
        assert Util.isStringInteger(studentIndexInput);
        int studentIndex = Integer.parseInt(studentIndexInput) - 1;

        StudentList studentList = classObject.getStudentList();
        Student student = studentList.getStudentAt(studentIndex);
        if (student == null) {
            throw new TaaException(MESSAGE_INVALID_STUDENT_INDEX);
        }

        AssessmentList assessmentList = classObject.getAssessmentList();
        String assessmentName = argumentMap.get(KEY_ASSESSMENT_NAME);
        Assessment assessment = assessmentList.getAssessment(assessmentName);
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }

        if (student.getResults().get(assessmentName) == null) {
            throw new TaaException(MESSAGE_NO_MARKS);
        }

        deleteMark(ui, student, assessment);
        storage.save(classList);
    }

    /**
     * Deletes marks from the results hashmap for the student.
     *
     * @param ui The ui instance to handle interactions with the user.
     * @param student The student whose marks are to be deleted.
     * @param assessment The assessment to delete marks for.
     */
    private void deleteMark(Ui ui, Student student, Assessment assessment) {
        student.deleteMark(assessment.getName());
        ui.printMessage(String.format(MESSAGE_FORMAT_MARKS_DELETED, student, assessment.getName()));
    }

    /**
     * Returns the usage message of the delete marks command.
     *
     * @return String which contains the usage message.
     */
    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_DELETE_MARKS_USAGE,
            COMMAND_DELETE_MARK,
            KEY_CLASS_ID,
            KEY_STUDENT_INDEX,
            KEY_ASSESSMENT_NAME
        );
    }
}
