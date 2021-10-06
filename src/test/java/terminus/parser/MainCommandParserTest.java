package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.ExitCommand;
import terminus.command.HelpCommand;
import terminus.command.NotesCommand;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public class MainCommandParserTest {
    
    private MainCommandParser commandParser;

    @BeforeEach
    void setUp() {
        this.commandParser = MainCommandParser.getInstance();
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("ex it"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("helpa"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand(""));
    }

    @Test
    void parseCommand_resolveExitCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("EXIT") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("   exit   ") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("eXiT a") instanceof ExitCommand);
    }
    
    @Test
    void parseCommand_resolveHelpCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HELP") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("   help   ") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HeLp    a") instanceof HelpCommand);
    }

    @Test
    void getCommandList_containsBasicCommands() {
        assertTrue(commandParser.getCommandList().contains("exit"));
        assertTrue(commandParser.getCommandList().contains("help"));
    }
    
    @Test
    void parseCommand_resolveNoteCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("note") instanceof NotesCommand);
        assertTrue(commandParser.parseCommand("NOTE") instanceof NotesCommand);
        assertTrue(commandParser.parseCommand("   note   ") instanceof NotesCommand);
        assertTrue(commandParser.parseCommand("note    help") instanceof NotesCommand);
        assertTrue(commandParser.parseCommand("note    exit") instanceof NotesCommand);
    }

    @Test
    void getHelpMenu_isNotEmpty() {
        assertTrue(commandParser.getHelpMenu().length > 0);
    }

    @Test
    void getWorkspace_isEmptyString() {
        assertTrue(commandParser.getWorkspace().isEmpty());
    }
}
