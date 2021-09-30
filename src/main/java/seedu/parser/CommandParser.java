package seedu.parser;

import seedu.command.*;
import seedu.duke.Duke;

public class CommandParser {

    private static final int SEARCH_LENGTH = 6;
    private static final int SHOW_LENGTH = 4;

    public Command parseCommand(String text) {
        Command command;
        text = text.trim();
        String lowerCaseText = text.toLowerCase();
        if (text.equalsIgnoreCase("exit")) {
            command = new ExitCommand();
        } else if (lowerCaseText.startsWith("update")) {
            command = new UpdateCommand();
        } else if (lowerCaseText.startsWith("search")) {
            command = parseSearchCommand(text);
        } else if (lowerCaseText.startsWith("show")) {
            command = parseShowCommand(text);
        } else if (lowerCaseText.startsWith("timetable")) {
            command = new TimetableCommand(Duke.timetable);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    public Command parseSearchCommand(String text) {
        String str = text.substring(SEARCH_LENGTH).trim();
        return new SearchCommand(str);
    }

    public Command parseShowCommand(String text) {
        String str = text.substring(SHOW_LENGTH).trim();
        return new ShowCommand(str);
    }

}
