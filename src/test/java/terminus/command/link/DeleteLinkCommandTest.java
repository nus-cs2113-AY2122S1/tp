package terminus.command.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.LinkCommandParser;
import terminus.ui.Ui;

public class DeleteLinkCommandTest {

    private LinkCommandParser linkCommandParser;
    private NusModule nusModule;
    private Ui ui;

    Class<Link> type = Link.class;

    @BeforeEach
    void setUp() {
        this.linkCommandParser = LinkCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void execute_deleteLink_success() throws InvalidCommandException, InvalidArgumentException {
        for (int i = 0; i < 3; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand("add \"test_desc\" \"Monday\" \"12:00\" \"https://zoom.us/test\"");
            CommandResult addResult = addLinkCommand.execute(ui, nusModule);
            assertTrue(addResult.isOk());
        }

        assertEquals(3, nusModule.getContentManager(type).getTotalContents());

        Command deleteLinkCommand = linkCommandParser.parseCommand("delete 1");
        CommandResult deleteResult = deleteLinkCommand.execute(ui, nusModule);
        assertTrue(deleteResult.isOk());
        assertEquals(2, nusModule.getContentManager(type).getTotalContents());

        for (int j = 0; j < 2; j++) {
            deleteLinkCommand = linkCommandParser.parseCommand("delete 1");
            deleteResult = deleteLinkCommand.execute(ui, nusModule);
            assertTrue(deleteResult.isOk());
        }
        assertEquals(0, nusModule.getContentManager(type).getTotalContents());
    }

    @Test
    void execute_deleteLink_throwsException() throws InvalidCommandException, InvalidArgumentException {
        Command deleteLinkCommand = linkCommandParser.parseCommand("delete 20");
        assertThrows(InvalidArgumentException.class, () -> deleteLinkCommand.execute(ui, nusModule));
    }
}