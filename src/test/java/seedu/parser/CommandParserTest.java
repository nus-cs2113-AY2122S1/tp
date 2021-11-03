package seedu.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.command.Command;
import seedu.command.ExitCommand;
import seedu.command.InvalidCommand;
import seedu.command.SearchCommand;
import seedu.command.ShowCommand;
import seedu.command.TimetableCommand;
import seedu.command.UpdateCommand;
import seedu.command.flags.SearchFlags;
import seedu.timetable.Timetable;

public class CommandParserTest {

    private CommandParser parser;

    @BeforeEach
    public void setup() {
        parser = new CommandParser();
    }

    @Test
    public void parse_EmptyInputs_returnsInvalidCommand() {
        final String[] emptyInputs = {"", " ", "\n", "\n \n"};
        parseMultipleInputsForCommand(new InvalidCommand(), emptyInputs);
    }

    @Test
    public void parse_Update_returnsUpdateCommand() {
        final String[] updateInputs = {"update", " update ", "UPDATE", " UpDaTe"};
        parseMultipleInputsForCommand(new UpdateCommand(), updateInputs);
    }

    @Test
    public void parse_Exit_returnsExitCommand() {
        final String[] exitInputs = {"exit", " exit ", "EXIT", " EXiT"};
        parseMultipleInputsForCommand(new ExitCommand(), exitInputs);
    }

    @Test
    public void parse_Search_returnsSearchCommand() {
        final String[] searchInputs = {"search", " search ", "SEARCH", " seaRCH"};
        parseMultipleInputsForCommand(new SearchCommand("", new SearchFlags()), searchInputs);
    }

    @Test
    public void parse_Show_returnsShowCommand() {
        final String[] showInputs = {"show", " show ", "SHOW", " ShoW"};
        parseMultipleInputsForCommand(new ShowCommand(""), showInputs);
    }

    @Test
    public void parse_Timetable_returnsTimetableCommand() {
        final String[] showInputs = {"timetable", " timetable ", "TIMETABLE", " TIMeTabLe"};
        parseMultipleInputsForCommand(new TimetableCommand(new Timetable(0), false), showInputs);
    }

    private void parseMultipleInputsForCommand(Command expectedCommand, String[] inputs) {
        for (String input : inputs) {
            final Command result = parser.parseCommand(input, new Timetable(1));
            assertEquals(expectedCommand.getClass(), result.getClass());
        }
    }


}
