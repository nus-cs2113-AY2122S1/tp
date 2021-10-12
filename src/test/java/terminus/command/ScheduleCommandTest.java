package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.NusModule;
import terminus.parser.MainCommandParser;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class ScheduleCommandTest {

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
    void execute_scheduleAdvance_success() throws InvalidArgumentException, InvalidCommandException {
        Command mainCommand = commandParser.parseCommand("note");
        CommandResult changeResult = mainCommand.execute(ui, nusModule);
        assertTrue(changeResult.isOk());
        assertTrue(changeResult.getAdditionalData() instanceof NoteCommandParser);
        mainCommand = commandParser.parseCommand("note add \"username\" \"password\"");
        changeResult = mainCommand.execute(ui, nusModule);
        assertTrue(changeResult.isOk());
        assertEquals(1, nusModule.getContentManager(Note.class).getTotalContents());
        mainCommand = commandParser.parseCommand("note view");
        changeResult = mainCommand.execute(ui, nusModule);
        assertTrue(changeResult.isOk());
    }

    @Test
    void execute_scheduleAdvance_throwsException() throws InvalidArgumentException, InvalidCommandException {
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("schedule -1").execute(ui, nusModule));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("schedule view 100").execute(ui, nusModule));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("note delete -1").execute(ui, nusModule));

    }
}
