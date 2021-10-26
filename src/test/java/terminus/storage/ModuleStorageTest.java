package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.JsonSyntaxException;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.common.CommonFormat;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Note;
import terminus.exception.InvalidArgumentException;
import terminus.module.ModuleManager;

public class ModuleStorageTest {

    private static final Path RESOURCE_FOLDER = Path.of("src", "test", "resources");
    private static final Path SAVE_FILE = RESOURCE_FOLDER.resolve("saveFile.json");
    private static final Path MALFORMED_FILE = RESOURCE_FOLDER.resolve("malformedFile.json");
    private static final Path VALID_FILE = RESOURCE_FOLDER.resolve("validFile.json");
    private ModuleManager moduleManager;
    private ModuleStorage moduleStorage;

    private String tempModule = "test".toUpperCase();

    @AfterAll
    static void reset() throws IOException {
        ModuleStorage moduleStorage = ModuleStorage.getInstance();
        moduleStorage.cleanAfterDeleteModule("test");
    }

    /**
     * Asserts whether the text in the two given files are the same. Ignores any differences in line endings. Taken
     * from: https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/util/TestUtil.java#L128
     */
    public static void assertTextFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1);
        List<String> list2 = Files.readAllLines(path2);
        assertEquals(String.join("\n", list1), String.join("\n", list2));
    }

    @BeforeEach
    void setUp() throws IOException {
        this.moduleStorage = ModuleStorage.getInstance();
        this.moduleStorage.init(SAVE_FILE);
        moduleManager = new ModuleManager();
        moduleManager.addModule(tempModule);
        moduleManager.getModule(tempModule).getContentManager(Note.class).add(new Note("test", "test"));
        moduleStorage.saveNotesFromModule(moduleManager, tempModule, true);
        moduleManager.getModule(tempModule).getContentManager(Link.class).add(new Link("test", "tuesday",
                LocalTime.of(11, 11), 2, "https://zoom.us/"));
    }

    @AfterEach
    void reset_filepath() throws IOException {
        File pdf = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule + CommonFormat.PDF_FORMAT)
                .toString());
        pdf.delete();
        this.moduleStorage.init(SAVE_FILE);
        this.moduleStorage.cleanAfterDeleteModule("test");
        this.moduleStorage.cleanAfterDeleteModule("test1");
    }

    @Test
    void loadFile_invalidJson_exceptionThrown() {
        this.moduleStorage.init(MALFORMED_FILE);
        assertThrows(JsonSyntaxException.class, moduleStorage::loadFile);
    }

    @Test
    void loadFile_success() throws IOException {
        this.moduleStorage.init(VALID_FILE);
        ModuleManager module = moduleStorage.loadFile();
        assertEquals(module.getModule(tempModule).getContentManager(Note.class).listAllContents(),
                moduleManager.getModule(tempModule).getContentManager(Note.class).listAllContents());
        assertEquals(module.getModule(tempModule).getContentManager(Link.class).listAllContents(),
                moduleManager.getModule(tempModule).getContentManager(Link.class).listAllContents());
    }

    @Test
    void saveFile_nullArgument_exceptionThrown() {
        this.moduleStorage.init(SAVE_FILE);
        assertThrows(NullPointerException.class, () -> moduleStorage.saveFile(null));
    }

    @Test
    void saveFile_success() throws IOException {
        this.moduleStorage.init(SAVE_FILE);
        moduleStorage.saveFile(moduleManager);
        assertTextFilesEqual(SAVE_FILE, VALID_FILE);
    }

    @Test
    void removeNoteFromModule_success() throws IOException {
        File deletedFile = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "test.txt").toString());
        assertTrue(deletedFile.exists());
        moduleStorage.removeNoteFromModule(tempModule, "test");
        assertFalse(deletedFile.exists());
    }

    @Test
    void addNoteFromModule_success() throws IOException {
        File file = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "test1.txt").toString());
        assertFalse(file.exists());
        this.moduleStorage.addNoteFromModule(tempModule, new Note("test1", "test1"));
        assertTrue(file.exists());
    }

    @Test
    void createModuleDirectory_success() throws IOException {
        File file = new File(Paths.get(RESOURCE_FOLDER.toString(), "newmod").toString());
        assertFalse(file.exists());
        this.moduleStorage.createModuleDirectory("newmod");
        assertTrue(file.exists());
        moduleStorage.cleanAfterDeleteModule("newmod");
    }

    @Test
    void createModuleDirectory_invalidModuleName_exceptionThrown() {
        assertThrows(AssertionError.class, () -> this.moduleStorage.createModuleDirectory("new mod"));
        assertThrows(AssertionError.class, () -> this.moduleStorage.createModuleDirectory(""));
        assertThrows(AssertionError.class, () -> this.moduleStorage.createModuleDirectory(null));
    }

    @Test
    void cleanAfterDeleteModule_success() throws IOException {
        File file = new File(Paths.get(RESOURCE_FOLDER.toString(), "newmod").toString());
        this.moduleStorage.createModuleDirectory("newmod");
        assertTrue(file.exists());
        moduleStorage.cleanAfterDeleteModule("newmod");
        assertFalse(file.exists());
    }

    @Test
    void saveAllNotes_success() throws IOException {
        ContentManager<Note> noteManager = this.moduleManager.getModule(tempModule).getContentManager(Note.class);
        Note note1 = new Note("a", "test");
        Note note2 = new Note("b", "test");
        Note note3 = new Note("c", "test");
        noteManager.add(note1);
        noteManager.add(note2);
        noteManager.add(note3);
        this.moduleStorage.cleanAfterDeleteModule(tempModule);
        File file1 = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "a.txt").toString());
        File file2 = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "b.txt").toString());
        File file3 = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "c.txt").toString());
        assertFalse(file1.exists());
        assertFalse(file2.exists());
        assertFalse(file3.exists());
        this.moduleStorage.saveAllNotes(this.moduleManager);
        assertTrue(file1.exists());
        assertTrue(file2.exists());
        assertTrue(file3.exists());
    }

    @Test
    void loadAllNotes_success() throws IOException {
        Note note1 = new Note("a", "test");
        Note note2 = new Note("b", "test");
        Note note3 = new Note("c", "test");
        this.moduleStorage.addNoteFromModule(tempModule, note1);
        this.moduleStorage.addNoteFromModule(tempModule, note2);
        this.moduleStorage.addNoteFromModule(tempModule, note3);
        File file1 = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "a.txt").toString());
        File file2 = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "b.txt").toString());
        File file3 = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule, "c.txt").toString());
        assertTrue(file1.exists());
        assertTrue(file2.exists());
        assertTrue(file3.exists());
        assertEquals(1,
                this.moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
        this.moduleManager = this.moduleStorage.loadFile();
        assertEquals(4,
                this.moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
    }

    @Test
    void exportModuleNotes_success() throws IOException, InvalidArgumentException {
        ContentManager<Note> noteManager = this.moduleManager.getModule(tempModule).getContentManager(Note.class);
        Note note1 = new Note("a", "test");
        Note note2 = new Note("b", "test");
        Note note3 = new Note("c", "test");
        noteManager.add(note1);
        noteManager.add(note2);
        noteManager.add(note3);
        this.moduleStorage.exportModuleNotes(tempModule, noteManager.getContents());
        File pdf = new File(Paths.get(RESOURCE_FOLDER.toString(), tempModule + CommonFormat.PDF_FORMAT)
                .toString());
        assertTrue(pdf.exists());
        StringBuilder content = new StringBuilder();
        try {
            PdfReader pdfReader = new PdfReader(new FileInputStream(pdf.getAbsolutePath()));
            int pages = pdfReader.getNumberOfPages();
            for (int i = 1; i <= pages; i++) {
                content.append(PdfTextExtractor.getTextFromPage(pdfReader, i));
            }
            assertTrue(content.toString().contains("a"));
            assertTrue(content.toString().contains("b"));
            assertTrue(content.toString().contains("c"));
            assertTrue(content.toString().contains("test"));
            pdfReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pdf.delete();
    }

    @Test
    void exportModuleNotes_throwException() throws IOException, InvalidArgumentException {
        ContentManager<Note> noteManager = this.moduleManager.getModule(tempModule).getContentManager(Note.class);
        noteManager.deleteContent(1);
        assertThrows(InvalidArgumentException.class,
            () -> this.moduleStorage.exportModuleNotes(tempModule, noteManager.getContents()));
        this.moduleStorage.init(Paths.get(RESOURCE_FOLDER.toString(),"doesNotExist","didNotExist"));
        assertThrows(IOException.class,
            () -> this.moduleStorage.exportModuleNotes(tempModule, noteManager.getContents()));

    }

    @Test
    void updateModuleDirectory_success() throws IOException {
        Path oldPath = Paths.get(RESOURCE_FOLDER.toString(), tempModule);
        Path newPath = Paths.get(RESOURCE_FOLDER.toString(), "test1");
        assertTrue(Files.exists(oldPath));
        assertFalse(Files.exists(newPath));
        this.moduleStorage.updateModuleDirectory(tempModule, "test1");
        assertTrue(Files.exists(newPath));
        assertFalse(Files.exists(oldPath));
    }

    @Test
    void updateModuleDirectory_newDirectoryExists() throws IOException {
        Path oldPath = Paths.get(RESOURCE_FOLDER.toString(), tempModule);
        Path newPath = Paths.get(RESOURCE_FOLDER.toString(), "test1");
        Files.createDirectories(newPath);
        assertTrue(Files.exists(oldPath));
        assertTrue(Files.exists(newPath));
        this.moduleStorage.updateModuleDirectory(tempModule, "test1");
        assertTrue(Files.exists(newPath));
        assertTrue(Files.exists(oldPath));
    }

    @Test
    void updateModuleDirectory_noDirectoryExists() throws IOException {
        Path oldPath = Paths.get(RESOURCE_FOLDER.toString(), tempModule);
        Path newPath = Paths.get(RESOURCE_FOLDER.toString(), "test1");
        this.moduleStorage.cleanAfterDeleteModule(tempModule);
        assertFalse(Files.exists(oldPath));
        assertFalse(Files.exists(newPath));
        this.moduleStorage.updateModuleDirectory(tempModule, "test1");
        assertTrue(Files.exists(newPath));
        assertFalse(Files.exists(oldPath));
    }

    @Test
    void loadNotesFromModule_success() throws IOException {
        moduleStorage.loadNotesFromModule(moduleManager, tempModule);
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
        Path nonTextFilePath = Paths.get(RESOURCE_FOLDER.toString(), tempModule, "test1.ser");
        File nonTextFile = new File(nonTextFilePath.toString());
        FileOutputStream output = new FileOutputStream(nonTextFile);
        ObjectOutputStream objectOutput = new ObjectOutputStream(output);
        objectOutput.writeObject("save");
        objectOutput.flush();
        objectOutput.close();
        moduleStorage.loadNotesFromModule(moduleManager, tempModule);
        assertEquals(1, moduleManager.getModule(tempModule).getContentManager(Note.class).getTotalContents());
    }
}
