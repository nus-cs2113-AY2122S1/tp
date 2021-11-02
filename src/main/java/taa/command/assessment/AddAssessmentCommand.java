package taa.command.assessment;

import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.storage.Storage;
import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.util.Util;

public class AddAssessmentCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_ASSESSMENT_NAME = "n";
    private static final String KEY_MAXIMUM_MARKS = "m";
    private static final String KEY_WEIGHTAGE = "w";
    private static final String[] ADD_ASSESSMENT_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_ASSESSMENT_NAME,
        KEY_MAXIMUM_MARKS,
        KEY_WEIGHTAGE
    };

    private static final String MESSAGE_FAIL_TO_ADD = "Fail to add assessment.";

    private static final String MESSAGE_FORMAT_ADD_ASSESSMENT_USAGE = "%s %s/<CLASS_ID> %s/<ASSESSMENT_NAME> "
        + "%s/<MAXIMUM_MARKS> %s/<WEIGHTAGE>";
    private static final String MESSAGE_FORMAT_INVALID_WEIGHTAGE = "Invalid weightage. "
        + "Weightage must be between %,.2f and %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_INVALID_MAXIMUM_MARKS = "Invalid maximum marks. "
        + "Maximum marks must be larger than %d (inclusive)";
    private static final String MESSAGE_FORMAT_ASSESSMENT_ADDED = "Assessment added to %s:\n"
        + "  %s\nThere are %d assessments in the %s.";

    public AddAssessmentCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String maximumMarksString = argumentMap.get(KEY_MAXIMUM_MARKS);
        if (!Util.isStringInteger(maximumMarksString)) {
            throw new TaaException(String.format(
                MESSAGE_FORMAT_INVALID_MAXIMUM_MARKS,
                Assessment.MINIMUM_MARKS)
            );
        }

        int maximumMarks = Integer.parseInt(maximumMarksString);
        if (maximumMarks < Assessment.MINIMUM_MARKS) {
            throw new TaaException(String.format(
                MESSAGE_FORMAT_INVALID_MAXIMUM_MARKS,
                Assessment.MINIMUM_MARKS)
            );
        }

        String weightageString = argumentMap.get(KEY_WEIGHTAGE);
        if (!Util.isStringDouble(weightageString)) {
            throw new TaaException(String.format(
                MESSAGE_FORMAT_INVALID_WEIGHTAGE,
                Assessment.WEIGHTAGE_RANGE[0],
                Assessment.WEIGHTAGE_RANGE[1])
            );
        }

        double weightage = Double.parseDouble(weightageString);
        if (!Assessment.isWeightageWithinRange(weightage)) {
            throw new TaaException(String.format(
                MESSAGE_FORMAT_INVALID_WEIGHTAGE,
                Assessment.WEIGHTAGE_RANGE[0],
                Assessment.WEIGHTAGE_RANGE[1])
            );
        }
    }

    /**
     * Executes the add_assessment command and adds an assessment to a particular class.
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

        String maximumMarksString = argumentMap.get(KEY_MAXIMUM_MARKS);
        assert Util.isStringInteger(maximumMarksString);
        int maximumMarks = Integer.parseInt(maximumMarksString);
        assert maximumMarks >= Assessment.MINIMUM_MARKS;

        String weightageString = argumentMap.get(KEY_WEIGHTAGE);
        assert Util.isStringDouble(weightageString);
        double weightage = Double.parseDouble(weightageString);
        assert Assessment.isWeightageWithinRange(weightage);

        String name = argumentMap.get(KEY_ASSESSMENT_NAME);
        Assessment assessment = new Assessment(name, maximumMarks, weightage);

        AssessmentList assessmentList = teachingClass.getAssessmentList();
        assert assessmentList != null : "assessment list should exist.";
        boolean isSuccessful = assessmentList.addAssessment(assessment);
        if (!isSuccessful) {
            throw new TaaException(MESSAGE_FAIL_TO_ADD);
        }

        assert storage != null : "storage should exist.";
        storage.save(classList);

        assert ui != null : "ui should exist.";
        ui.printMessage(String.format(MESSAGE_FORMAT_ASSESSMENT_ADDED, classId,
            assessment, assessmentList.getSize(), teachingClass));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_ADD_ASSESSMENT_USAGE,
            COMMAND_ADD_ASSESSMENT,
            KEY_CLASS_ID,
            KEY_ASSESSMENT_NAME,
            KEY_MAXIMUM_MARKS,
            KEY_WEIGHTAGE
        );
    }
}
