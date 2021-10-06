package terminus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.ui.Ui;

public class NoteDeleteCommandTest {
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
    void execute_deleteCommand_success() throws InvalidCommandException, InvalidArgumentException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, nusModule);
            assertTrue(addResult.isOk());
        }

        assertEquals(5, nusModule.getContentManager().getTotalContents());

        Command deleteCommand = commandParser.parseCommand("delete 1");
        CommandResult deleteResult = deleteCommand.execute(ui, nusModule);
        assertTrue(deleteResult.isOk());
        assertEquals(4, nusModule.getContentManager().getTotalContents());
        for (int i = 2; i < 4; i++) {
            deleteCommand = commandParser.parseCommand("delete " + i);
            deleteResult = deleteCommand.execute(ui, nusModule);
            assertTrue(deleteResult.isOk());
        }
        assertEquals(2, nusModule.getContentManager().getTotalContents());
    }
}
