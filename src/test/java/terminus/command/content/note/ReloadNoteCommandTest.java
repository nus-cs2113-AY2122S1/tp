package terminus.command.content.note;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.NoteCommandParser;
import terminus.storage.ModuleStorage;

public class ReloadNoteCommandTest {

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
    void execute_success() throws InvalidArgumentException, InvalidCommandException, IOException {
        Command addCommand = commandParser.parseCommand("add \"test1\" \"test1\"");
        addCommand.execute(moduleManager);
        addCommand = commandParser.parseCommand("add \"test2\" \"test2\"");
        addCommand.execute(moduleManager);
        addCommand = commandParser.parseCommand("add \"test3\" \"test3\"");
        addCommand.execute(moduleManager);
        ContentManager noteContentManager = moduleManager.getModule(tempModule).getContentManager(type);
        assertEquals(3, noteContentManager.getTotalContents());
        Note note2 = new Note("test2", "test2");
        assertEquals(note2.getDisplayInfo(), noteContentManager.getContentData(2));
        moduleStorage.removeNoteFromModule(tempModule, "test2");
        note2 = new Note("test100", "test100");
        moduleStorage.addNoteFromModule(tempModule, note2);
        Command reloadCommand = commandParser.parseCommand("reload");
        reloadCommand.execute(moduleManager);
        assertEquals(note2.getDisplayInfo(), noteContentManager.getContentData(2));
    }
}
