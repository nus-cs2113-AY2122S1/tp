package seedu.duke;

import seedu.duke.items.Event;
import seedu.duke.items.Task;
import seedu.duke.items.characteristics.Member;
import seedu.duke.parser.Parser;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Ui {
    private static final String HELP_MESSAGE = "Here's some tips on how to use me!\n\n"
            + "help\n"
            + "\t - Show a summary of the commands and options that I can handle\n\n"
            + "list\n"
            + "\t - Lists all the events and tasks currently in the schedule\n"
            + "\t - An additional -e or -t flag can be appended to list either events or tasks only\n"
            + "\t - E.g. list -e OR list -t\n\n"
            + "add -e n/TITLE d/DATE v/VENUE b/BUDGET\n"
            + "\t - Add an event to the schedule\n\n"
            + "delete -e n/INDEX\n"
            + "\t - Deletes an event given its index in the overall schedule\n\n"
            + "select -e n/INDEX\n"
            + "\t - Selects an event given its index in the overall schedule and displays its details\n\n"
            + "add -t n/TITLE d/DATE\n"
            + "\t - Add a task to the schedule\n\n"
            + "delete -t n/INDEX\n"
            + "\t - Deletes a task given its index in the overall schedule\n\n"
            + "select -t n/INDEX\n"
            + "\t - Selects a task given its index in the overall schedule and displays its details\n\n"
            + "update -e OR -t\n"
            + "\t - Displays the respective list of events or tasks that you can choose and update";

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        String userInput = "";
        if (in.hasNextLine()) {
            userInput = in.nextLine();
        }
        return userInput;
    }

    public static String getLineBreak() {
        return "______________________________________________________________________";
    }

    public static void printLineBreak() {
        System.out.println("______________________________________________________________________");
    }

    public static void promptForDescription() {
        System.out.println("Please add an optional description for your item and press enter.");
        printLineBreak();
    }

    public static void promptForEventIndex() {
        System.out.println("Please choose which event you want to add your task to. ");
    }

    public static void promptForMemberIndex() {
        System.out.println("Please choose which member you want to assign your task to. ");
    }

    public static String getTaskDeletionMessage(String taskTitle) {
        return String.format("This task has been removed: %s\n", taskTitle);
    }

    public static String getEventDeletionMessage(String eventTitle) {
        return String.format("This event has been removed: %s\n", eventTitle);
    }

    public static String getMemberDeletionMessage(String name) {
        return String.format("This member has been removed: %s\n", name);
    }

    public static String getTaskAddedMessage(int eventIndex, Task task) {
        assert eventIndex < Duke.eventCatalog.size() : "Number entered cannot be more than "
                + "number of events";
        return String.format("Task added: %s\n"
                        + "Total number of tasks in this event = %s",
                task.getTitle(), Duke.eventCatalog.get(eventIndex - 1).getTaskList().size());
    }

    public static String getEventAddedMessage(Event event) {
        return String.format("Event added: %s\n"
                        + "Total number of events = %s",
                event.getTitle(), Duke.eventCatalog.size());
    }

    public static String getMemberAddedMessage(Member member) {
        return String.format("Member added: %s\n", member.getName());
    }

    public static void printGreetingMessage() {
        System.out.println("Greetings mortal. How may you be served today?\n"
                + "TIP: enter \"help\" if you are weak and clueless!\n"
                + getLineBreak());
    }

    public static String getByeMessage() {
        return "You will be missed!!";
    }

    public static String getHelpMessage() {
        return HELP_MESSAGE;
    }

    public static String getSelectedTaskMessage(Task task) {
        return getLineBreak() + System.lineSeparator() + "Here are the details of the task:" + System.lineSeparator()
                + getTask(task) + System.lineSeparator() + getLineBreak();
    }

    public static String getSelectedEventMessage(Event event) {
        return getLineBreak() + System.lineSeparator() + "Here are the details of the event:" + System.lineSeparator()
                + getEvent(event) + System.lineSeparator() + getLineBreak();
    }


    public static <T> void printList(ArrayList<T> list) {
        AtomicInteger i = new AtomicInteger();
        list.forEach(item -> System.out.println(i.getAndIncrement() + 1 + ". " + item));
    }

    public static void printEventCatalog() {
        printList(Duke.eventCatalog);
    }

    public static void printMemberRoster() {
        printList(Duke.memberRoster);
    }

    public static String getTask(Task task) {
        return "Title: " + task.getTitle() + System.lineSeparator()
                + "Deadline: " + Parser.convertDateTime(task.getDateTime()) + System.lineSeparator()
                + "Description: " + task.getDescription() + System.lineSeparator()
                + "Members: " + System.lineSeparator() + task.getMemberListAsString();
    }

    public static String getEvent(Event event) {
        return "Title: " + event.getTitle() + System.lineSeparator()
                + "Date: " + Parser.convertDateTime(event.getDateTime()) + System.lineSeparator()
                + "Description: " + event.getDescription() + System.lineSeparator()
                + "Venue: " + event.getVenue() + System.lineSeparator()
                + "Budget: $" + event.getBudget() + System.lineSeparator()
                + "Tasks: " + System.lineSeparator() + event.getTaskListAsString();
    }

    public static void printTask(Task task) {
        System.out.println("Title: " + task.getTitle() + System.lineSeparator()
                + "Deadline: " + Parser.convertDateTime(task.getDateTime()) + System.lineSeparator()
                + "Description: " + task.getDescription() + System.lineSeparator()
                + "Members: ");
        printMemberList(task.getMemberList());
    }

    public static void printEvent(Event event) {
        System.out.println("Title: " + event.getTitle() + System.lineSeparator()
                + "Date: " + Parser.convertDateTime(event.getDateTime()) + System.lineSeparator()
                + "Description: " + event.getDescription() + System.lineSeparator()
                + "Venue: " + event.getVenue() + System.lineSeparator()
                + "Budget: $" + event.getBudget() + System.lineSeparator()
                + "Tasks: ");
        printList(event.getTaskList());
    }

    public static void printMemberList(ArrayList<Member> list) {
        AtomicInteger i = new AtomicInteger();
        list.forEach(member -> System.out.println(i.getAndIncrement() + 1 + ". " + member));
    }

    public static void listUsageCommands() {
        System.out.println(System.lineSeparator() + "FURTHER COMMANDS"
                + System.lineSeparator() + "-----------------------"
                + System.lineSeparator() + "To list Task: list [Event Index] -t"
                + System.lineSeparator() + "To list Members of a Task: "
                + "list [Event Index] t/[Task Index]");
    }

    public static void updateIntroMessage() {
        System.out.println("Please type the item you would like to update in the following manner "
                + System.lineSeparator() + "-----------------------------------------------------------------------   "
                + System.lineSeparator() + "title/[NEW NAME]   "
                + System.lineSeparator() + "date/[NEW DATE[d/dd-MM-yyyy HHmm]]"
                + System.lineSeparator() + "description/[NEW DESCRIPTION]"
                + System.lineSeparator() + "venue/[NEW VENUE]"
                + System.lineSeparator() + "budget/[NEW BUDGET]"
                + System.lineSeparator() + "task/[TASK NUM YOU WANT TO UPDATE]"
                + System.lineSeparator()
                + "You may type more then one update at a given time but separate them with a [>]"
                + System.lineSeparator() + Ui.getLineBreak());
    }

}
