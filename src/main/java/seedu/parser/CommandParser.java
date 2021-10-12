package seedu.parser;


import seedu.command.AddCommand;
import seedu.command.ClearCommand;
import seedu.command.Command;
import seedu.command.DeleteCommand;
import seedu.command.ExitCommand;
import seedu.command.HelpCommand;
import seedu.command.InvalidCommand;
import seedu.command.SearchCommand;
import seedu.command.ShowCommand;
import seedu.command.TimetableCommand;
import seedu.command.UpdateCommand;
import seedu.duke.Duke;
import seedu.timetable.Timetable;

public class CommandParser {
    private static final Integer SEARCH_LENGTH = 6;
    private static final Integer SHOW_LENGTH = 4;
    private static final Integer ADD_LENGTH = 3;
    public static final Integer DELETE_LENGTH = 6;
    private static final String FLAG = "-";


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
        } else if (lowerCaseText.startsWith("delete")) {
            command = parseDeleteCommand(text, timetable);
        } else if (lowerCaseText.startsWith("clear")) {
            command = parseClearCommand(timetable);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }

    public Command parseDeleteCommand(String text, Timetable timetable) {
        String moduleToBeDeleted = (text.substring(DELETE_LENGTH).trim()).toUpperCase();
        return new DeleteCommand(moduleToBeDeleted, timetable);
    }

    public Command parseClearCommand(Timetable timetable) {
        return new ClearCommand(timetable);
    }

    public Command parseSearchCommand(String text) {
        if (text.toLowerCase().contains(FLAG)) {
            return parseSearchCommandWithFlag(text);
        }
        assert !text.contains(FLAG) : "This line should not be hit with a flag.";
        String searchTerm = text.substring(SEARCH_LENGTH).trim();
        return new SearchCommand(searchTerm, new SearchFlags());
    }

    private Command parseSearchCommandWithFlag(String text) {
        assert text.contains(FLAG) : "String should contain flag";
        int firstFlagPos = text.indexOf(FLAG);
        String searchTerm = text.substring(SEARCH_LENGTH, firstFlagPos - 1).trim();
        String[] flags = text.substring(firstFlagPos).split(FLAG);
        SearchFlags searchFlags = FlagParser.parseSearchFlags(flags);
        return new SearchCommand(searchTerm, searchFlags);
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
