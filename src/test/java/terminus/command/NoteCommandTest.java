package terminus.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.MainCommandParser;
import terminus.parser.NoteCommandParser;

public class NoteCommandTest {

    private MainCommandParser commandParser;
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() throws IOException {
        commandParser = MainCommandParser.getInstance();
        moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @Test
    void execute_noteAdvance_success()
            throws InvalidArgumentException, InvalidCommandException, IOException {
        Command mainCommand = commandParser.parseCommand("go " + tempModule + " note");
        CommandResult changeResult = mainCommand.execute(moduleManager);
        assertTrue(changeResult.isOk());
        assertTrue(changeResult.getNewCommandParser() instanceof NoteCommandParser);
        mainCommand = commandParser.parseCommand("go " + tempModule + " note add \"username\" \"password\"");
        changeResult = mainCommand.execute(moduleManager);
        assertTrue(changeResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
        mainCommand = commandParser.parseCommand("go " + tempModule + " note view");
        changeResult = mainCommand.execute(moduleManager);
        assertTrue(changeResult.isOk());
    }

    @Test
    void execute_noteAdvance_throwsException() {
        assertThrows(InvalidCommandException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note -1").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note view 100").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("go " + tempModule + " note delete -1").execute(moduleManager));

    }
}
