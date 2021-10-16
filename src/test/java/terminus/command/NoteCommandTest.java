package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;
import terminus.parser.MainCommandParser;
import terminus.parser.NoteCommandParser;
import terminus.ui.Ui;

public class ScheduleCommandTest {

    private MainCommandParser commandParser;
    private Ui ui;

    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        commandParser = MainCommandParser.getInstance();
        moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        ui = new Ui();
    }

    @Test
    void execute_scheduleAdvance_success() throws InvalidArgumentException, InvalidCommandException {
        Command mainCommand = commandParser.parseCommand("go " + tempModule + " note");
        CommandResult changeResult = mainCommand.execute(ui, moduleManager);
        assertTrue(changeResult.isOk());
        assertTrue(changeResult.getAdditionalData() instanceof NoteCommandParser);
        mainCommand = commandParser.parseCommand("go " + tempModule + " note add \"username\" \"password\"");
        changeResult = mainCommand.execute(ui, moduleManager);
        assertTrue(changeResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
        mainCommand = commandParser.parseCommand("go " + tempModule + " note view");
        changeResult = mainCommand.execute(ui, moduleManager);
        assertTrue(changeResult.isOk());
    }

    @Test
    void execute_scheduleAdvance_throwsException() throws InvalidArgumentException, InvalidCommandException {
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note -1").execute(ui, moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note view 100").execute(ui, moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note delete -1").execute(ui, moduleManager));

    }
}
