package seedu.budgettracker.logic.commands;

import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.storage.Storage;
import seedu.budgettracker.ui.TextUi;

//@@author yeoweihngwhyelab
public class DbCommand extends Command {
    public static final String COMMAND_WORD = "db";
    private static final String SEPARATOR = "========================================================";

    //@@author yeoweihngwhyelab
    @Override
    public void execute() throws CommandException {
        Storage budgetStorage = new Storage();
        TextUi.printAvailableDataBase(budgetStorage);
        System.out.println(SEPARATOR);
    }
}
