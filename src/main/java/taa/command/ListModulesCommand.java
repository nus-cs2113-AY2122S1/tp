package taa.command;

import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.ModuleList;

public class ListModulesCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "There are no modules in the list.";

    public ListModulesCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (moduleList.getSize() == 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ui.printMessage(moduleList.toString());
        }
    }

    @Override
    protected String getUsageMessage() {
        return String.format(
                MESSAGE_FORMAT_GENERIC_USAGE,
                COMMAND_LIST_MODULES
        );
    }
}
