package terminus.command.content.note;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.command.Command;
import terminus.command.CommandResult;
import terminus.common.CommonFormat;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidCommandException;
import terminus.module.ModuleManager;
import terminus.parser.NoteCommandParser;
import terminus.storage.ModuleStorage;
import terminus.ui.Ui;

public class ExportNoteCommandTest {

    Class<Note> type = Note.class;
    private NoteCommandParser commandParser;
    private ModuleManager moduleManager;
    private Ui ui;
    private ModuleStorage moduleStorage;
    private String tempModule = "test1";

    @AfterAll
    static void reset() throws IOException {
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.cleanAfterDeleteModule("test1");
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
        for (int i = 0; i < 5; i++) {
            Command addCommand = commandParser.parseCommand("add \"test" + i + "\" \"test" + i + "\"");
            CommandResult addResult = addCommand.execute(moduleManager);
            assertTrue(addResult.isOk());
        }

        assertEquals(5, moduleManager.getModule(tempModule).getContentManager(type).getTotalContents());

        Command exportCommand = commandParser.parseCommand("export");
        CommandResult exportResult = exportCommand.execute(moduleManager);
        assertTrue(exportResult.isOk());
        File pdf = new File(Paths.get(TestFilePath.RESOURCE_FOLDER.toString(), tempModule + CommonFormat.PDF_FORMAT)
                .toString());
        assertTrue(pdf.exists());
    }
}
