package seedu.duke;

import seedu.duke.items.Event;
import seedu.duke.items.Item;
import seedu.duke.items.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String HELP_MESSAGE = "Here's some tips on how to use me!\n\n"
            + "help"
            + "\t - Show a summary of the commands and options that I can handle\n\n"
            + "list"
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
            + "\t - Selects a task given its index in the overall schedule and displays its details\n\n";

    public static String readInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static String getLineBreak() {
        return "______________________________________________________________________";
    }

    public static void printLineBreak() {
        System.out.println("______________________________________________________________________");
    }

    public static void printList(ArrayList<? extends Item> list) {
        for (int i = 0; i < list.size(); i++) {
            Item item = list.get(i);
            int itemIndex = i + 1;
            System.out.println(itemIndex + "." + item);
        }
    }

    public static void promptForDescription() {
        System.out.println("Please add an optional description for your item and press enter.");
        printLineBreak();
    }

    public static String getTaskDeletionMessage(String taskTitle) {
        return String.format("This task has been removed: %s\n", taskTitle);
    }

    public static String getTaskAddedMessage(Task task) {
        return String.format("Task added: %s\n"
                        + "Total number of tasks = %s\n",
                task.getTitle(), Duke.taskList.size());
    }

    public static String getEventAddedMessage(Event event) {
        return String.format("Event added: %s\n"
                        + "Total number of events = %s\n",
                event.getTitle(), Duke.eventList.size());
    }

    public static String getByeMessage() {
        return "You will be missed!!";
    }

    public static void displayUserGuide() {
        System.out.println(HELP_MESSAGE);
    }
}
