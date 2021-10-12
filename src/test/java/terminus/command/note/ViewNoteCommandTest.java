package terminus.command.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class ViewNoteCommandTest {
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
    void execute_viewAll_success()
            throws InvalidCommandException, InvalidArgumentException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(ui, nusModule);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, nusModule.getContentManager(type).getTotalContents());

        Command viewCommand = commandParser.parseCommand("view");
        CommandResult result = viewCommand.execute(ui,nusModule);




    }


}
