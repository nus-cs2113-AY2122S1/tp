package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import terminus.common.Messages;
import terminus.exception.InvalidFileException;
import terminus.module.ModuleManager;

public class JsonStorage extends Storage {

    private Path baseDirectory;
    private String jsonFileName;
    private Path jsonFilePath;
    private Gson gson;

    public JsonStorage(Path baseDirectory, String jsonFileName) {
        this.baseDirectory = baseDirectory;
        this.jsonFileName = jsonFileName;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        this.jsonFilePath = getAppendPath(baseDirectory, jsonFileName);
    }

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

    private void createJson() throws InvalidFileException {
        createFile(jsonFilePath);
    }

    public ModuleManager loadJson() throws InvalidFileException {
        BufferedReader reader = getBufferedReader(jsonFilePath);
        try {
            return gson.fromJson(reader, ModuleManager.class);
        } catch (Exception e) {
            throw new InvalidFileException(String.format(Messages.ERROR_FILE_BUFFERED_READER, jsonFilePath));
        }
    }

    private void saveJson(ModuleManager moduleManager) throws InvalidFileException {
        String jsonString = gson.toJson(moduleManager);
        writeFile(jsonFilePath, jsonString);
    }
}
