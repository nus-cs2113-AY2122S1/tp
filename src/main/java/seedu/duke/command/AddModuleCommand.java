package seedu.duke.command;

import seedu.duke.CustomException;
import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.module.ModuleList;
import seedu.duke.module.Module;

import java.util.HashMap;

public class AddModuleCommand extends Command {
    private static String MESSAGE_MODULE_ADDED_FORMAT = "Module added:\n  %s\nThere are %d modules in the list.";

    private static String[] argumentKeys = new String[]{"n"};

    public AddModuleCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        if (argument.isEmpty()) {
            throw new CustomException("");
        }

        String[] argumentSplit = Parser.splitFirstSpace(argument);
        String moduleCode = argumentSplit[0];
        String additionalArgument = argumentSplit[1];

        HashMap<String, String> argumentsMap = Parser.getArgumentsFromString(additionalArgument, argumentKeys);
        if (!checkArgumentsMap(argumentsMap, argumentKeys)) {
            throw new CustomException("");
        }

        String name = argumentsMap.get("n");
        Module module = new Module(moduleCode, name);
        modules.addModule(module);

        ui.printMessage(String.format(MESSAGE_MODULE_ADDED_FORMAT, module, modules.getSize()));
    }
}
