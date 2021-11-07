package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.parser.ItemType;
import seedu.duke.parser.Parser;

public class DoneUndoCommand extends Command {

    private final String action;
    private final ItemType itemType;
    private final int[] indexes;

    private static final String DONE = "done";
    private static final String UNDO = "undo";

    public DoneUndoCommand(String action, ItemType itemType, int[] indexes) {
        this.action = action;
        this.itemType = itemType;
        this.indexes = indexes;
    }

    private String[] obtainAndMarkDoneItems() {
        String[] listOfItems = new String[2];
        StringBuilder listOfItemsMarkedDone = new StringBuilder();
        StringBuilder listOfAlreadyDoneItems = new StringBuilder();
        switch (itemType) {
        case EVENT:
            for (int index : indexes) {
                Event event = Duke.eventCatalog.get(index);
                if (!event.getIsDone()) {
                    event.markAsDone();
                    listOfItemsMarkedDone.append(event).append("\n");
                } else {
                    listOfAlreadyDoneItems.append(event).append("\n");
                }
            }
        case TASK:
            Event selectedEvent = Duke.eventCatalog.get(Parser.getIndexOfLastSelectedEvent());
            for (int index : indexes) {
                Task task = selectedEvent.getFromTaskList(index);
                if (!task.getIsDone()) {
                    task.markAsDone();
                    listOfItemsMarkedDone.append(task).append("\n");
                } else {
                    listOfAlreadyDoneItems.append(task).append("\n");
                }
            }
        default:
        }
        listOfItems[0] = listOfItemsMarkedDone.toString();
        listOfItems[1] = listOfAlreadyDoneItems.toString();
        return listOfItems;
    }

    private String[] obtainAndUndoItems() {
        String[] listOfItems = new String[2];
        StringBuilder listOfItemsUnmarked = new StringBuilder();
        StringBuilder listOfUndoneItems = new StringBuilder();
        switch (itemType) {
        case EVENT:
            for (int index : indexes) {
                Event event = Duke.eventCatalog.get(index);
                if (event.getIsDone()) {
                    event.undo();
                    listOfItemsUnmarked.append(event).append("\n");
                } else {
                    listOfUndoneItems.append(event).append("\n");
                }
            }
        case TASK:
            Event selectedEvent = Duke.eventCatalog.get(Parser.getIndexOfLastSelectedEvent());
            for (int index : indexes) {
                Task task = selectedEvent.getFromTaskList(index);
                if (task.getIsDone()) {
                    task.undo();
                    listOfItemsUnmarked.append(task).append("\n");
                } else {
                    listOfUndoneItems.append(task).append("\n");
                }
            }
        default:
        }
        listOfItems[0] = listOfItemsUnmarked.toString();
        listOfItems[1] = listOfUndoneItems.toString();
        return listOfItems;
    }

    public CommandResult execute() {
        if (action.equals(DONE)) {
            String[] listOfItems = obtainAndMarkDoneItems();
            return new CommandResult(Ui.getItemsMarkedDoneMessage(listOfItems[0], listOfItems[1]));
        }
        if (action.equals(UNDO)) {
            String[] listOfItems = obtainAndUndoItems();
            return new CommandResult(Ui.getItemsUnmarkedMessage(listOfItems[0], listOfItems[1]));
        }
        return null;
    }
}
