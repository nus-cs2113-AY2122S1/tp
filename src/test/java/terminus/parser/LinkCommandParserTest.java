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
import terminus.command.content.link.AddLinkCommand;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;

public class LinkCommandParserTest {

    private LinkCommandParser linkCommandParser;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.linkCommandParser.setModuleName(tempModule);
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("exitt"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("helpp"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("aadddd"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("vieww"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("dellett"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand(""));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("eXiT tt"));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("HeLp pppp"));
    }

    @Test
    void parseCommand_resolveExitCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(linkCommandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(linkCommandParser.parseCommand("eXiT") instanceof ExitCommand);
        assertTrue(linkCommandParser.parseCommand("   ExIt   ") instanceof ExitCommand);
    }

    @Test
    void parseCommand_resolveHelpCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(linkCommandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(linkCommandParser.parseCommand("HeLp") instanceof HelpCommand);
        assertTrue(linkCommandParser.parseCommand("   hElP   ") instanceof HelpCommand);
    }

    @Test
    void parseCommand_resolveAddCommand_InvalidArgumentExceptionThrown()
            throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("add"));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\"test day\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"test day\" \"00:00\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"today\" \"00:00\" \"2\" \"https://zoom.us/test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"monday\" \"x:30\" \"3\" \"https://zoom.us/test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"10:00\" \"1\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Monday\" \"10:00\" \"1\" \"empty\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"10:00\" \"1\" \"zoom\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"10:00\" \"-1\" \"zoom.test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"23:00\" \"25\" \"zoom.test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"23:00\" \"12\" \"zoom.test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"23:00\" \"12\" \"zoom.test\""));

    }

    @Test
    void parseCommand_resolveAddCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(linkCommandParser.parseCommand(
                "add \"test desc\" \"Tuesday\" \"10:00\" \"1\" \"https://zoom.us/test\"") instanceof AddLinkCommand);
        assertTrue(linkCommandParser.parseCommand(
                "add \"    test     \" \"Wednesday\" \"10:00\" \"2\" \"    https://zoom.us/test    \"") instanceof AddLinkCommand);
        assertTrue(linkCommandParser.parseCommand(
                "add \"CS2113T Lecture\" \"Friday\" \"16:00\" \"3\" \"https://zoom.us/test\"") instanceof AddLinkCommand);
    }

    @Test
    void parseCommand_resolveDeleteCommand_exceptionThrown()
            throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("delete"));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("delete abcd"));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("delete -1"));
    }

    @Test
    void parseCommand_resolveDeleteCommand_success()
            throws InvalidCommandException, InvalidArgumentException {
        assertTrue(linkCommandParser.parseCommand("delete 1") instanceof DeleteCommand);
        assertTrue(linkCommandParser.parseCommand("delete 2") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_resolveViewCommand_exceptionThrown() throws InvalidCommandException, InvalidArgumentException {
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("view abcd"));
    }

    @Test
    void parseCommand_resolveViewCommand_success() throws InvalidCommandException, InvalidArgumentException {
        assertTrue(linkCommandParser.parseCommand("view") instanceof ViewCommand);
        assertTrue(linkCommandParser.parseCommand("view 1") instanceof ViewCommand);
    }

    @Test
    void getCommandList_containsBasicCommands() {
        assertTrue(linkCommandParser.getCommandList().contains("exit"));
        assertTrue(linkCommandParser.getCommandList().contains("add"));
        assertTrue(linkCommandParser.getCommandList().contains("back"));
        assertTrue(linkCommandParser.getCommandList().contains("delete"));
        assertTrue(linkCommandParser.getCommandList().contains("view"));
        assertTrue(linkCommandParser.getCommandList().contains("help"));
    }

    @Test
    void getWorkspace_isSchedule() {
        assertEquals(tempModule + " > schedule", linkCommandParser.getWorkspace());
    }

    @Test
    void getHelpMenu_isNotEmpty() {
        assertTrue(linkCommandParser.getHelpMenu().length > 0);
    }
}