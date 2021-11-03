package seplanner.storage;

import seplanner.constants.Constants;
import seplanner.exceptions.InvalidMappingException;
import seplanner.exceptions.InvalidUniversityException;
import seplanner.exceptions.StorageException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.ui.UiStorage;
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

    public void updateFile(UniversityList universityList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < universityList.getSize(); i++) {
            University curr = universityList.get(i);
            fw.write(curr.toFileFormat());
        }
        fw.close();
        logger.log(Level.INFO, "File writing operation completed");
    }

    public UniversityList readFile(UniversityList universityMasterList,
                                   ModuleList moduleMasterList)
            throws IOException, StorageException {
        File file = loadFile(FILE_PATH);
        logger.log(Level.INFO, "File is either created or opened");
        Scanner scanner = new Scanner(file);
        UniversityList universitySelectedList = new UniversityList();
        ArrayList<ModuleMapping> moduleMappings = new ArrayList<>();
        String curr = " ";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (curr.equals(" ")) {
                curr = line;
            } else if (!line.contains("#")) {
                updateUniversityList(curr, moduleMappings, universitySelectedList,
                        universityMasterList);
                moduleMappings = new ArrayList<>();
                curr = line;
            } else {
                updateMappings(moduleMappings, line, moduleMasterList,
                        universityMasterList, curr);
            }
        }
        if (!curr.equals(" ")) {
            updateUniversityList(curr, moduleMappings, universitySelectedList,
                    universityMasterList);
        }
        updateFile(universitySelectedList);
        logger.log(Level.INFO, "Module mappings stored in the file are successfully loaded");
        return universitySelectedList;
    }

    private void updateMappings(ArrayList<ModuleMapping> moduleMappings,
                                String line, ModuleList moduleMasterList,
                                UniversityList universityMasterList,
                                String universityName) throws InvalidMappingException {
        String[] attributes = line.split(" # ");
        if (attributes.length != 6) {
            logger.log(Level.SEVERE, "Invalid mapping found in the file.");
            throw new InvalidMappingException();
        }
        Module local = new Module(attributes[0], attributes[1],
                    parseDouble(attributes[2]), moduleMasterList);
        Module mapped = new Module(attributes[3], attributes[4],
                    parseDouble(attributes[5]), 0);
        ModuleMapping newMapping = new ModuleMapping(local, mapped);
        University currentUni = universityMasterList.getUniversity(universityName);
        if ((local.getIndex() != -1) && currentUni.isExistMapping(newMapping)
                && !(isMappingExist(moduleMappings, newMapping))) {
            moduleMappings.add(newMapping);
        } else {
            logger.log(Level.SEVERE, "Invalid mapping found in the file.");
            throw new InvalidMappingException();
        }
    }

    private void updateUniversityList(String universityName,
                                      ArrayList<ModuleMapping> moduleMappings,
                                      UniversityList universitySelectedList,
                                      UniversityList universityMasterList)
            throws InvalidUniversityException {
        if ((universityMasterList.searchUniversity(universityName))
                && !(universitySelectedList.searchUniversity(universityName))) {
            universitySelectedList.addUniversity(new University(universityName, moduleMappings,
                    universityMasterList));
        } else {
            logger.log(Level.SEVERE, "Invalid university found in the file.");
            throw new InvalidUniversityException();
        }
    }

    private boolean isMappingExist(ArrayList<ModuleMapping> mappings,
                                   ModuleMapping searchMapping) {
        for (ModuleMapping mapping : mappings) {
            if (mapping.equals(searchMapping)) {
                return true;
            }
        }
        return false;
    }
}