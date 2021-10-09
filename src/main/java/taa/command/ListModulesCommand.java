package taa.command;

import taa.exception.TaaException;
import taa.Ui;
import taa.module.ModuleList;

public class ListModulesCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "There are no modules in the list.";

    public ListModulesCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList modules, Ui ui) throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        if (modules.getSize() == 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ui.printMessage(modules.toString());
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
