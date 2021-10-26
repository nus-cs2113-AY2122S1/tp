package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.Duke;
import seedu.duke.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    protected String listType;
    protected String[] userCommand;
    protected ArrayList<String> checkCommand = new ArrayList<>();
    protected String feedback = "--------END OF LIST-----------";

    public ListCommand(String[] command) {
        checkForEmptyCells(command);
        userCommand = command;
        if (checkCommand.size() == 1) {
            this.listType = "list";
        } else if (userCommand.length > 2 && command[2].equalsIgnoreCase("-t")) {
            this.listType = "task";
        } else if (userCommand.length > 2 && command[2].contains("t/")) {
            this.listType = "member";
        } else if (userCommand.length > 1 && command[1].contains("-m")) {
            this.listType = "memberRoster";
        } else if (userCommand.length >= 2 && command[1].equalsIgnoreCase("-t")) {
            this.listType = "EventError";
        } else {
            this.listType = "others";
        }
    }

    public CommandResult execute() {
        try {
            switch (listType) {
            case "list":
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
            event1 = Duke.eventCatalog.get(Integer.parseInt(userCommand[1]) - 1);
            System.out.println("Event: " + event1.getTitle()
                    + System.lineSeparator() + "=======================");
            Ui.printList(event1.getTaskList());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("That Event does not exist!");
        }
    }

    private void listingMemberDetails() throws DukeException {
        try {
            Event event2 = Duke.eventCatalog.get(Integer.parseInt(userCommand[1]) - 1);
            String[] memberCommand = userCommand[2].split("/");
            int taskNum = Integer.parseInt(memberCommand[1]) - 1;
            Task task = event2.getFromTaskList(taskNum);
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
                checkCommand.add(check);
            }
        }
    }
}
