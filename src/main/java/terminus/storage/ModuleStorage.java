package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import terminus.common.TerminusLogger;
import terminus.module.NusModule;

public class ModuleStorage {
    
    private final Path filePath;
    private final Gson gson;

    public ModuleStorage(Path filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private void initializeFile() throws IOException {
        assert filePath != null : "filePath should not be null";
        if (!Files.isDirectory(filePath.getParent())) {
            TerminusLogger.warning("Parent directories not found, attempting to create them...");
            Files.createDirectories(filePath.getParent());
            TerminusLogger.info("Parent directories created.");
        }
        if (!Files.exists(filePath)) {
            TerminusLogger.warning(String.format("%s not found, attempting to create file...",
                    filePath.getFileName().toString()));
            Files.createFile(filePath);
            TerminusLogger.info(String.format("%s created.", filePath.getFileName().toString()));
        }
    }
    
    public NusModule loadFile() throws IOException {
        initializeFile();
        if (!Files.isReadable(filePath)) {
            TerminusLogger.severe("File is does not exist or is not readable!");
            return null;
        }
        TerminusLogger.info("Decoding JSON to object");
        return gson.fromJson(Files.newBufferedReader(filePath), NusModule.class);
    }
    
    public void saveFile(NusModule module) throws IOException {
        initializeFile();
        TerminusLogger.info("Converting NusModule object into String...");
        String jsonString = gson.toJson(module);
        TerminusLogger.info("String conversion completed.");
        TerminusLogger.info(String.format("Writing to file: %s", filePath.toString()));
        assert jsonString != null && !jsonString.isBlank() : "File saved is blank";
        Files.writeString(filePath, jsonString);
    }
    
}
