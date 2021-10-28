package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataWriter {

    public static void write(String filePath, String data) throws IOException {
        try {
            Path dataFolderPath = Paths.get(DataLocation.rootDirectory, DataLocation.dataLocation);
            Path dataFilePath = Paths.get(dataFolderPath.toString(), filePath);

            FileWriter fw = new FileWriter(dataFilePath.toString());
            fw.write(data);
            fw.close();
        } catch(IOException e) {
            throw new IOException("Cannot write to task data file.");
        }
    }
}
