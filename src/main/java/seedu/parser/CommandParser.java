package seedu.parser;

import seedu.command.Command;
import seedu.command.ExitCommand;
import seedu.command.InvalidCommand;
import seedu.command.SearchCommand;

public class CommandParser {

    private static final int SEARCH_LENGTH = 6;

    public Command parseCommand(String text) {
        Command command;
        text = text.trim();
        String lowerCaseText = text.toLowerCase();
        if (text.equalsIgnoreCase("exit")) {
            command = new ExitCommand();
        } else if (lowerCaseText.startsWith("search")) {
            command = parseSearchCommand(text);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    public Command parseSearchCommand(String text) {
        String str = text.substring(SEARCH_LENGTH).trim();
        return new SearchCommand(str);
    }
}
