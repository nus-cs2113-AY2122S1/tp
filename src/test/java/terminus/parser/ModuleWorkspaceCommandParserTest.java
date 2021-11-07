package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.ExitCommand;
import terminus.command.HelpCommand;
import terminus.command.content.NotesCommand;
import terminus.command.content.ScheduleCommand;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public class ModuleWorkspaceCommandParserTest {

    private ModuleWorkspaceCommandParser commandParser;

    @BeforeEach
    void setUp() {
        commandParser = ModuleWorkspaceCommandParser.getInstance();
        commandParser.setWorkspace("2106");
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("ex it"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("helpa"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("adda"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("vie wer"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("deleterr"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand(""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("eXiT a"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("HeLp    a"));
    }

    @Test
    void parseCommand_resolveExitCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("EXIT") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("   exit   ") instanceof ExitCommand);
    }

    @Test
    void parseCommand_resolveHelpCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HELP") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("   help   ") instanceof HelpCommand);
    }

    @Test
    void parseCommand_resolveNoteCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("note") instanceof NotesCommand);
        assertTrue(commandParser.parseCommand("note add \"test\" \"test\"") instanceof NotesCommand);
        assertTrue(commandParser.parseCommand("note view") instanceof NotesCommand);
        assertTrue(commandParser.parseCommand("note delete 1") instanceof NotesCommand);
    }

    @Test
    void parseCommand_resolveScheduleCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("schedule") instanceof ScheduleCommand);
        assertTrue(commandParser.parseCommand(
                "schedule add \"test desc\" \"Tuesday\" \"10:00\" \"https://zoom.us/test\"")
                instanceof ScheduleCommand);
        assertTrue(commandParser.parseCommand("schedule view") instanceof ScheduleCommand);
        assertTrue(commandParser.parseCommand("schedule delete 1") instanceof ScheduleCommand);
    }
}
