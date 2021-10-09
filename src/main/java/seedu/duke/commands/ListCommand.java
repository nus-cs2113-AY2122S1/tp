package seedu.duke.commands;

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
            sortedList.addAll(Duke.eventList);
            sortedList.addAll(Duke.taskList);
            bubbleSortTask(sortedList);
            System.out.println("Here is your overall schedule:");
            Ui.printList(sortedList);
            break;
        case "event":
            sortedList = new ArrayList<Item>(Duke.eventList);
            bubbleSortTask(sortedList);
            System.out.println("Here are all the events in your list:");
            Ui.printList(sortedList);
            break;
        case "task":
            sortedList = new ArrayList<Item>(Duke.taskList);
            bubbleSortTask(sortedList);
            System.out.println("Here are all the tasks in your list:");
            Ui.printList(sortedList);
            break;
        default:
            return new CommandResult("please specify type for list "
                    + "[list, list -e, list -t ]");
        }

        return new CommandResult("--------END OF LIST-----------");
    }

    private void bubbleSortTask(ArrayList<Item> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            for (int i = 0; i < list.size() - j - 1; i++) {
                if (list.get(i + 1).getDateValue().isBefore(list.get(i).getDateValue())) {
                    swap(i, list);
                }
            }
        }
    }

    private void swap(int i, ArrayList<Item> list) {
        Item t;
        t = list.get(i);
        list.set(i, list.get(i + 1));
        list.set(i + 1, t);
    }
}
