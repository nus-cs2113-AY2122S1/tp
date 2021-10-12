package seedu.duke;


import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ByeCommand;

import seedu.duke.commands.DoneUndoCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.SelectCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.NextCommand;
import seedu.duke.commands.SelectCommand;
import seedu.duke.commands.UpdateCommand;
import seedu.duke.items.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Parser {
    protected static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    protected static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm");

    public static Command parseCommand(String response) {
        String[] command = response.split(" ", 10);
        switch (command[0]) {
        case "list":
            return new ListCommand(command);
        case "done":
        case "undo":
            return new DoneUndoCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "add":
            return new AddCommand(command, response);
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "find":
            return new FindCommand(command);
        case "select":
            return new SelectCommand(command);
        case "update":
            return new UpdateCommand(command);
        case "next":
            return new NextCommand();
        default:
            System.out.println("Im sorry i did not catch that maybe these instructions below will help"
                    + System.lineSeparator() /*+ ui.lineBreak*/);
            return new HelpCommand();
        }
    }

    public static String convertDateTime(LocalDateTime dateTime) {
        return dateTime.format(formatter2);
    }

    public static LocalDateTime convertDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter1);
    }

    public static String convertDateTimeForSaving(LocalDateTime dateTime) {
        return dateTime.format(formatter1);
    }

    public static void bubbleSortItems(ArrayList<Item> list) {
        for (int j = 0; j < list.size() - 1; j++) {
            for (int i = 0; i < list.size() - j - 1; i++) {
                if (list.get(i + 1).getDateTime().isBefore(list.get(i).getDateTime())) {
                    swap(i, list);
                }
            }
        }
    }

    private static void swap(int i, ArrayList<Item> list) {
        Item t;
        t = list.get(i);
        list.set(i, list.get(i + 1));
        list.set(i + 1, t);
    }

    public static ArrayList<Item> makeMainList() {
        ArrayList<Item> sortedList = new ArrayList<>();
        sortedList.addAll(Duke.eventList);
        sortedList.addAll(Duke.taskList);
        return sortedList;
    }
}
