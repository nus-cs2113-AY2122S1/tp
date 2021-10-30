package terminus;

import java.nio.file.Path;

public class TestFilePath {

    public static final Path RESOURCE_FOLDER = Path.of("src", "test", "resources");
    public static final String SAVE_FILE = "saveFile.json";
    public static final Path MALFORMED_FILE = RESOURCE_FOLDER.resolve("malformedFile.json");
    public static final Path VALID_FILE = RESOURCE_FOLDER.resolve("validFile.json");
}
