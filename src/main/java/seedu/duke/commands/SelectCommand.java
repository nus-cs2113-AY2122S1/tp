package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;
import seedu.duke.parser.ItemType;

public class SelectCommand extends Command {

    protected ItemType itemType;
    private final int eventIndex;
    private int taskIndex;

    public SelectCommand(ItemType itemType, int eventIndex) {
        this.itemType = itemType;
        this.eventIndex = eventIndex;
    }

    public SelectCommand(ItemType itemType, int eventIndex, int taskIndex) {
        this.itemType = itemType;
        this.eventIndex = eventIndex;
        this.taskIndex = taskIndex;
    }

    public CommandResult execute() {
        Item selectedItem;

        switch (itemType) {
        case EVENT:
            selectedItem = getEventFromIndex(eventIndex);
            return new CommandResult(Ui.getSelectedEventMessage((Event) selectedItem));
        case TASK:
            selectedItem = getTaskFromEventIndex(eventIndex, taskIndex);
            return new CommandResult(Ui.getSelectedTaskMessage((Task) selectedItem));
        case MEMBER:
        default:
            return new CommandResult("");
        }
    }

    private Event getEventFromIndex(int eventIndex) {
        return Duke.eventCatalog.get(eventIndex);
    }

    private Task getTaskFromEventIndex(int eventIndex, int taskIndex) {
        return Duke.eventCatalog.get(eventIndex).getFromTaskList(taskIndex);
    }

}
