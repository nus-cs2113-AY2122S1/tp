package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;


public class NextCommand extends Command {
    protected static String nextItem;
    protected static int eventIndex;

    public NextCommand(String command, int index) {
        nextItem = command;
        eventIndex = index;
    }

    public CommandResult execute() {
        switch (nextItem) {
        case "event":
            Ui.printEvent(Duke.eventCatalog.get(eventIndex));
            break;
        case "task":
            Ui.printTask(Duke.eventCatalog.get(eventIndex).getFromTaskList(0));
            break;
        case "noTask":
            return new CommandResult("This Event has no Tasks!");
        default:
            return new CommandResult("please specify type for list "
                    + System.lineSeparator()
                    + "[list: to see overall schedule"
                    + System.lineSeparator()
                    + "list [EVENT_NUM] -t : to see tasks in an Event"
                    + System.lineSeparator()
                    + "list [Event Index] t/[Task Index] : to see members in a Task");
        }
        return new CommandResult("Hope you have prepared everything!");
    }
}
