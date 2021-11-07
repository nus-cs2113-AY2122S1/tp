package terminus.command.content.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.Messages;
import terminus.common.TestUtils;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;

public class ViewLinkCommandTest {

    Class<Link> type = Link.class;

    private LinkCommandParser linkCommandParser;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.linkCommandParser.setModuleName(tempModule);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_viewNone_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command viewCommand = linkCommandParser.parseCommand("view");
        CommandResult viewResult = viewCommand.execute(moduleManager);
        assertEquals(TestUtils.generateCommandOutputString(viewResult.getMessage()),
            Messages.EMPTY_CONTENT_LIST_MESSAGE.trim());
    }

    @Test
    void execute_viewAll_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                "add \"test\" \"Saturday\" \"00:00\" \"1\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        String stringBuilder = Messages.CONTENT_MESSAGE_HEADER
            + moduleManager.getModule(tempModule).getContentManager(type).listAllContents()
            + Messages.CONTENT_MESSAGE_FOOTER;
        Command viewCommand = linkCommandParser.parseCommand("view");
        CommandResult viewResult = viewCommand.execute(moduleManager);
        assertEquals(stringBuilder, TestUtils.generateCommandOutputString(viewResult.getMessage()));
    }

    @Test
    void execute_viewLink_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                "add \"test\" \"Saturday\" \"00:00\" \"3\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command viewLinkCommand = linkCommandParser.parseCommand("view 1");
        CommandResult viewLinkResult = viewLinkCommand.execute(moduleManager);
        assertTrue(viewLinkResult.isOk());

        viewLinkCommand = linkCommandParser.parseCommand("view 5");
        viewLinkResult = viewLinkCommand.execute(moduleManager);
        assertTrue(viewLinkResult.isOk());
    }

    @Test
    void execute_viewLink_exceptionThrown() throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                "add \"test\" \"Saturday\" \"00:00\" \"2\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(moduleManager);
            assertTrue(addLinkResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("view -1"));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("view X"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("viewwwww"));
    }
}