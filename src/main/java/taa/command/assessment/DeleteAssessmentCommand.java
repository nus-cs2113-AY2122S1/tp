package taa.command.assessment;

//@@author rachelkeh
import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.exception.TaaException;
import taa.storage.Storage;
import taa.student.Student;

import java.util.ArrayList;

public class DeleteAssessmentCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String[] DELETE_ASSESSMENT_ARGUMENT_KEYS = {KEY_CLASS_ID, KEY_ASSESSMENT_NAME};

    private static final String MESSAGE_FORMAT_DELETE_ASSESSMENT_USAGE = "%s %s/<CLASS_ID> %s/<ASSESSMENT_NAME>";
    private static final String MESSAGE_FORMAT_ASSESSMENT_DELETED = "Assessment removed from %s:\n  %s";

    public DeleteAssessmentCommand(String argument) {
        super(argument, DELETE_ASSESSMENT_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }
    }

    /**
     * Executes the delete_assessment command and deletes an assessment with the specified assessment name.
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

        String name = argumentMap.get(KEY_ASSESSMENT_NAME);
        AssessmentList assessmentList = teachingClass.getAssessmentList();
        assert assessmentList != null : "assessment list should exist.";
        Assessment assessment = assessmentList.deleteAssessment(name);
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }

        ArrayList<Student> students = teachingClass.getStudentList().getStudents();
        for (Student s : students) {
            if (s.marksExist(name)) {
                double marks = s.getMarks(name);
                s.deleteAssessmentAndMarks(name, marks);
            }
        }

        assert storage != null : "storage should exist.";
        storage.save(classList);

        assert ui != null : "ui should exist.";
        ui.printMessage(String.format(MESSAGE_FORMAT_ASSESSMENT_DELETED, classId, assessment));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_DELETE_ASSESSMENT_USAGE,
            COMMAND_DELETE_ASSESSMENT,
            KEY_CLASS_ID,
            KEY_ASSESSMENT_NAME
        );
    }
}
