package terminus.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import terminus.module.NusModule;

public class ModuleStorage {
    
    private final Path filePath;
    private final Gson gson;

    public ModuleStorage(Path filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    private void initializeFile() throws IOException {
        assert filePath != null: "filePath should not be null";
        if (!Files.isDirectory(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
    }
    
    public NusModule loadFile() throws IOException {
        initializeFile();
        if (!Files.isReadable(filePath)) {
            return null;
        }
        return gson.fromJson(Files.newBufferedReader(filePath), NusModule.class);
    }
    
    public void saveFile(NusModule module) throws IOException {
        initializeFile();
        String jsonString = gson.toJson(module);
        assert jsonString != null && !jsonString.isBlank(): "File saved is blank";
        Files.writeString(filePath, jsonString);
    }
    
}
