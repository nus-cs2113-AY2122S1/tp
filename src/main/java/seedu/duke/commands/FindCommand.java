package seedu.duke.commands;

import seedu.duke.Parser;
import seedu.duke.Ui;
import seedu.duke.items.Item;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindCommand extends Command {

    public String keyword;
    public static ArrayList<Item> combinedItemList = new ArrayList<>();

    // to pass into SelectCommand
    public static ArrayList<Item> filteredItemList = new ArrayList<>();

    public FindCommand(String[] command) {
        keyword = command[1];
    }

    public CommandResult execute() {
        combinedItemList = Parser.makeMainList();
        filteredItemList = filterItemsByString(keyword);

        // can replace this with exceptions
        if (filteredItemList.isEmpty()) {
            return new CommandResult("Failed: no matching items found");
        }
        Ui.printList(filteredItemList);
        return new CommandResult(filteredItemList.size() + " items found.");
    }

    public static ArrayList<Item> filterItemsByString(String keyword) {
        filteredItemList = (ArrayList<Item>) combinedItemList.stream()
                .filter((item) -> item.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
        return filteredItemList;
    }
}
