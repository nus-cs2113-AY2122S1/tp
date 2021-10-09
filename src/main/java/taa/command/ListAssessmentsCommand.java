package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.module.Module;
import taa.module.ModuleList;

public class ListAssessmentsCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "There are no assessments in the module.";
    private static final String[] LIST_ASSESSMENTS_ARGUMENT_KEYS = {"c"};

    public ListAssessmentsCommand(String argument) {
        super(argument, LIST_ASSESSMENTS_ARGUMENT_KEYS);
    }

    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        if (argument.isEmpty()) {
            // TODO Usage format message
            throw new CustomException("");
        }

        String moduleCode = argumentMap.get("c");
        Module module = modules.getModule(moduleCode);
        if (module.getAssessmentsSize() == 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ui.printMessage(module.getAssessments().toString());
        }
    }
}
