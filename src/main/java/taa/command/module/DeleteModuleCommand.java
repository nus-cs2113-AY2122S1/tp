package taa.command.module;

import taa.Ui;
import taa.command.Command;
import taa.exception.TaaException;
import taa.module.Module;
import taa.module.ModuleList;
import taa.storage.Storage;

public class DeleteModuleCommand extends Command {
    private static final String KEY_MODULE_CODE = "c";
    private static final String[] DELETE_MODULE_ARGUMENT_KEYS = {KEY_MODULE_CODE};

    private static final String MESSAGE_FORMAT_DELETE_MODULE_USAGE = "%s %s/<MODULE_CODE>";
    private static final String MESSAGE_FORMAT_DELETE_FAILED = "Fail to delete module:\n  %s";
    private static final String MESSAGE_FORMAT_MODULE_DELETED = "Module deleted:\n  %s";

    public DeleteModuleCommand(String argument) {
        super(argument, DELETE_MODULE_ARGUMENT_KEYS);
    }

    @Override
    public void checkArgument() throws TaaException {
        if (argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (!hasAllArguments()) {
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

        String message;
        boolean isSuccessful = moduleList.deleteModule(module);
        if (isSuccessful) {
            message = String.format(MESSAGE_FORMAT_MODULE_DELETED, module);
        } else {
            message = String.format(MESSAGE_FORMAT_DELETE_FAILED, module);
        }

        storage.save(moduleList);
        ui.printMessage(message);
    }

    @Override
    protected String getUsage() {
        return String.format(MESSAGE_FORMAT_DELETE_MODULE_USAGE, COMMAND_DELETE_MODULE, KEY_MODULE_CODE);
    }
}
