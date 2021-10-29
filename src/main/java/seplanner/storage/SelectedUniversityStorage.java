package seplanner.storage;

import seplanner.constants.Constants;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

public class SelectedUniversityStorage extends UserStorage {
    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    private static final String FILE_PATH = "data/selectedUniversities.txt";

    public void updateSelectedUniversityList(UniversityList universityList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < universityList.getSize(); i++) {
            University curr = universityList.get(i);
            fw.write(curr.toFileFormat());
        }
        fw.close();
        logger.log(Level.INFO, "File writing operation completed");
    }

    public ArrayList<University> readSelectedUniversityList(
            UniversityList universityMasterList, ModuleList moduleMasterList) throws IOException {
        File file = loadFile(FILE_PATH);
        logger.log(Level.INFO, "File is either created or opened");
        Scanner scanner = new Scanner(file);
        ArrayList<University> universities = new ArrayList<>();
        ArrayList<ModuleMapping> moduleMappings = new ArrayList<>();
        String curr = " ";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (curr.equals(" ")) {
                curr = line;
            } else if (!line.contains("#")) {
                universities.add(new University(curr, moduleMappings, universityMasterList));
                curr = line;
                moduleMappings = new ArrayList<>();
            } else {
                String[] attributes = line.split(" # ");
                Module local = new Module(attributes[0], attributes[1],
                        parseDouble(attributes[2]),moduleMasterList);
                Module mapped = new Module(attributes[3], attributes[4],
                        parseDouble(attributes[5]),moduleMasterList);
                moduleMappings.add(new ModuleMapping(local, mapped));
            }
        }
        if (!curr.equals(" ")) {
            universities.add(new University(curr, moduleMappings, universityMasterList));
        }
        logger.log(Level.INFO, "Module mappings stored in the file are successfully loaded");
        return universities;
    }
}