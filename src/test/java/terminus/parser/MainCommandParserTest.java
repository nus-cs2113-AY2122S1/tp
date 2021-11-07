package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.ExitCommand;
import terminus.command.GoCommand;
import terminus.command.HelpCommand;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;

public class MainCommandParserTest {

    private MainCommandParser commandParser;

    private ModuleManager moduleManager;
    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.commandParser = MainCommandParser.getInstance();
        moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);

    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("ex it"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("helpa"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand(""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("eXiT a"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("HeLp    a"));
    }

    @Test
    void parseCommand_resolveExitCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("EXIT") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("   exit   ") instanceof ExitCommand);
    }

    @Test
    void parseCommand_resolveHelpCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HELP") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("   help   ") instanceof HelpCommand);
    }

    @Test
    void getCommandList_containsBasicCommands() {
        assertTrue(commandParser.getCommandList().contains("exit"));
        assertTrue(commandParser.getCommandList().contains("help"));
    }

    @Test
    void parseCommand_resolveNoteCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("go " + tempModule + " note") instanceof GoCommand);
        assertTrue(commandParser.parseCommand("go " + tempModule + " NOTE") instanceof GoCommand);
        assertTrue(commandParser.parseCommand("go " + tempModule + "    note   ") instanceof GoCommand);
        assertTrue(commandParser.parseCommand("go " + tempModule + " note    help") instanceof GoCommand);
        assertTrue(commandParser.parseCommand("go " + tempModule + " note    exit") instanceof GoCommand);
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
