package terminus.command.content.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.itextpdf.text.DocumentException;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.TerminusLogger;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.NoteCommandParser;
import terminus.storage.ModuleStorage;

public class AddNoteCommandTest {

    Class<Note> type = Note.class;
    private NoteCommandParser commandParser;
    private ModuleManager moduleManager;
    private ModuleStorage moduleStorage;
    private String tempModule = "test";

    @AfterAll
    static void reset() throws IOException {
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.cleanAfterDeleteModule("test");
    }

    @BeforeEach
    void setUp() throws IOException {
        this.moduleStorage = ModuleStorage.getInstance();
        this.moduleStorage.init(TestFilePath.SAVE_FILE);
        this.moduleStorage.createModuleDirectory(tempModule);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        this.commandParser = NoteCommandParser.getInstance();
        this.commandParser.setModuleName(tempModule);
    }

    @Test
    void execute_success() throws InvalidCommandException, InvalidArgumentException, IOException {
        Command addCommand = commandParser.parseCommand("add \"test\" \"test1\"");
        CommandResult addResult = addCommand.execute(moduleManager);
        assertTrue(addResult.isOk());
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1)
            .contains("test"));
        assertTrue(moduleManager.getModule(tempModule).getContentManager(type).getContentData(1)
            .contains("test1"));
    }

    @Test
    void execute_success_multipleNotes() throws InvalidArgumentException, InvalidCommandException, IOException,
            DocumentException {
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test" + i + "\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }
        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }

    @Test
    void execute_duplicateNoteName_exceptionThrown() throws InvalidArgumentException, InvalidCommandException,
            IOException {
        Command addCommand = commandParser.parseCommand("add \"test\" \"test\"");
        CommandResult addResult = addCommand.execute(moduleManager);
        for (int i = 1; i < 5; i++) {
            assertThrows(InvalidArgumentException.class,
                () -> commandParser.parseCommand("add \"test\" \"test\"").execute(moduleManager));
        }
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());
    }

    @Test
    void execute_illegalNoteName_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"\\uD83D\\uDC76 \" \"test\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \".......\" \"test\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"\" \"test\"").execute(moduleManager));
        String s = "a".repeat(31);
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"" + s + "\" \"test\"").execute(moduleManager));
    }

    @Test
    void execute_invalidArguments_exceptionThrown() {
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \" \"test\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"t\" \"\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"\" \"test\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"test\"\"\"").execute(moduleManager));
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \" \" \" \"").execute(moduleManager));
    }

    @Test
    void execute_longNoteData_exceptionThrown() {
        TerminusLogger.initializeLoggerWarnings();
        String s = "a".repeat(1000001);
        assertThrows(InvalidArgumentException.class,
            () -> commandParser.parseCommand("add \"test\" \"" + s + "\"").execute(moduleManager));
    }
}
