package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.List;

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
            System.out.println("Here are the tasks in your list:");
            //ui.printList(Duke.itemList);
            break;
        case "event":
        case "task":
            // Cast TaskList as a list of Item objects.
            ArrayList<Item> castedTaskList = new ArrayList<Item>(Duke.taskList);
            bubbleSortTask(castedTaskList);
            System.out.println(listType
                    + " list! Total number of "
                    + listType
                    + "s = "
                    + sortedList.size());
            //ui.printList(sortedList);
            break;
        default:
            return new CommandResult("please specify type for list "
                    + "[list, list deadline, list event,list todo ]");
        }

        return new CommandResult("--------END OF LIST-----------");
    }

    // TODO: Perhaps this can be deleted since the events and items are now in separate lists
    private void getListTypeTasks(String type) {

        /*for (Item task : Duke.itemList) {
            if (Item.getItemType().equalsIgnoreCase(type)) {
                sortedList.add(task);
            }
        }*/
    }

    private void bubbleSortTask(ArrayList<Item> list) {
        // TODO: Modify bubble sort to compare the two strings
        /*for (int j = 0; j < list.size() - 1; j++) {
            for (int i = 0; i < list.size() - j - 1; i++) {
                if (list.get(i + 1).getDateValue().isBefore(list.get(i).getDateValue())) {
                    swap(i, list);
                }
            }
        }*/
    }

    private void swap(int i, ArrayList<Item> list) {
        Item t;
        t = list.get(i);
        list.set(i, list.get(i + 1));
        list.set(i + 1, t);
    }
}
