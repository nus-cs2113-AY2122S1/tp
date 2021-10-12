package terminus.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.DeleteCommand;
import terminus.command.ExitCommand;
import terminus.command.HelpCommand;
import terminus.command.ViewCommand;
import terminus.command.zoomlink.AddLinkCommand;
import terminus.exception.InvalidLinkException;
import terminus.exception.InvalidTimeFormatException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidDayException;
import terminus.module.NusModule;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkCommandParserTest {

    private LinkCommandParser linkCommandParser;
    private NusModule nusModule;

    @BeforeEach
    void setUp() {
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.nusModule = new NusModule();
    }

    @Test
    void parseCommand_invalidCommand_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("exitt"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("helpp"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("aadddd"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("vieww"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("dellett"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand(""));
    }

    @Test
    void parseCommand_resolveExitCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(linkCommandParser.parseCommand("exit") instanceof ExitCommand);
        assertTrue(linkCommandParser.parseCommand("eXiT") instanceof ExitCommand);
        assertTrue(linkCommandParser.parseCommand("   ExIt   ") instanceof ExitCommand);
        assertTrue(linkCommandParser.parseCommand("eXiT tt") instanceof ExitCommand);
    }

    @Test
    void parseCommand_resolveHelpCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(linkCommandParser.parseCommand("help") instanceof HelpCommand);
        assertTrue(linkCommandParser.parseCommand("HeLp") instanceof HelpCommand);
        assertTrue(linkCommandParser.parseCommand("   hElP   ") instanceof HelpCommand);
        assertTrue(linkCommandParser.parseCommand("HeLp pppp") instanceof HelpCommand);
    }

    @Test
    void parseCommand_resolveAddCommand_InvalidArgumentExceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("add"));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\"test day\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"test day\" \"00:00\""));
    }

    @Test
    void parseCommand_resolveAddCommand_InvalidTimeFormatExceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidTimeFormatException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Friday\" \"0:0\" \"https://zoom.us/test\""));
        assertThrows(InvalidTimeFormatException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Saturday\" \"0:0X\" \"https://zoom.us/test\""));
        assertThrows(InvalidTimeFormatException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Sunday\" \"0\" \"https://zoom.us/test\""));
        assertThrows(InvalidTimeFormatException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Tuesday\" \"14:0\" \"https://zoom.us/test\""));
    }

    @Test
    void parseCommand_resolveAddCommand_InvalidLinkExceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidLinkException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Friday\" \"10:00\" \"zoom.com\""));
        assertThrows(InvalidLinkException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Saturday\" \"12:30\" \"zoom.sg\""));
        assertThrows(InvalidLinkException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Sunday\" \"09:00\" \"invalidlink\""));
        assertThrows(InvalidLinkException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Tuesday\" \"14:00\" \"invalid link.com\""));
    }

    @Test
    void parseCommand_resolveAddCommand_InvalidDayExceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidDayException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Today\" \"10:00\" \"https://zoom.us/test\""));
        assertThrows(InvalidDayException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Yesterday\" \"10:00\" \"https://zoom.us/test\""));
        assertThrows(InvalidDayException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"Everyday\" \"10:00\" \"https://zoom.us/test\""));
        assertThrows(InvalidDayException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"whenever\" \"10:00\" \"https://zoom.us/test\""));
    }

    @Test
    void parseCommand_resolveAddCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(linkCommandParser.parseCommand(
                "add \"test desc\" \"Tuesday\" \"10:00\" \"https://zoom.us/test\"") instanceof AddLinkCommand);
        assertTrue(linkCommandParser.parseCommand(
                "add \"    test     \" \"Wednesday\" \"10:00\" \"    https://zoom.us/test    \"") instanceof AddLinkCommand);
        assertTrue(linkCommandParser.parseCommand(
                "add \"CS2113T Lecture\" \"Friday\" \"16:00\" \"https://zoom.us/test\"") instanceof AddLinkCommand);
    }

    @Test
    void parseCommand_resolveDeleteCommand_exceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("delete"));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("delete abcd"));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("delete -1"));
    }

    @Test
    void parseCommand_resolveDeleteCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
        assertTrue(linkCommandParser.parseCommand("delete 1") instanceof DeleteCommand);
        assertTrue(linkCommandParser.parseCommand("delete 2") instanceof DeleteCommand);
    }

    @Test
    void parseCommand_resolveViewCommand_exceptionThrown()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("view abcd"));
    }

    @Test
    void parseCommand_resolveViewCommand_success()
            throws InvalidCommandException, InvalidArgumentException,
            InvalidTimeFormatException, InvalidLinkException, InvalidDayException {
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
        assertEquals("schedule", linkCommandParser.getWorkspace());
    }

    @Test
    void getHelpMenu_isNotEmpty() {
        assertTrue(linkCommandParser.getHelpMenu().length > 0);
    }
}