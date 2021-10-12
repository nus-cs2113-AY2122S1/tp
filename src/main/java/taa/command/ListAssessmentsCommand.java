package taa.command;

import taa.storage.Storage;
import taa.Ui;
import taa.assessment.AssessmentList;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;

public class ListAssessmentsCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String[] LIST_ASSESSMENTS_ARGUMENT_KEYS = {KEY_MODULE_CODE};

    private static final String MESSAGE_LIST_EMPTY = "There are no assessments in the module.";

    private static final String MESSAGE_FORMAT_LIST_ASSESSMENTS_USAGE = "Usage: %s %s/<MODULE_CODE>";

    public ListAssessmentsCommand(String argument) {
        super(argument, LIST_ASSESSMENTS_ARGUMENT_KEYS);
    }

    /**
     * Lists all the assessments of a particular module.
     *
     * @param moduleList The list of modules.
     * @param ui The ui instance to handle interactions with the user.
     * @param storage The storage instance to handle saving.
     * @throws TaaException If the user inputs an invalid command.
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

        AssessmentList assessmentList = module.getAssessmentList();
        if (assessmentList.getSize() == 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
            return;
        }

        ui.printMessage(assessmentList.toString());
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_LIST_ASSESSMENTS_USAGE,
                COMMAND_LIST_ASSESSMENTS,
                KEY_MODULE_CODE
        );
    }
}
