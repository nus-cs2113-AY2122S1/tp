package taa.command;

import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.ModuleList;

public class ListModulesCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "There are no modules in the list.";

    private static final String MESSAGE_FORMAT_OUTPUT = "Module list:\n%s";

    public ListModulesCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        String message;
        if (moduleList.getSize() == 0) {
            message = MESSAGE_LIST_EMPTY;
        } else {
            message = String.format(MESSAGE_FORMAT_OUTPUT, moduleList);
        }

        ui.printMessage(message);
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_GENERIC_USAGE,
                COMMAND_LIST_MODULES
        );
    }
}
