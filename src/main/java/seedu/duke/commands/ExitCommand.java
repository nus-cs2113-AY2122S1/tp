package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author nvbinh15

/**
 * A representation of the command for exiting the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     *  @param ui The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.printGoodBye();
        System.exit(0);
    }
}
