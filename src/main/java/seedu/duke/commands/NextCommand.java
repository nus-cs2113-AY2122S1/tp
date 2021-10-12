package seedu.duke.commands;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

import java.util.ArrayList;

public class NextCommand extends Command {
    public static ArrayList<Item> sortedList = new ArrayList<>();

    public NextCommand() {
    }

    public CommandResult execute() {
        sortedList = Parser.makeMainList();
        Parser.bubbleSortItems(sortedList);
        Item nextItem = sortedList.get(0);
        if (nextItem.getItemType().equalsIgnoreCase("task")) {
            Ui.printTask((Task) nextItem);
        } else if (nextItem.getItemType().equalsIgnoreCase("event")) {
            Ui.printEvent((Event) nextItem);
        }
        return new CommandResult("Hope you have prepared everything!");
    }
}
