package seedu.duke.command;

import seedu.duke.CustomException;
import seedu.duke.Ui;
import seedu.duke.module.ModuleList;

public class ExitCommand extends Command {
    public ExitCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        ui.printExitMessage();
        this.isExit = true;
    }
}
