package seedu.duke.storage;

import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleMapping;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

public class SelectedUniversityStorage {
    private static Logger logger = Logger.getLogger("SelectedUniversityStorageLog");

    private static final String FILE_PATH = "data/selectedUniversities.txt";

    public static void write(UniversityList universityList) throws IOException {
        logger.log(Level.INFO, "File writing operation started");
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < universityList.getSize(); i++) {
            University curr = universityList.get(i);
            fw.write(curr.toFileFormat());
        }
        fw.close();
        logger.log(Level.INFO, "File writing operation completed");
    }

    public static ArrayList<University> load() throws IOException {
        File file = loadFile();
        logger.log(Level.INFO, "File is either created or opened");
        Scanner scanner = new Scanner(file);
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<ModuleMapping> moduleMappings = new ArrayList<>();
        String curr = " ";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (curr.equals(" ")) {
                curr = line;
            } else if (!curr.contains("|")) {
                universities.add(new University(curr, moduleMappings));
                curr = line;
                moduleMappings = new ArrayList<>();
            } else {
                String[] attributes = line.split(" //| ");
                Module local = new Module(attributes[0], attributes[1],
                        parseDouble(attributes[2]));
                Module mapped = new Module(attributes[3], attributes[4],
                        parseDouble(attributes[5]));
                moduleMappings.add(new ModuleMapping(local, mapped));
            }
        }
        logger.log(Level.INFO, "Module mappings stored in the file are successfully loaded");
        return universities;
    }

    private static File loadFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }
}