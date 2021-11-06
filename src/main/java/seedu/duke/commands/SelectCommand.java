package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.ItemType;

import static seedu.duke.parser.ItemType.EVENT;
import static seedu.duke.parser.ItemType.MEMBER;

public class SelectCommand extends Command {

    protected ItemType itemType;
    private int eventIndex;
    private int taskIndex;
    private int memberIndex;

    public SelectCommand(ItemType itemType, int index) {
        this.itemType = itemType;
        assert itemType.equals(EVENT) | itemType.equals(MEMBER);
        if (itemType.equals(EVENT)) {
            eventIndex = index;
        } else if (itemType.equals(MEMBER)) {
            memberIndex = index;
        }
    }

    public SelectCommand(ItemType itemType, int eventIndex, int taskIndex) {
        this.itemType = itemType;
        this.eventIndex = eventIndex;
        this.taskIndex = taskIndex;
    }

    public CommandResult execute() {
        Item selectedItem;
        Member selectedMember;

        switch (itemType) {
        case EVENT:
            selectedItem = getEventFromIndex(eventIndex);
            return new CommandResult(Ui.getSelectedEventMessage((Event) selectedItem));
        case TASK:
            selectedItem = getTaskFromEventIndex(eventIndex, taskIndex);
            return new CommandResult(Ui.getSelectedTaskMessage((Task) selectedItem));
        case MEMBER:
            selectedMember = getMemberFromIndex(memberIndex);
            return new CommandResult(Ui.getSelectedMemberMessage(selectedMember));
        default:
            return new CommandResult("Something went wrong.");
        }
    }

    private Event getEventFromIndex(int eventIndex) {
        return Duke.eventCatalog.get(eventIndex);
    }

    private Task getTaskFromEventIndex(int eventIndex, int taskIndex) {
        return Duke.eventCatalog.get(eventIndex).getFromTaskList(taskIndex);
    }

    private Member getMemberFromIndex(int memberIndex) {
        return Duke.memberRoster.get(memberIndex);
    }
}
