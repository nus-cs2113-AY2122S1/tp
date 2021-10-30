package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.nio.file.Path;
import terminus.common.Messages;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

/**
 * JsonStorage class to handle any Json file related operations.
 */
public class JsonStorage extends Storage {

    private Path baseDirectory;
    private String jsonFileName;
    private Path jsonFilePath;
    private Gson gson;

    /**
     * Initialise a JsonStorage object that creates a GsonBuilder for Json loading and saving operation.
     *
     * @param baseDirectory The base directory in which all items is stored in.
     * @param jsonFileName The name of the main json file used by TermiNUS.
     */
    public JsonStorage(Path baseDirectory, String jsonFileName) {
        this.baseDirectory = baseDirectory;
        this.jsonFileName = jsonFileName;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jsonFilePath = getAppendPath(baseDirectory, jsonFileName);
    }

    /**
     * Executes the specified operation with the given arguments.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @param action The operation type to determine which operation to execute.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public void execute(ModuleManager moduleManager, StorageActionEnum action) throws InvalidFileException {
        switch (action) {
        case UPDATE:
            saveJson(moduleManager);
            break;
        case CREATE:
            createJson();
            break;
        default:
            throw new InvalidFileException(Messages.ERROR_STORAGE_INVALID_TYPE);
        }
    }

    /**
     * Creates the json file.
     *
     * @throws InvalidFileException when any file I/O operations has error.
     */
    private void createJson() throws InvalidFileException {
        createFile(jsonFilePath);
    }

    /**
     * Loads the content from the json file into Module Manager.
     *
     * @return The ModuleManager objects containing the contents from the json file.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    public ModuleManager loadJson() throws InvalidFileException {
        BufferedReader reader = getBufferedReader(jsonFilePath);
        try {
            return gson.fromJson(reader, ModuleManager.class);
        } catch (Exception e) {
            throw new InvalidFileException(String.format(Messages.ERROR_FILE_BUFFERED_READER, jsonFilePath));
        }
    }

    /**
     * Saves the contents from ModuleManager back into the json file.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    private void saveJson(ModuleManager moduleManager) throws InvalidFileException {
        String jsonString = gson.toJson(moduleManager);
        writeFile(jsonFilePath, jsonString);
    }
}
