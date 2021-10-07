package seedu.duke.commands;

import seedu.duke.items.Item;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static seedu.duke.Duke.eventList;
import static seedu.duke.Duke.taskList;

public class SelectCommand extends Command {

    public String keyword;
    public static ArrayList<Item> combinedItemList = new ArrayList<>();

    public SelectCommand(String[] command) {
        keyword = command[2];
    }

    public CommandResult execute() {
        fillCombinedItemList();
        ArrayList<Item> filteredItemList = filterItemsByString(keyword);

        // can replace this with exceptions
        if (filteredItemList.isEmpty()) {
            return new CommandResult("Failed: no matching items found");
        }
        printFilteredItemList(filteredItemList);
        return new CommandResult(filteredItemList.size() + " items found.");
    }

    public static void fillCombinedItemList() {
        combinedItemList.clear();
        combinedItemList.addAll(eventList);
        combinedItemList.addAll(taskList);
    }

    public static ArrayList<Item> filterItemsByString(String keyword) {
        ArrayList<Item> filteredItemList = (ArrayList<Item>) combinedItemList.stream()
                .filter((item) -> item.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        return filteredItemList;
    }

    // can put below two methods into Ui later
    public static void printFilteredItemList(ArrayList<Item> filteredItems) {
        System.out.println("Here are the items: " + System.lineSeparator());
        printItemList(filteredItems);
    }

    public static void printItemList(ArrayList<Item> items) {
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + "." + items.get(i));
        }
    }
}
