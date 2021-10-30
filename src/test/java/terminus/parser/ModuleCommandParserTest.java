package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.ExitCommand;
import terminus.command.HelpCommand;
import terminus.command.module.AddModuleCommand;
import terminus.command.module.DeleteModuleCommand;
import terminus.command.module.UpdateModuleCommand;
import terminus.command.module.ViewModuleCommand;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public class ModuleCommandParserTest {

    private ModuleCommandParser commandParser;

    @BeforeEach
    void setUp() {
        commandParser = ModuleCommandParser.getInstance();
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("ex it"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("helpa"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("adda"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("vie wer"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand("deleterr"));
        assertThrows(InvalidCommandException.class, () -> commandParser.parseCommand(""));
    }

    @Test
    void parseCommand_resolveExitCommand_success()
        throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("EXIT") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("   exit   ") instanceof ExitCommand);
        assertTrue(commandParser.parseCommand("eXiT a") instanceof ExitCommand);
    }

    @Test
    void parseCommand_resolveHelpCommand_success()
        throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HELP") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("   help   ") instanceof HelpCommand);
        assertTrue(commandParser.parseCommand("HeLp    a") instanceof HelpCommand);
    }

    @Test
    void parseCommand_resolveAddCommand_exceptionThrown()
        throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add"));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test1\" "));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add \"    test     \" "));
    }

    @Test
    void parseCommand_resolveAddCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("add \"test\" ") instanceof AddModuleCommand);
        assertTrue(commandParser.parseCommand("add \"username\"") instanceof AddModuleCommand);
    }

    @Test
    void parseCommand_resolveDeleteCommand_exceptionThrown() throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete abcd"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("delete -5"));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add \"test1\"test2\""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("add \"test1\" \"test2\""));
    }

    @Test
    void parseCommand_resolveDeleteCommand_success()
        throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("delete 1") instanceof DeleteModuleCommand);
        assertTrue(commandParser.parseCommand("delete 2") instanceof DeleteModuleCommand);
    }

    @Test
    void parseCommand_resolveViewCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("view") instanceof ViewModuleCommand);
    }

    @Test
    void parseCommand_resolveUpdateCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(commandParser.parseCommand("update 1 \"test\"") instanceof UpdateModuleCommand);
        assertTrue(commandParser.parseCommand("update 99 \"CS2106\"") instanceof UpdateModuleCommand);
    }

    @Test
    void parseCommand_resolveUpdateCommand_thrownException() throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update abcd \"test\""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update -1 \"test\""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update 1 \"    test    \""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update 1 \"\""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update -1 \"     \""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update -1 "));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update \"test\""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update "
            +
            "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111"
            + "1111111111111 \"test\""));
        assertThrows(InvalidArgumentException.class, () -> commandParser.parseCommand("update"));
    }

    @Test
    void getCommandList_containsBasicCommands() {
        assertTrue(commandParser.getCommandList().contains("exit"));
        assertTrue(commandParser.getCommandList().contains("add"));
        assertTrue(commandParser.getCommandList().contains("back"));
        assertTrue(commandParser.getCommandList().contains("delete"));
        assertTrue(commandParser.getCommandList().contains("view"));
        assertTrue(commandParser.getCommandList().contains("help"));
        assertTrue(commandParser.getCommandList().contains("update"));

    }

    @Test
    void getWorkspace_isModule() {
        assertEquals("module", commandParser.getWorkspace());
    }

    @Test
    void getHelpMenu_isNotEmpty() {
        assertTrue(commandParser.getHelpMenu().length > 0);
    }

}
