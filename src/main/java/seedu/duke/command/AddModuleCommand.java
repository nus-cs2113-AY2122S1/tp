package seedu.duke.command;

import seedu.duke.CustomException;
import seedu.duke.Ui;
import seedu.duke.module.ModuleList;
import seedu.duke.module.Module;

public class AddModuleCommand extends Command {
    private static final String MESSAGE_MODULE_ADDED_FORMAT = "Module added:\n  %s\nThere are %d modules in the list.";

    private static final String[] ADD_MODULE_ARGUMENT_KEYS = {"c","n"};

    public AddModuleCommand(String argument) {
        super(argument, ADD_MODULE_ARGUMENT_KEYS);
    }

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

        String name = argumentMap.get("n");
        Module module = new Module(moduleCode, name);
        modules.addModule(module);

        ui.printMessage(String.format(MESSAGE_MODULE_ADDED_FORMAT, module, modules.getSize()));
    }
}
