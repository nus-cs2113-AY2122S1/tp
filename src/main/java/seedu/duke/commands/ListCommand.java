package seedu.duke.commands;

import seedu.duke.Parser;
import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Item;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static ArrayList<Item> sortedList = new ArrayList<>();

    protected String listType;

    public ListCommand(String[] command) {

        if (command.length == 1) {
            this.listType = "list";
        } else if (command[1].equalsIgnoreCase("-e")) {
            this.listType = "event";
        } else if (command[1].equalsIgnoreCase("-t")) {
            this.listType = "task";
        } else {
            this.listType = "others";
        }
    }

    public CommandResult execute() {

        sortedList.clear();

        switch (listType) {
        case "list":
            sortedList = Parser.makeMainList();
            Parser.bubbleSortItems(sortedList);
            System.out.println("Here is your overall schedule:");
            Ui.printList(sortedList);
            break;
        case "event":
            sortedList = new ArrayList<>(Duke.eventList);
            Parser.bubbleSortItems(sortedList);
            System.out.println("Here are all the events in your list:");
            Ui.printList(sortedList);
            break;
        case "task":
            sortedList = new ArrayList<>(Duke.taskList);
            Parser.bubbleSortItems(sortedList);
            System.out.println("Here are all the tasks in your list:");
            Ui.printList(sortedList);
            break;
        default:
            return new CommandResult("please specify type for list "
                    + "[list, list -e, list -t ]");
        }

        return new CommandResult("--------END OF LIST-----------");
    }
}
