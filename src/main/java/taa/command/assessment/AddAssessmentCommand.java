package taa.command.assessment;

//@@author rachelkeh
import taa.teachingclass.ClassList;
import taa.teachingclass.TeachingClass;
import taa.command.Command;
import taa.storage.Storage;
import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.util.Util;

import java.util.ArrayList;

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
    private static final String MESSAGE_FORMAT_INVALID_NAME = "Invalid assessment name. "
            + "Assessment already exists.";
    private static final String MESSAGE_FORMAT_INVALID_WEIGHTAGE = "Invalid weightage. "
        + "Weightage must be between %,.2f and %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_INVALID_TOTAL_WEIGHTAGE = "Invalid new weightage. "
            + "Total new weightage exceeds 100%.";
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

        String weightageString = argumentMap.get(KEY_WEIGHTAGE);
        if (!Util.isStringDouble(weightageString)) {
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

        int maximumMarks = checkAndGetMaximumMarks();
        String name = argumentMap.get(KEY_ASSESSMENT_NAME);
        double weightage = checkAndGetWeightage(name, teachingClass);

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

    public int checkAndGetMaximumMarks() throws TaaException {
        String maximumMarksString = argumentMap.get(KEY_MAXIMUM_MARKS);
        assert Util.isStringInteger(maximumMarksString);
        int maximumMarks = Integer.parseInt(maximumMarksString);
        if (maximumMarks < Assessment.MINIMUM_MARKS) {
            throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_MAXIMUM_MARKS,
                    Assessment.MINIMUM_MARKS)
            );
        }
        return maximumMarks;
    }

    public double checkAndGetWeightage(String name, TeachingClass teachingClass) throws TaaException {
        String weightageString = argumentMap.get(KEY_WEIGHTAGE);
        assert Util.isStringDouble(weightageString);
        double weightage = Double.parseDouble(weightageString);
        if (!Assessment.isWeightageWithinRange(weightage)) {
            throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_WEIGHTAGE,
                    Assessment.WEIGHTAGE_RANGE[0],
                    Assessment.WEIGHTAGE_RANGE[1])
            );
        }
        ArrayList<Assessment> assessments = teachingClass.getAssessmentList().getAssessments();
        double totalWeightage = 0;
        for (Assessment a : assessments) {
            if (a.getName().equalsIgnoreCase(name)) {
                throw new TaaException(MESSAGE_FORMAT_INVALID_NAME);
            }

            totalWeightage += a.getWeightage();
        }
        double newTotalWeightage = totalWeightage + weightage;
        if (!Assessment.isWeightageWithinRange(newTotalWeightage)) {
            throw new TaaException(MESSAGE_FORMAT_INVALID_TOTAL_WEIGHTAGE);
        }
        return weightage;
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
