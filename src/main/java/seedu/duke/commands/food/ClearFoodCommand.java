package seedu.duke.commands.food;

import seedu.duke.commands.Command;
import seedu.duke.storage.Storage;
import seedu.duke.storage.StorageFood;
import seedu.duke.ui.Ui;

import java.io.IOException;

//@@author ngnigel99
/**
 * Clears the current list  of food, and clear entries in storage.
 * @author  ngnigel99
 */
public class ClearFoodCommand extends Command {

    public ClearFoodCommand() {
        helpMessage = "Clears the current list of food, and clear entries in storage";
        syntax = "food clear";
    }

    /**
     * Executes clear food command.
     * @param ui      The component of Duke that deals with the interaction with the user.
     * @param storage The component of Duke that deals with loading tasks from the file and saving tasks in the file.
     * @throws IOException If there is an error reading/ writing to save file.
     */

    @Override
    public void execute(Ui ui, Storage storage) throws IOException {
        storage.whatIAteTodayList.clearList();
        StorageFood.saveList(storage.whatIAteTodayList);
    }
}
