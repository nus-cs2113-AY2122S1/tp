package terminus.command.content.link;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AddLinkCommandTest {

    private LinkCommandParser linkCommandParser;
    private ModuleManager moduleManager;
    private Ui ui;

    private String tempModule = "test";
    Class<Link> type = Link.class;

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.linkCommandParser.setModuleName(tempModule);
        this.ui = new Ui();
    }

    @Test
    void parseArguments_addLinkCommand_success() {
        String addLinkInput = "add \"test\" \"Thursday\" \"00:00\" \"2\"\"https://zoom.us/test\"";
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
        CommandResult addResult = addLinkCommand.execute(ui, moduleManager);
        assertTrue(addResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1).contains("test"));
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1).contains("Monday"));
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1).contains("00:00"));
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1).contains("2"));
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1).contains("https://zoom.us/test"));

        Link newLink = new Link("test conflict", "Saturday", LocalTime.of(9, 00), 3, "https://zoom.us/test");
        ConflictManager conflictManager = new ConflictManager(moduleManager, newLink);

        for (int i = 0; i < 5; i++) {
            addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"10:00\" \"2\" \"https://zoom.us/test\"");
            addResult = addLinkCommand.execute(ui, moduleManager);
            assertTrue(addResult.isOk());
            assertNotNull(conflictManager.getConflictingSchedule());
        }
        assertEquals(6, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }
}