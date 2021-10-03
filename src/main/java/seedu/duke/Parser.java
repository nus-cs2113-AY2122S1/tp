package seedu.duke;

import seedu.duke.commands.*;

public class Parser {

    public static Command parseCommand(String response) {
        String[] command = response.split(" ", 10);
        switch (command[0]) {
        case "list":
            return new ListCommand(command);
        case "delete":
            return new DeleteCommand();
        case "add":
            return new AddCommand();
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "select":
            return new SelectCommand();
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
}
