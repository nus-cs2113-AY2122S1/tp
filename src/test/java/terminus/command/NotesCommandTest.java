package terminus.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Link;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.LinkCommandParser;
import terminus.parser.MainCommandParser;
import terminus.ui.Ui;

public class NotesCommandTest {

    private MainCommandParser commandParser;
    private Ui ui;
    private NusModule nusModule;

    @BeforeEach
    void setUp() {
        commandParser = MainCommandParser.getInstance();
        ui = new Ui();
        nusModule = new NusModule();
    }

    @Test
    void execute_linkAdvance_success() throws InvalidArgumentException, InvalidCommandException {
        Command mainCommand = commandParser.parseCommand("schedule");
        CommandResult changeResult = mainCommand.execute(ui, nusModule);
        assertTrue(changeResult.isOk());
        assertTrue(changeResult.getAdditionalData() instanceof LinkCommandParser);
        mainCommand = commandParser.parseCommand("schedule add \"test\" \"Thursday\" \"00:00\" \"https://zoom.us\"");
        changeResult = mainCommand.execute(ui, nusModule);
        assertTrue(changeResult.isOk());
        assertEquals(1, nusModule.getContentManager(Link.class).getTotalContents());
        mainCommand = commandParser.parseCommand("schedule view");
        changeResult = mainCommand.execute(ui, nusModule);
        assertTrue(changeResult.isOk());
    }

    @Test
    void execute_linkAdvance_throwsException() throws InvalidArgumentException, InvalidCommandException {
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("schedule -1").execute(ui, nusModule));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("schedule add \"test\" \"Thursday\" \"00:00\" \"test.com\"")
                    .execute(ui, nusModule));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("schedule delete -1").execute(ui, nusModule));

    }
}
