package terminus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import terminus.TestFilePath;
import terminus.content.ContentManager;
import terminus.content.Link;
import terminus.content.Question;
import terminus.exception.InvalidArgumentException;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;
import terminus.module.NusModule;

public class FilterManagerTest {

    private FilterManager filterManager;

    private Gson gson;

    public static final Path RESOURCE_FOLDER = TestFilePath.RESOURCE_FOLDER;
    public static final String SAVE_FILE = TestFilePath.SAVE_FILE;
    public static final String MALFORMED_FILE = TestFilePath.MALFORMED_FILE;
    public static final String VALID_FILE = TestFilePath.VALID_FILE;
    public static final String FAULTY_FOLDER = TestFilePath.FAULTY_FOLDER;

    public static final String INCORRECT_FILE = "incorrectFile.json";

    private Path incorrectFilePath;

    @BeforeEach
    void setup() {
        filterManager = new FilterManager();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        incorrectFilePath = Paths.get(RESOURCE_FOLDER.toString(),INCORRECT_FILE);
    }

    @Test
    void filterModule_success() throws IOException, InvalidArgumentException {
        BufferedReader reader = Files.newBufferedReader(incorrectFilePath);
        ModuleManager moduleManager = gson.fromJson(reader, ModuleManager.class);
        reader.close();
        filterManager.filter(moduleManager);
        assertEquals(1,moduleManager.getAllModules().length);
        NusModule module = moduleManager.getModule("TEST");
        ContentManager<Question> questionContentManager = module.getContentManager(Question.class);
        ContentManager<Link> linkContentManager = module.getContentManager(Link.class);
        assertEquals(2,questionContentManager.getTotalContents());
        assertEquals(1,linkContentManager.getTotalContents());
    }
}
