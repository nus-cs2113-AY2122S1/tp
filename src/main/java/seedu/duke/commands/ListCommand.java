package seedu.duke.commands;

import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.Duke;
import seedu.duke.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {
    protected String listType;
    protected String[] userCommand;
    protected ArrayList<String> checkCommand = new ArrayList<>();

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
            default:
                return new CommandResult("please specify type for list "
                        + "[list, list [EVENT_NUM] -t, ], list [Event Index] t/[Task Index]");
            }
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println("Please check through the format carefully");
            Ui.listUsageCommands();
        }
        return new CommandResult("--------END OF LIST-----------");

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
        event1 = Duke.eventCatalog.get(Integer.parseInt(userCommand[1]) - 1);
        System.out.println("Event: " + event1.getTitle()
                + System.lineSeparator() + "=======================");
        Ui.printList(event1.getTaskList());
    }

    private void listingMemberDetails() {
        Event event2 = Duke.eventCatalog.get(Integer.parseInt(userCommand[1]) - 1);
        String[] memberCommand = userCommand[2].split("/");
        int taskNum = Integer.parseInt(memberCommand[1]) - 1;
        Task task = event2.getFromTaskList(taskNum);
        System.out.println("Event: " + event2.getTitle()
                + System.lineSeparator() + "Task: " + task.getTitle()
                + System.lineSeparator() + "=======================");
        Ui.printMemberList(task.getMemberList());
    }

    private void checkForEmptyCells(String[] command) {
        for (String check : command) {
            if (!check.equalsIgnoreCase("")) {
                checkCommand.add(check);
            }
        }
    }
}
