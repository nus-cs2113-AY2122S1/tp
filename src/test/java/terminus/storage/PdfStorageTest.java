package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.content.ContentManager;
import terminus.content.Note;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class PdfStorageTest {

    private PdfStorage pdfStorage;
    private ModuleManager moduleManager;

    private String tempModule = "test".toUpperCase();

    @BeforeEach
    void setup() {
        this.pdfStorage = new PdfStorage(TestFilePath.RESOURCE_FOLDER);
        this.moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
    }

    @AfterEach
    void reset() throws InvalidFileException {
        Path filepath = pdfStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, "TEST.pdf");
        pdfStorage.delete(filepath);
    }

    @Test
    void execute_success() throws InvalidFileException {
        NusModule module = moduleManager.getModule(tempModule);
        ContentManager<Note> noteContentManager = module.getContentManager(Note.class);
        noteContentManager.add(new Note("a", "this is a test"));
        pdfStorage.execute(moduleManager, tempModule, StorageActionEnum.EXPORT);
    }

    @Test
    void execute_invalidAction_exceptionThrown() {
        assertThrows(InvalidFileException.class, () -> pdfStorage.execute(moduleManager, tempModule,
                StorageActionEnum.CREATE));
    }

    @Test
    void exportModuleNotes_success() throws InvalidFileException {
        Path filepath = pdfStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, "TEST.pdf");
        assertFalse(Files.exists(filepath));
        NusModule module = moduleManager.getModule(tempModule);
        ContentManager<Note> noteContentManager = module.getContentManager(Note.class);
        noteContentManager.add(new Note("a", "this is a test"));
        pdfStorage.exportModuleNotes(moduleManager, tempModule);
        assertTrue(Files.exists(filepath));
    }

    @Test
    void exportModuleNotes_invalidInputs_exceptionThrown() throws InvalidFileException {
        Path filepath = pdfStorage.getAppendPath(TestFilePath.RESOURCE_FOLDER, "TEST.pdf");
        assertFalse(Files.exists(filepath));
        assertThrows(InvalidFileException.class, () -> pdfStorage.exportModuleNotes(moduleManager, tempModule));
        assertThrows(AssertionError.class, () -> pdfStorage.exportModuleNotes(moduleManager, "a"));
    }
}
