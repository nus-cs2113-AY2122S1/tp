package seedu.duke.commands;

import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.items.Event;
import seedu.duke.items.Task;


public class ListCommand extends Command {
    protected String listType;
    private final int eventIndex;
    private final int taskIndex;
    protected String feedback = "--------END OF LIST-----------";

    public ListCommand(String listType, int eventIndex, int taskIndex) {
        this.listType = listType;
        this.eventIndex = eventIndex;
        this.taskIndex = taskIndex;
    }

    public CommandResult execute() {
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
                    + "list -e: to see overall events"
                    + System.lineSeparator()
                    + "list -t [EVENT_NUM]: to see tasks in an Event"
                    + System.lineSeparator()
                    + "list -m e/[Event Index] t/[Task Index] : to see members in a Task"
                    + System.lineSeparator()
                    + "list -m: to see overall member roster");
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

    private void listingTaskDetails() {
        Event event1;
        event1 = Duke.eventCatalog.get(eventIndex);
        System.out.println("Event: " + event1.getTitle()
                + System.lineSeparator() + "=======================");
        Ui.printList(event1.getTaskList());
    }

    private void listingMemberDetails() {
        Event event2 = Duke.eventCatalog.get(eventIndex);
        Task task = event2.getFromTaskList(taskIndex);
        System.out.println("Event: " + event2.getTitle()
                + System.lineSeparator() + "Task: " + task.getTitle()
                + System.lineSeparator() + "=======================");
        Ui.printMemberList(task.getMemberList());
    }
}
