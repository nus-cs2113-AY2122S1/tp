package seedu.duke;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.ByeCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.SelectCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.UpdateCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.NextCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Parser {
    protected LocalDateTime formattedDate;
    protected static DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d MMM yyyy");

    public static Command parseCommand(String response) {
        String[] command = response.split(" ", 10);
        switch (command[0]) {
        case "list":
            return new ListCommand(command);
        case "delete":
            return new DeleteCommand(command);
        case "add":
            return new AddCommand(command, response);
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
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
        return dateTime.format(formatter1);
    }

    public static LocalDateTime convertDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, formatter1);
    }
}
