package terminus.command.content.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonUtils;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;
import terminus.timetable.ConflictManager;
import terminus.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddLinkCommandTest {

    Class<Link> type = Link.class;
    private LinkCommandParser linkCommandParser;
    private ModuleManager moduleManager;
    private Ui ui;
    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.linkCommandParser.setModuleName(tempModule);
    }

    @Test
    void parseArguments_addLinkCommand_success() {
        String addLinkInput = "\"test\" \"Thursday\" \"00:00\" \"2\" \"https://zoom.us/test\"";
        ArrayList<String> parsedArguments = CommonUtils.findArguments(addLinkInput);
        assertEquals("test", parsedArguments.get(0));
        assertEquals("Thursday", parsedArguments.get(1));
        assertEquals("00:00", parsedArguments.get(2));
        assertEquals("2", parsedArguments.get(3));
        assertEquals("https://zoom.us/test", parsedArguments.get(4));
    }

    @Test
    void execute_addLinkCommand_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        Command addLinkCommand = linkCommandParser.parseCommand("add \"test\" \"Monday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
        CommandResult addResult = addLinkCommand.execute(moduleManager);
        assertTrue(addResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
        String link = moduleManager.getModule(tempModule).getContentManager(type).getContentData(1);
        assertTrue(link.contains("test"));
        assertTrue(link.contains("Monday"));
        assertTrue(link.contains("00:00"));
        assertTrue(link.contains("2"));
        assertTrue(link.contains("https://zoom.us/test"));

        Link newLink = new Link("test conflict", "Saturday", LocalTime.of(9, 00), 3, "https://zoom.us/test");
        ConflictManager conflictManager = new ConflictManager(moduleManager, newLink);


        for (int i = 0; i < 5; i++) {
            addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"10:00\" \"2\" \"https://zoom.us/test\"");
            addResult = addLinkCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
            assertNotNull(conflictManager.getConflictingSchedule());
        }
        assertEquals(6, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }

    @Test
    void execute_addLinkCommand_fail() {
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"23:00\" \"12\" \"https://zoom.us/test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"23:00\" \"12\" \"zoom.test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"00:00\" \"25\" \"https://zoom.us/test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"00:00\" \"2.5\" \"https://zoom.us/test\""));
        assertThrows(InvalidArgumentException.class,
            () -> linkCommandParser.parseCommand("add \"test desc\" \"friday\" \"00:00\" \"a\" \"https://zoom.us/test\""));
    }
}