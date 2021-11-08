package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import terminus.common.Messages;
import terminus.common.TerminusLogger;
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
            TerminusLogger.info(String.format("Updating JSON file : %s", jsonFileName));
            saveJson(moduleManager);
            break;
        case CREATE:
            TerminusLogger.info(String.format("Creating the main JSON file : %s", jsonFileName));
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
    protected void createJson() throws InvalidFileException {
        createFolder(baseDirectory);
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
            ModuleManager moduleManager = gson.fromJson(reader, ModuleManager.class);
            reader.close();
            return moduleManager;
        } catch (Exception e) {
            try {
                reader.close();
            } catch (IOException ex) {
                throw new InvalidFileException(e.getMessage());
            }
            throw new InvalidFileException(String.format(Messages.ERROR_FILE_BUFFERED_READER, jsonFilePath));
        }
    }

    /**
     * Saves the contents from ModuleManager back into the json file.
     *
     * @param moduleManager The Module Manager containing all item information used in TermiNUS.
     * @throws InvalidFileException when any file I/O operations has error.
     */
    protected void saveJson(ModuleManager moduleManager) throws InvalidFileException {
        assert moduleManager != null;
        String jsonString = gson.toJson(moduleManager);
        writeFile(jsonFilePath, jsonString);
    }

    /**
     * Sets a new filename for the json file.
     *
     * @param newName The new name of the json file.
     */
    protected void setJsonFileName(String newName) {
        this.jsonFileName = newName;
        this.jsonFilePath = getAppendPath(baseDirectory, jsonFileName);
    }
}
