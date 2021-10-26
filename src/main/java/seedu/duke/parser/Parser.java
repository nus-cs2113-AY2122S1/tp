package seedu.duke.parser;


import seedu.duke.Duke;
import seedu.duke.Ui;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ByeCommand;

import seedu.duke.commands.DoneUndoCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.SelectCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.FindCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.NextCommand;
import seedu.duke.commands.UpdateCommand;
import seedu.duke.exceptions.DukeException;
import seedu.duke.items.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class Parser {
    protected static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    protected static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy - HH:mm");
    private static int indexOfLastSelectedEvent = -1;

    public static Command parseCommand(String response) throws DukeException {
        String[] command = response.split(" ");
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
            return new NextCommand(command);
        default:
            throw new DukeException(Ui.getInvalidCommandMessage());
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

    public static ArrayList<Item> makeMainList() {
        ArrayList<Item> sortedList = new ArrayList<>();
        sortedList.addAll(Duke.eventCatalog);
        sortedList.addAll(Duke.taskList);
        return sortedList;
    }

    public static void updateIndexOfLastSelectedEvent(int index) {
        indexOfLastSelectedEvent = index;
    }

    public static int getIndexOfLastSelectedEvent() {
        return indexOfLastSelectedEvent;
    }

    public static void updateIndexToNoEventSelected() {
        updateIndexOfLastSelectedEvent(-1);
    }

    public static boolean noEventSelected() {
        return getIndexOfLastSelectedEvent() == -1;
    }
}
