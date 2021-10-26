package taa.command.module;

import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.storage.Storage;

public class EditModuleCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String KEY_NEW_MODULE_CODE = "nc";
    private static final String KEY_NEW_MODULE_NAME = "n";
    private static final String[] EDIT_MODULE_ARGUMENT_KEYS = {
        KEY_MODULE_CODE,
        KEY_NEW_MODULE_CODE,
        KEY_NEW_MODULE_NAME
    };

    private static final String MESSAGE_FORMAT_EDIT_MODULE_USAGE = "%s %s/<MODULE_CODE> "
        + "[%s/<NEW_MODULE_CODE>] [%s/<NEW_MODULE_NAME>]";
    private static final String MESSAGE_FORMAT_MODULE_EDITED = "Module edited:\n  %s";

    public EditModuleCommand(String argument) {
        super(argument, EDIT_MODULE_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!argumentMap.containsKey(KEY_MODULE_CODE)) {
            throw new TaaException(getMissingArgumentMessage());
        }

        boolean hasNewModuleCode = argumentMap.containsKey(KEY_NEW_MODULE_CODE);
        boolean hasNewModuleName = argumentMap.containsKey(KEY_NEW_MODULE_NAME);
        boolean hasNecessaryArguments = (hasNewModuleCode || hasNewModuleName);
        if (!hasNecessaryArguments) {
            throw new TaaException(getMissingArgumentMessage());
        }
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        assert argumentMap.containsKey(KEY_MODULE_CODE);
        String moduleCode = argumentMap.get(KEY_MODULE_CODE);
        Module module = moduleList.getModuleWithCode(moduleCode);
        if (module == null) {
            throw new TaaException(MESSAGE_MODULE_NOT_FOUND);
        }

        assert (argumentMap.containsKey(KEY_NEW_MODULE_CODE) || argumentMap.containsKey(KEY_NEW_MODULE_NAME));
        String newModuleCode = argumentMap.getOrDefault(KEY_NEW_MODULE_CODE, null);
        if (newModuleCode != null) {
            module.setCode(newModuleCode);
        }

        String newModuleName = argumentMap.getOrDefault(KEY_NEW_MODULE_NAME, null);
        if (newModuleName != null) {
            module.setName(newModuleName);
        }

        storage.save(moduleList);

        ui.printMessage(String.format(MESSAGE_FORMAT_MODULE_EDITED, module));
    }

    @Override
    protected String getUsage() {
        return String.format(
            MESSAGE_FORMAT_EDIT_MODULE_USAGE,
            COMMAND_EDIT_MODULE,
            KEY_MODULE_CODE,
            KEY_NEW_MODULE_CODE,
            KEY_NEW_MODULE_NAME
        );
    }
}
