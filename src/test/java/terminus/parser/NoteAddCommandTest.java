package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidTimeFormatException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class NoteAddCommandTest {
    private NoteCommandParser commandParser;
    private NusModule nusModule;
    private Ui ui;

    @BeforeEach
    void setUp() {
        this.commandParser = NoteCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void execute_addCommand_success()
            throws InvalidCommandException, InvalidArgumentException, InvalidTimeFormatException {
        Command addCommand = commandParser.parseCommand("add \"test\" \"test1\"");
        CommandResult addResult = addCommand.execute(ui, nusModule);
        assertTrue(addResult.isOk());
        assertEquals(1, nusModule.getContentManager().getTotalContents());
        assertTrue(nusModule.getContentManager().getContentData(1).contains("test"));
        assertTrue(nusModule.getContentManager().getContentData(1).contains("test1"));
        for (int i = 0; i < 5; i++) {
            addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            addResult = addCommand.execute(ui, nusModule);
            assertTrue(addResult.isOk());
        }
        assertEquals(6, nusModule.getContentManager().getTotalContents());
    }
}
