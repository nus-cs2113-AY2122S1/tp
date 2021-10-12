package terminus.command.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Note;
import terminus.exception.InvalidCommandException;
import terminus.exception.InvalidArgumentException;
import terminus.module.NusModule;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class DeleteNoteCommandTest {

    private NoteCommandParser commandParser;
    private NusModule nusModule;
    private Ui ui;

    Class<Note> type = Note.class;

    @BeforeEach
    void setUp() {
        this.commandParser = NoteCommandParser.getInstance();
        this.nusModule = new NusModule();
        this.ui = new Ui();
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, nusModule);
            assertTrue(addResult.isOk());
        }

        assertEquals(5, nusModule.getContentManager(type).getTotalContents());

        Command deleteCommand = commandParser.parseCommand("delete 1");
        CommandResult deleteResult = deleteCommand.execute(ui, nusModule);
        assertTrue(deleteResult.isOk());
        assertEquals(4, nusModule.getContentManager(type).getTotalContents());
        for (int i = 2; i < 4; i++) {
            deleteCommand = commandParser.parseCommand("delete " + i);
            deleteResult = deleteCommand.execute(ui, nusModule);
            assertTrue(deleteResult.isOk());
        }
        assertEquals(2, nusModule.getContentManager(type).getTotalContents());
    }

    @Test
    void execute_throwsException() throws InvalidCommandException, InvalidArgumentException {
        Command deleteCommand = commandParser.parseCommand("delete 100");
        assertThrows(InvalidArgumentException.class, () -> deleteCommand.execute(ui, nusModule));
    }
}
