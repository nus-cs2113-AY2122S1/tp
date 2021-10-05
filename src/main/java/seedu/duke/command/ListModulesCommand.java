package seedu.duke.command;

import seedu.duke.CustomException;
import seedu.duke.Ui;
import seedu.duke.module.ModuleList;

public class ListModulesCommand extends Command {
    private static final String MESSAGE_LIST_EMPTY = "There are no modules in the list.";

    public ListModulesCommand(String argument) {
        super(argument);
    }

    @Override
    public void execute(ModuleList modules, Ui ui) throws CustomException {
        if (modules.getSize() == 0) {
            ui.printMessage(MESSAGE_LIST_EMPTY);
        } else {
            ui.printMessage(modules.toString());
        }
    }
}
