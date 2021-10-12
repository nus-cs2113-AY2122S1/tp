package seedu.duke.commands;


// meant to be used after FindCommand, where the user can view the details of a specific item
// this class assumes that there were no exceptions thrown while using FindCommand and that at least one item
// was found in FindCommand

//                        filteredItemList
// FindCommand(keyword) --------------------> SelectCommand(index)

import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;


public class SelectCommand extends Command{

    protected int itemIndex;

    // eg select 1
    public SelectCommand(String[] command) {
        itemIndex = Integer.parseInt(command[1]);
    }

    public CommandResult execute() {
        Item selectedItem = FindCommand.filteredItemList.get(itemIndex);
        if (isEvent(selectedItem)) {
            Ui.printLineBreak();
            System.out.println("Here are the details of the event:");
            Ui.printEvent((Event) selectedItem);
            return new CommandResult(Ui.getLineBreak());
        } else if (isTask(selectedItem)) {
            Ui.printLineBreak();
            System.out.println("Here are the details of the task:");
            Ui.printTask((Task) selectedItem);
            return new CommandResult(Ui.getLineBreak());
        }
        return new CommandResult("I can't select the item you want!");
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
