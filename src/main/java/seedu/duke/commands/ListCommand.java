package seedu.duke.commands;

import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Item;

import java.util.ArrayList;

public class ListCommand extends Command {
    public static ArrayList<Item> sortedList = new ArrayList<>();

    protected String listType;
    protected String[] userCommand;
    public ListCommand(String[] command) {
        this.userCommand = command;
        if (command.length == 1) {
            this.listType = "list";
        } else if (command[2].equalsIgnoreCase("-t")) {
            this.listType = "task";
        }
        else if (command[2].contains("t/")) {
            this.listType = "member";
        }else {
            this.listType = "others";
        }
    }

    public CommandResult execute() {

        sortedList.clear();

        switch (listType) {
        case "list":
            Ui.printEventCatalog();
            break;
        case "task":
            Event event1 = Duke.eventCatalog.get(Integer.parseInt(userCommand[1]) - 1);
            Ui.printList(event1.getTaskList());
            break;
        case "member":
            Event event2 = Duke.eventCatalog.get(Integer.parseInt(userCommand[1]) - 1);
            String[] taskCommand = userCommand[2].split("/", 1);
            Integer taskNum = Integer.parseInt(taskCommand[2]);
            Task task = event2.getFromTaskList(taskNum);
            Ui.printList(event2.getTaskList());
            break;
        default:
            return new CommandResult("please specify type for list "
                    + "[list, list -t [EVENT_NUM]]");
        }

        return new CommandResult("--------END OF LIST-----------");
    }
}
