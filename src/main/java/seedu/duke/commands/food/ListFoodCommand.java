package seedu.duke.commands.food;

import seedu.duke.commands.Command;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

//@@author ngnigel99
/**
 * List all current records (assumed today for simplicity).
 * @author ngnigel99
 */
public class ListFoodCommand extends Command {
    public ListFoodCommand() {
        helpMessage = "Lists all food items recorded";
        syntax = "food list";
    }

    @Override
    public void execute(Ui ui, Storage storage) {
        storage.whatIAteTodayList.printList(true);
    }
}
