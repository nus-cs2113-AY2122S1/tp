package seedu.parser;

import seedu.command.*;
import seedu.duke.Duke;
import seedu.timetable.Timetable;

public class CommandParser {
    private static final Integer SEARCH_LENGTH = 6;
    private static final Integer SHOW_LENGTH = 4;
    private static final Integer ADD_LENGTH = 3;
    private static final String FLAG = "-";
    private static final String L_FLAG = "-l";

    public Command parseCommand(String text, Timetable timetable) {
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
        } else if (lowerCaseText.startsWith("add")) {
            command = parseAddCommand(text, timetable);
        } else if (lowerCaseText.startsWith("help")) {
            command = new HelpCommand();
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    public Command parseSearchCommand(String text) {
        if (text.toLowerCase().contains(FLAG)) {
            return parseSearchCommandWithFlag(text);
        }
        String str = text.substring(SEARCH_LENGTH).trim();
        return new SearchCommand(str, false);
    }

    public Command parseSearchCommandWithFlag(String text) {
        int flagPos = text.indexOf(FLAG);
        try {
            String str = text.substring(SEARCH_LENGTH, flagPos - 1).trim();
            if (text.toLowerCase().contains(L_FLAG)) {
                return new SearchCommand(str, true);
            }
        } catch (IndexOutOfBoundsException e) {
            return new InvalidCommand();
        }
        return new InvalidCommand();
    }

    public Command parseShowCommand(String text) {
        String str = text.substring(SHOW_LENGTH).trim();
        return new ShowCommand(str);
    }

    public Command parseAddCommand(String input, Timetable timetable) {
        input = input.substring(ADD_LENGTH).trim();
        String moduleCode = input.toUpperCase();
        return new AddCommand(moduleCode, timetable);
    }
}
