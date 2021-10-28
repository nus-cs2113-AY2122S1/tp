package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import storage.DataLocation;

public class DataIntegrityChecker {

    public static void check() throws IOException {
        Path dataFolderPath = Paths.get(DataLocation.rootDirectory, DataLocation.dataLocation);
        boolean directoryExists = Files.exists(dataFolderPath);
        if (!directoryExists) {
            Files.createDirectory(dataFolderPath);
        }

        for (String filename : DataLocation.dataFilenames.values()) {
            Path dataFilePath = Paths.get(dataFolderPath.toString(), filename);
            boolean dataFileExists = Files.exists(dataFilePath);

            if (!dataFileExists) {
                Files.createFile(dataFilePath);
            }
        }
    }
}
