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

public class ViewLinkCommandTest {

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
    void execute_viewAll_success() throws InvalidCommandException, InvalidArgumentException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"00:00\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(ui, nusModule);
            assertTrue(addLinkResult.isOk());
        }
        assertEquals(5, nusModule.getContentManager(type).getTotalContents());

        Command viewLinkCommand = linkCommandParser.parseCommand("view");
        CommandResult viewLinkResult = viewLinkCommand.execute(ui, nusModule);
        assertTrue(viewLinkResult.isOk());
    }

    @Test
    void execute_viewLink_success() throws InvalidCommandException, InvalidArgumentException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"00:00\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(ui, nusModule);
            assertTrue(addLinkResult.isOk());
        }
        assertEquals(5, nusModule.getContentManager(type).getTotalContents());

        Command viewLinkCommand = linkCommandParser.parseCommand("view 1");
        CommandResult viewLinkResult = viewLinkCommand.execute(ui, nusModule);
        assertTrue(viewLinkResult.isOk());

        viewLinkCommand = linkCommandParser.parseCommand("view 5");
        viewLinkResult = viewLinkCommand.execute(ui, nusModule);
        assertTrue(viewLinkResult.isOk());
    }

    @Test
    void execute_viewLink_exceptionThrown() throws InvalidCommandException, InvalidArgumentException {
        for (int i = 0; i < 5; i++) {
            Command addLinkCommand = linkCommandParser.parseCommand(
                    "add \"test\" \"Saturday\" \"00:00\" \"https://zoom.us/test\"");
            CommandResult addLinkResult = addLinkCommand.execute(ui, nusModule);
            assertTrue(addLinkResult.isOk());
        }
        assertEquals(5, nusModule.getContentManager(type).getTotalContents());

        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("view -1"));
        assertThrows(InvalidArgumentException.class, () -> linkCommandParser.parseCommand("view X"));
        assertThrows(InvalidCommandException.class, () -> linkCommandParser.parseCommand("viewwwww"));
    }
}