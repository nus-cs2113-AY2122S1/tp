//@@author YEOWEIHNGWHYELAB

package seedu.budgettracker.logic.commands;

import seedu.budgettracker.logic.commands.exceptions.CommandException;
import seedu.budgettracker.storage.Storage;
import seedu.budgettracker.ui.TextUi;

/**
 * DbCommand class will create a new Storage() object and then list all the available data
 * .txt file that is in the valid year format. Eg. "2021.txt"
 */
public class DbCommand extends Command {
    public static final String COMMAND_WORD = "db";
    private static final String SEPARATOR = "========================================================";

    @Override
    /**
     * Creates a new Storage() object to obtain all legitimate data text files and using
     * the TextUI method to print those files name out.
     */
    public void execute() throws CommandException {
        Storage budgetStorage = new Storage();
        TextUi.printAvailableDataBase(budgetStorage);
        System.out.println(SEPARATOR);
    }
}
