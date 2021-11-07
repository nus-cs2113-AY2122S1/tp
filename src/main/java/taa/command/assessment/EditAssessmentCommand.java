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
import taa.util.Util;

import java.util.ArrayList;

public class EditAssessmentCommand extends Command {
    private static final String KEY_CLASS_ID = "c";
    private static final String KEY_ASSESSMENT_NAME = "a";
    private static final String KEY_NEW_ASSESSMENT_NAME = "n";
    private static final String KEY_NEW_MAXIMUM_MARKS = "m";
    private static final String KEY_NEW_WEIGHTAGE = "w";
    private static final String[] EDIT_ASSESSMENT_ARGUMENT_KEYS = {
        KEY_CLASS_ID,
        KEY_ASSESSMENT_NAME,
        KEY_NEW_ASSESSMENT_NAME,
        KEY_NEW_MAXIMUM_MARKS,
        KEY_NEW_WEIGHTAGE
    };

    private static final String MESSAGE_FORMAT_EDIT_ASSESSMENT_USAGE = "%s %s/<CLASS_ID> "
        + "%s/<ASSESSMENT_NAME> [%s/<NEW_ASSESSMENT_NAME> | %s/<NEW_MAXIMUM_MARKS> | %s/<NEW_WEIGHTAGE>]";
    private static final String MESSAGE_FORMAT_INVALID_NEW_WEIGHTAGE = "Invalid new weightage. "
        + "Weightage must be between %,.2f (inclusive) and %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_INVALID_NEW_TOTAL_WEIGHTAGE = "Invalid new weightage. "
        + "Total new weightage exceeds %,.2f%%.";
    private static final String MESSAGE_FORMAT_INVALID_NEW_MAXIMUM_MARKS = "Invalid new maximum marks. "
        + "Maximum marks must be larger than %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_INVALID_NEW_NAME = "Invalid new name. "
        + "An assessment with the same name already exists.";
    private static final String MESSAGE_FORMAT_ASSESSMENT_EDITED = "Assessment in %s updated:\n  %s";

    public EditAssessmentCommand(String argument) {
        super(argument, EDIT_ASSESSMENT_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        boolean hasClassId = argumentMap.containsKey(KEY_CLASS_ID);
        boolean hasAssessmentName = argumentMap.containsKey(KEY_ASSESSMENT_NAME);
        boolean hasNewAssessmentName = argumentMap.containsKey(KEY_NEW_ASSESSMENT_NAME);
        boolean hasNewMaximumMarks = argumentMap.containsKey(KEY_NEW_MAXIMUM_MARKS);
        boolean hasNewWeightage = argumentMap.containsKey(KEY_NEW_WEIGHTAGE);
        boolean hasOptionalArgument = hasClassId && hasAssessmentName
            && (hasNewAssessmentName || hasNewMaximumMarks || hasNewWeightage);
        if (!hasOptionalArgument) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String newMaximumMarksString = argumentMap.getOrDefault(KEY_NEW_MAXIMUM_MARKS, null);
        if (newMaximumMarksString != null) {
            if (!Util.isStringDouble(newMaximumMarksString)) {
                throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_NEW_MAXIMUM_MARKS,
                    Assessment.MINIMUM_MARKS)
                );
            }
        }

