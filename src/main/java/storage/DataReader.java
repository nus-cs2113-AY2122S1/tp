package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {

    public static String[] read(String filePath) throws FileNotFoundException {
        Scanner sc;
        ArrayList<String> fileContents = new ArrayList<>();

        try {
            Path dataFolderPath = Paths.get(DataLocation.rootDirectory, DataLocation.dataLocation);
            Path dataFilePath = Paths.get(dataFolderPath.toString(), filePath);
            File dataFile = new File(dataFilePath.toString());

            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Cannot find the task data file");
        }

        while (sc.hasNext()) {
            fileContents.add(sc.nextLine().strip());
        }

        String[] rawTasksString = new String[fileContents.size()];

        return fileContents.toArray(rawTasksString);
    }
}
