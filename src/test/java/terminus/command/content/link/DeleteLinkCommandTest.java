package terminus.command.content.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.LinkCommandParser;

public class DeleteLinkCommandTest {

    Class<Link> type = Link.class;
    private LinkCommandParser linkCommandParser;
    private ModuleManager moduleManager;
    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.linkCommandParser.setModuleName(tempModule);
    }

    @Test
    void execute_deleteLink_success()
            throws InvalidCommandException, InvalidArgumentException, IOException {
        for (int i = 0; i < 3; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                "add \"test_desc\" \"Monday\" \"12:00\" \"1\" \"https://zoom.us/test\""
            );
            CommandResult addResult = addLinkCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }

        assertEquals(3, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command deleteLinkCommand = linkCommandParser.parseCommand("delete 1");
        CommandResult deleteResult = deleteLinkCommand.execute(moduleManager);
        assertTrue(deleteResult.isOk());
        assertEquals(2, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        for (int j = 0; j < 2; j++) {
            deleteLinkCommand = linkCommandParser.parseCommand("delete 1");
            deleteResult = deleteLinkCommand.execute(moduleManager);
            assertTrue(deleteResult.isOk());
        }
        assertEquals(0, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }

    @Test
    void execute_deleteLink_throwsException()
            throws InvalidCommandException, InvalidArgumentException {
        Command deleteLinkCommand = linkCommandParser.parseCommand("delete 20");
        assertThrows(InvalidArgumentException.class, () -> deleteLinkCommand.execute(moduleManager));
    }
}