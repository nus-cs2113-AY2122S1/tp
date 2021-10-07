package taa.command;

import taa.CustomException;
import taa.Ui;
import taa.module.ModuleList;

public class ExitCommand extends Command {
    public ExitCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        if (!argument.isEmpty()) {
            // TODO Usage format message
            throw new CustomException("");
        }

        ui.printExitMessage();
        this.isExit = true;
    }
}
