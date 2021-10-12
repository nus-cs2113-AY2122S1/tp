package seedu.duke.commands;

import seedu.duke.Ui;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;


// meant to be used after FindCommand, where the user can view the details of a specific item
// this class assumes that there were no exceptions thrown while using FindCommand and that at least one item
// was found in FindCommand

//                        filteredItemList
// FindCommand(keyword) --------------------> SelectCommand(index)
public class SelectCommand extends Command {

    protected int itemIndex;

    // eg select 1
    public SelectCommand(String[] command) {
        try {
            itemIndex = getItemIndex(command);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public CommandResult execute() {
        try {
            Item selectedItem = getItemFromIndex(itemIndex);
            if (isTask(selectedItem)) {
                return new CommandResult(Ui.getSelectedTaskMessage((Task) selectedItem));
            } else if (isEvent(selectedItem)) {
                return new CommandResult(Ui.getSelectedEventMessage((Event) selectedItem));
            }
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
        return new CommandResult("I can't select the item you want!");
    }

    private Item getItemFromIndex(int index) throws DukeException {
        if (FindCommand.filteredItemList.isEmpty()) {
            throw new DukeException("Search for some items first, then select the item you want.");
        }
        return FindCommand.filteredItemList.get(index);
    }

    private int getItemIndex(String[] command) throws DukeException {
        int itemIndex = 0;
        try {
            itemIndex = Integer.parseInt(command[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please enter the numeric index of the item you wish to select.");
        }
        return itemIndex;
    }

    private boolean isEvent(Item selectedItem) {
        String type = selectedItem.getItemType();
        return type.equalsIgnoreCase("event");
    }

    private boolean isTask(Item selectedItem) {
        String type = selectedItem.getItemType();
        return type.equalsIgnoreCase("task");
    }
}
