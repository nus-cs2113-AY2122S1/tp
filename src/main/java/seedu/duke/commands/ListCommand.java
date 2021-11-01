package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.parser.ItemType;

import java.util.ArrayList;

public class ListCommand extends Command {
    protected String listType;
    private int eventIndex;
    private int taskIndex;
    protected String feedback = "--------END OF LIST-----------";

    public ListCommand(String listType, int eventIndex, int taskIndex) {
        this.listType = listType;
        this.eventIndex = eventIndex;
        this.taskIndex = taskIndex;
    }

    public CommandResult execute() {
        try {
            switch (listType) {
            case "event":
                listingOverallSchedule();
                break;
            case "task":
                listingTaskDetails();
                break;
            case "member":
                listingMemberDetails();
                break;
            case "memberRoster":
                listingMemberRoster();
                break;
            case "EventError":
                return new CommandResult("Please specify which event to view!");
            default:
                return new CommandResult("please specify type for list "
                        + System.lineSeparator()
                        + "[list: to see overall schedule"
                        + System.lineSeparator()
                        + "list [EVENT_NUM] -t : to see tasks in an Event"
                        + System.lineSeparator()
                        + "list [Event Index] t/[Task Index] : to see members in a Task");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Please check through the format carefully");
            Ui.listUsageCommands();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            Ui.listUsageCommands();
        }
        return new CommandResult(feedback);

    }

    private void listingMemberRoster() {
        System.out.println("List of members in MemberRoster");
        Ui.printMemberRoster();
    }

    private void listingOverallSchedule() {
        System.out.println("OVERALL SCHEDULE"
                + System.lineSeparator() + "=======================");
        Ui.printEventCatalog();
        Ui.listUsageCommands();
    }

    private void listingTaskDetails() throws DukeException {
        Event event1;
        try {
            event1 = Duke.eventCatalog.get(eventIndex);
            System.out.println("Event: " + event1.getTitle()
                    + System.lineSeparator() + "=======================");
            Ui.printList(event1.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That Event does not exist!");
        }
    }

    private void listingMemberDetails() throws DukeException {
        try {
            Event event2 = Duke.eventCatalog.get(eventIndex);
            Task task = event2.getFromTaskList(taskIndex);
            System.out.println("Event: " + event2.getTitle()
                    + System.lineSeparator() + "Task: " + task.getTitle()
                    + System.lineSeparator() + "=======================");
            Ui.printMemberList(task.getMemberList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That Task does not exist!");
        }
    }

    private void checkForEmptyCells(String[] command) {
        for (String check : command) {
            if (!check.equalsIgnoreCase("")) {
               // checkCommand.add(check);
            }
        }
    }
}
