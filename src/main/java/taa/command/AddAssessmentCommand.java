package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.assessment.Assessment;
import taa.module.Module;
import taa.module.ModuleList;

public class AddAssessmentCommand extends Command {
    private static final String MESSAGE_ASSESSMENT_ADDED_FORMAT =
            "Assessment added:\n  %s\nThere are %d assessments in the %s module.";

    private static final String[] ADD_ASSESSMENT_ARGUMENT_KEYS = {"c", "a", "w"};

    public AddAssessmentCommand(String argument) {
        super(argument, ADD_ASSESSMENT_ARGUMENT_KEYS);
    }

    /**
     * Adds an assessment to a particular module.
     *
     * @param modules The list of modules.
     * @param ui      The ui instance to handle interactions with the user.
     * @throws CustomException If the user inputs an invalid command.
     */
    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        if (argument.isEmpty()) {
            // TODO Usage format message
            throw new CustomException("");
        }

        if (!checkArgumentMap()) {
            // TODO Invalid/missing arguments message
            throw new CustomException("");
        }

        String moduleCode = argumentMap.get("c");
        if (moduleCode.contains(" ")) {
            // TODO Invalid module code message
            throw new CustomException("");
        }
        String assessmentName = argumentMap.get("a");
        double assessmentWeightage = Double.parseDouble(argumentMap.get("w"));
        Module module = modules.getModule(moduleCode);
        Assessment assessment = new Assessment(assessmentName, assessmentWeightage);
        module.addAssessment(assessment);

        ui.printMessage(String.format(MESSAGE_ASSESSMENT_ADDED_FORMAT,
                assessment, module.getAssessmentsSize(), module));
    }
}
