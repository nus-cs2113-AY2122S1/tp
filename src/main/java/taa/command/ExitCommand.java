package taa.command;

import taa.storage.Storage;
import taa.exception.TaaException;
import taa.Ui;
import taa.module.ModuleList;

public class ExitCommand extends Command {
    private static final String MESSAGE_FORMAT_EXIT_USAGE = "Usage: %s";

    public ExitCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList moduleList, Ui ui, Storage storage) throws TaaException {
        if (!argument.isEmpty()) {
            throw new TaaException(getUsageMessage());
        }

        ui.printExitMessage();
        this.isExit = true;
    }

    @Override
    protected String getUsageMessage() {
        return String.format(MESSAGE_FORMAT_EXIT_USAGE, COMMAND_EXIT);
    }
}
