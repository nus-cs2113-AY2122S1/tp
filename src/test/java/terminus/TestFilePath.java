package terminus;

import java.nio.file.Path;

public class TestFilePath {

    public static final Path RESOURCE_FOLDER = Path.of("src", "test", "resources");
    public static final Path RESOURCE_DATA_FOLDER = RESOURCE_FOLDER.resolve("data");
    public static final String FAULTY_FOLDER = "faulty";
    public static final String SAVE_FILE = "saveFile.json";
    public static final String MALFORMED_FILE = "malformedFile.json";
    public static final String VALID_FILE = "validFile.json";
}
