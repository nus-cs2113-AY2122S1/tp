package taa.command.assessment;

import taa.Ui;
import taa.assessment.Assessment;
import taa.assessment.AssessmentList;
import taa.command.Command;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.storage.Storage;
import taa.util.Util;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EditAssessmentCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_ASSESSMENT_NAME = "n";
    private static final String KEY_NEW_ASSESSMENT_NAME = "nn";
    private static final String KEY_NEW_MAXIMUM_MARKS = "m";
    private static final String KEY_NEW_WEIGHTAGE = "w";
    private static final String[] EDIT_ASSESSMENT_ARGUMENT_KEYS = {
        KEY_MODULE_CODE,
        KEY_ASSESSMENT_NAME,
        KEY_NEW_ASSESSMENT_NAME,
        KEY_NEW_MAXIMUM_MARKS,
        KEY_NEW_WEIGHTAGE
    };

    private static final String MESSAGE_FORMAT_EDIT_ASSESSMENT_USAGE = "%s %s/<MODULE_CODE> "
        + "%s/<ASSESSMENT_NAME> [%s/<NEW_ASSESSMENT_NAME>] [%s/<NEW_MAXIMUM_MARKS>] [%s/<NEW_WEIGHTAGE>]";
    private static final String MESSAGE_FORMAT_INVALID_NEW_WEIGHTAGE = "Invalid new weightage. "
        + "Weightage must be between %,.2f and %,.2f (inclusive)";
    private static final String MESSAGE_FORMAT_INVALID_NEW_MAXIMUM_MARKS = "Invalid new maximum marks. "
        + "Maximum marks must be larger than %d (inclusive)";
    private static final String MESSAGE_FORMAT_ASSESSMENT_EDITED = "Assessment in %s updated:\n  %s";

    public EditAssessmentCommand(String argument) {
        super(argument, EDIT_ASSESSMENT_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        boolean hasModuleCode = argumentMap.containsKey(KEY_MODULE_CODE);
        boolean hasAssessmentName = argumentMap.containsKey(KEY_ASSESSMENT_NAME);
        boolean hasNewAssessmentName = argumentMap.containsKey(KEY_NEW_ASSESSMENT_NAME);
        boolean hasNewMaximumMarks = argumentMap.containsKey(KEY_NEW_MAXIMUM_MARKS);
        boolean hasNewWeightage = argumentMap.containsKey(KEY_NEW_WEIGHTAGE);
        boolean hasNecessaryArguments = hasModuleCode && hasAssessmentName
            && (hasNewAssessmentName || hasNewMaximumMarks || hasNewWeightage);
        if (!hasNecessaryArguments) {
            throw new TaaException(getMissingArgumentMessage());
        }

        String newMaximumMarksString = argumentMap.getOrDefault(KEY_NEW_MAXIMUM_MARKS, null);
        if (newMaximumMarksString != null) {
            if (!Util.isStringInteger(newMaximumMarksString)) {
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
     * Executes the edit_assessment command and edits an assessment a particular module.
     * The name, maximum marks and weightage of the assessment can be changed.
     * Some new values can be missing and the old values of them will not change.
     *
     * @param moduleList The list of modules.
     * @param ui         The ui instance to handle interactions with the user.
     * @param storage    The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command or has missing/invalid argument(s).
     */
    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModuleWithCode(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        String name = argumentMap.get(KEY_ASSESSMENT_NAME);
        Assessment assessment = module.getAssessmentList().getAssessment(name);
        if (assessment == null) {
            throw new TaaException(MESSAGE_INVALID_ASSESSMENT_NAME);
        }

        assert (argumentMap.containsKey(KEY_NEW_ASSESSMENT_NAME) || argumentMap.containsKey(KEY_NEW_MAXIMUM_MARKS)
            || argumentMap.containsKey(KEY_NEW_WEIGHTAGE)) : "a new value to edit should at least exist.";
        String newWeightageString = argumentMap.getOrDefault(KEY_NEW_WEIGHTAGE, null);
        if (newWeightageString != null) {
            assert Util.isStringDouble(newWeightageString);
            double newWeightage = Double.parseDouble(newWeightageString);
            if (!Assessment.isWeightageWithinRange(newWeightage)) {
                throw new TaaException(String.format(
                        MESSAGE_FORMAT_INVALID_NEW_WEIGHTAGE,
                        Assessment.WEIGHTAGE_RANGE[0],
                        Assessment.WEIGHTAGE_RANGE[1])
                );
            }
            double totalNewWeightage = 0;
            ArrayList<Assessment> assessments = module.getAssessmentList().getAssessments();
            for (Assessment a : assessments) {
                if (a.getName() != name) {
                    totalNewWeightage += a.getWeightage();
                }
            }
            double newTotalNewWeightage = totalNewWeightage + newWeightage;
            if (Assessment.isWeightageWithinRange(newTotalNewWeightage)) {
                assessment.setWeightage(newWeightage);
            } else {
                throw new TaaException(String.format(
                        MESSAGE_FORMAT_INVALID_NEW_WEIGHTAGE,
                        Assessment.WEIGHTAGE_RANGE[0],
                        Assessment.WEIGHTAGE_RANGE[1])
                );
            }
        }

        String newName = argumentMap.getOrDefault(KEY_NEW_ASSESSMENT_NAME, null);
        if (newName != null) {
            assessment.setName(newName);
        }

        String newMaximumMarksString = argumentMap.getOrDefault(KEY_NEW_MAXIMUM_MARKS, null);
        if (newMaximumMarksString != null) {
            assert Util.isStringInteger(newMaximumMarksString);
            int newMaximumMarks = Integer.parseInt(newMaximumMarksString);
            if (newMaximumMarks < Assessment.MINIMUM_MARKS) {
                throw new TaaException(String.format(
                    MESSAGE_FORMAT_INVALID_NEW_MAXIMUM_MARKS,
                    Assessment.MINIMUM_MARKS)
                );
            }
            assessment.setMaximumMarks(newMaximumMarks);
        }

        assert storage != null : "storage should exist.";
        storage.save(moduleList);

        assert ui != null : "ui should exist.";
        ui.printMessage(String.format(MESSAGE_FORMAT_ASSESSMENT_EDITED, moduleCode,
            assessment));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_EDIT_ASSESSMENT_USAGE,
            COMMAND_EDIT_ASSESSMENT,
            KEY_MODULE_CODE,
            KEY_ASSESSMENT_NAME,
            KEY_NEW_ASSESSMENT_NAME,
            KEY_NEW_MAXIMUM_MARKS,
            KEY_NEW_WEIGHTAGE
        );
    }
}