        String newWeightageString = argumentMap.getOrDefault(KEY_NEW_WEIGHTAGE, null);
        if (newWeightageString != null) {
            if (!Util.isStringDouble(newWeightageString)) {
                throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_NEW_WEIGHTAGE,
                    Assessment.WEIGHTAGE_RANGE[0],
                    Assessment.WEIGHTAGE_RANGE[1])
                );
            }
        }
    }

    /**
     * Executes the edit_assessment command and edits an assessment a particular class.
     * The name, maximum marks and weightage of the assessment can be changed.
     * Some new values can be missing and the old values of them will not change.
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
        Assessment assessment = assessmentList.getAssessment(name);
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }

        assert (argumentMap.containsKey(KEY_NEW_ASSESSMENT_NAME) || argumentMap.containsKey(KEY_NEW_MAXIMUM_MARKS)
            || argumentMap.containsKey(KEY_NEW_WEIGHTAGE)) : "a new value to edit should at least exist.";

        String newWeightageString = argumentMap.getOrDefault(KEY_NEW_WEIGHTAGE, null);
        boolean hasValidNewWeightage = false;
        if (newWeightageString != null) {
            hasValidNewWeightage = checkWeightage(newWeightageString, assessmentList, name);
        }

        String newMaximumMarksString = argumentMap.getOrDefault(KEY_NEW_MAXIMUM_MARKS, null);
        boolean hasValidNewMaximumMarks = false;
        if (newMaximumMarksString != null) {
            hasValidNewMaximumMarks = checkMaximumMarks(newMaximumMarksString);
        }

        String newName = argumentMap.getOrDefault(KEY_NEW_ASSESSMENT_NAME, null);
        boolean hasValidNewName = false;
        if (newName != null) {
            hasValidNewName = checkNewName(newName, assessmentList, assessment);
        }

        if (hasValidNewWeightage) {
            double newWeightage = Double.parseDouble(newWeightageString);
            assessment.setWeightage(newWeightage);
        }
        if (hasValidNewMaximumMarks) {
            double newMaximumMarks = Double.parseDouble(newMaximumMarksString);
            assessment.setMaximumMarks(newMaximumMarks);
        }
        if (hasValidNewName) {
            assessment.setName(newName);
        }

        assert storage != null : "storage should exist.";
        storage.save(classList);

        assert ui != null : "ui should exist.";
        ui.printMessage(String.format(MESSAGE_FORMAT_ASSESSMENT_EDITED, classId,
            assessment));
    }

    public boolean checkWeightage(String newWeightageString, AssessmentList assessmentList, String name)
            throws TaaException {
        assert Util.isStringDouble(newWeightageString);
        double newWeightage = Double.parseDouble(newWeightageString);
        if (!Assessment.isWeightageWithinRange(newWeightage)) {
            throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_NEW_WEIGHTAGE,
                    Assessment.WEIGHTAGE_RANGE[0],
                    Assessment.WEIGHTAGE_RANGE[1])
            );
        }
        double totalWeightage = 0;
        ArrayList<Assessment> assessments = assessmentList.getAssessments();
        for (Assessment a : assessments) {
            if (!a.getName().equalsIgnoreCase(name)) {
                totalWeightage += a.getWeightage();
            }
        }
        double totalNewWeightage = totalWeightage + newWeightage;
        if (!Assessment.isWeightageWithinRange(totalNewWeightage)) {
            throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_NEW_TOTAL_WEIGHTAGE,
                    Assessment.WEIGHTAGE_RANGE[1])
            );
        }
        return true;
    }

    public boolean checkMaximumMarks(String newMaximumMarksString) throws TaaException {
        assert Util.isStringDouble(newMaximumMarksString);
        double newMaximumMarks = Double.parseDouble(newMaximumMarksString);
        if (!Assessment.isMaximumMarksValid(newMaximumMarks)) {
            throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_NEW_MAXIMUM_MARKS,
                    Assessment.MINIMUM_MARKS)
            );
        }
        return true;
    }

    public boolean checkNewName(String newName, AssessmentList assessmentList, Assessment assessment)
            throws TaaException {
        boolean isValidNewName = assessmentList.checkRepeatedName(assessment, newName);
        if (!isValidNewName) {
            throw new TaaException(String.format(MESSAGE_FORMAT_INVALID_NEW_NAME));
        }
        return true;
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_EDIT_ASSESSMENT_USAGE,
            COMMAND_EDIT_ASSESSMENT,
            KEY_CLASS_ID,
            KEY_ASSESSMENT_NAME,
            KEY_NEW_ASSESSMENT_NAME,
            KEY_NEW_MAXIMUM_MARKS,
            KEY_NEW_WEIGHTAGE
        );
    }
}
