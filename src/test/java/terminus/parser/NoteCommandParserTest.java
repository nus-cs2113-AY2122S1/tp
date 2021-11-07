package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.ExitCommand;
import terminus.command.HelpCommand;
import terminus.command.content.DeleteCommand;
import terminus.command.content.ViewCommand;
import terminus.command.content.note.AddNoteCommand;
import terminus.command.content.note.ExportNoteCommand;
import terminus.command.content.note.ReloadNoteCommand;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public class NoteCommandParserTest {

    private NoteCommandParser commandParser;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.commandParser = NoteCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
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
    void parseCommand_resolveAddCommand_exceptionThrown() {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add \"test1\"test2\""));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test1\" \"test2\""));
    }

    @Test
    void parseCommand_resolveAddCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("add \"test\" \"test1\"") instanceof AddNoteCommand);
        assertTrue(commandParser.parseCommand("add \"username\" \"password\"") instanceof AddNoteCommand);
    }

    @Test
    void parseCommand_resolveDeleteCommand_exceptionThrown() throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete abcd"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete -5"));
    }

    @Test
    void parseCommand_resolveDeleteCommand_success()
        throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("delete 1") instanceof DeleteCommand);
        assertTrue(commandParser.parseCommand("delete 2") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_resolveViewCommand_exceptionThrown()
        throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("view abcd"));
    }

    @Test
    void parseCommand_resolveViewCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("view") instanceof ViewCommand);
        assertTrue(commandParser.parseCommand("view 1") instanceof ViewCommand);
    }

    @Test
    void parseCommand_resolveExportCommand_success()
        throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("export") instanceof ExportNoteCommand);
    }

    @Test
    void parseCommand_resolveReloadCommand_success()
        throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("reload") instanceof ReloadNoteCommand);
    }

    @Test
    void getCommandList_containsBasicCommands() {
        assertTrue(commandParser.getCommandList().contains("exit"));
        assertTrue(commandParser.getCommandList().contains("add"));
        assertTrue(commandParser.getCommandList().contains("back"));
        assertTrue(commandParser.getCommandList().contains("delete"));
        assertTrue(commandParser.getCommandList().contains("view"));
        assertTrue(commandParser.getCommandList().contains("help"));
        assertTrue(commandParser.getCommandList().contains("export"));
        assertTrue(commandParser.getCommandList().contains("reload"));
    }

    @Test
    void getWorkspace_isNote() {
        assertEquals(tempModule + " > note", commandParser.getWorkspace());
    }

    @Test
    void getHelpMenu_isNotEmpty() {
        assertTrue(commandParser.getHelpMenu().length > 0);
    }
}
