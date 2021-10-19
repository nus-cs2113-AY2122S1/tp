package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.content.Link;
import terminus.content.Note;
import terminus.module.ModuleManager;

public class ModuleStorageTest {

    /*private static final Path RESOURCE_FOLDER = Path.of("src", "test", "resources");
    private static final Path SAVE_FILE = RESOURCE_FOLDER.resolve("saveFile.json");
    private static final Path MALFORMED_FILE = RESOURCE_FOLDER.resolve("malformedFile.json");
    private static final Path VALID_FILE = RESOURCE_FOLDER.resolve("validFile.json");
    private ModuleManager moduleManager;

    private String tempModule = "test";

    @BeforeEach
    void setUp() {
        moduleManager = new ModuleManager();
        moduleManager.setModule(tempModule);
        moduleManager.getModule(tempModule).getContentManager(Note.class).add(new Note("test", "test"));
        moduleManager.getModule(tempModule).getContentManager(Link.class).add(new Link("test", "tuesday",
                LocalTime.of(11, 11), "https://zoom.us/"));
    }

    @Test
    void loadFile_invalidJson_exceptionThrown() {
        ModuleStorage moduleStorage = new ModuleStorage(MALFORMED_FILE);
        assertThrows(JsonSyntaxException.class, moduleStorage::loadFile);
    }

    @Test
    void loadFile_success() throws IOException {
        ModuleStorage moduleStorage = new ModuleStorage(VALID_FILE);
        ModuleManager module = moduleStorage.loadFile();
        assertEquals(module.getModule(tempModule).getContentManager(Note.class).listAllContents(),
                moduleManager.getModule(tempModule).getContentManager(Note.class).listAllContents());
        assertEquals(module.getModule(tempModule).getContentManager(Link.class).listAllContents(),
                moduleManager.getModule(tempModule).getContentManager(Link.class).listAllContents());
    }

    @Test
    void saveFile_nullArgument_exceptionThrown() {
        ModuleStorage saveModuleStorage = new ModuleStorage(SAVE_FILE);
        assertThrows(NullPointerException.class, () -> saveModuleStorage.saveFile(null));
    }

    @Test
    void saveFile_success() throws IOException {
        ModuleStorage saveModuleStorage = new ModuleStorage(SAVE_FILE);
        saveModuleStorage.saveFile(moduleManager);
        assertTextFilesEqual(SAVE_FILE, VALID_FILE);
    }

    *//**
     * Asserts whether the text in the two given files are the same.
     * Ignores any differences in line endings.
     * Taken from: https://github.com/se-edu/addressbook-level2/blob/master/test/java/seedu/addressbook/util/TestUtil.java#L128
     *//*
    public static void assertTextFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1);
        List<String> list2 = Files.readAllLines(path2);
        assertEquals(String.join("\n", list1), String.join("\n", list2));
    }*/
}
