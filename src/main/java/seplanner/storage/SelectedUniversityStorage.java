package seplanner.storage;

import seplanner.constants.Constants;
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

//@@author madhanse
/**
 * Handles the storage for user's selected universities and its respective mappings.
 */
public class SelectedUniversityStorage extends UserStorage {
    private static final String FILE_PATH = "data/selectedUniversities.txt";
    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
    private static Boolean isUniversityValid = true;
    private static Boolean isMappingValid = true;

    /**
     * Writes the user's selected university list into the text file.
     * @param universityList User's selected university list.
     * @throws IOException If there is a problem in accessing the file.
     */
    public void updateFile(UniversityList universityList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < universityList.getSize(); i++) {
            University curr = universityList.get(i);
            fw.write(curr.toFileFormat());
        }
        fw.close();
        isMappingValid = true;
        isUniversityValid = true;
        logger.log(Level.INFO, "File writing operation completed");
    }

    /**
     * Loads the user's selected universities, and its associated mappings into the array
     * list. Error messages+
     * @param universityMasterList University Master List
     * @param moduleMasterList Module Master List
     * @return Arraylist storing the user's selected university list
     * @throws IOException If there is a problem accessing the file
     */
    public UniversityList readFile(UniversityList universityMasterList,
                                   ModuleList moduleMasterList) throws IOException {
        File file = loadFile(FILE_PATH);
        Scanner scanner = new Scanner(file);
        UniversityList universitySelectedList = new UniversityList();
        ArrayList<ModuleMapping> moduleMappings = new ArrayList<>();
        String curr = " ";
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (curr.equals(" ")) {
                curr = line;
            } else if (!line.contains("#")) {
                updateUniversityList(curr, moduleMappings, universitySelectedList, universityMasterList);
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
        if (!isUniversityValid) {
            System.out.println(UiStorage.getInvalidUniversityMessage());
        } else if (!isMappingValid) {
            System.out.println(UiStorage.getInvalidMappingMessage());
        }
        updateFile(universitySelectedList);
        logger.log(Level.INFO, "Module mappings stored in the file are successfully loaded");
        return universitySelectedList;
    }

    /**
     * Adds a new mapping into the array list if it exists.
     * @param moduleMappings Array List storing the module mappings
     * @param line Line read from the file
     * @param moduleMasterList  Module Master List
     * @param universityMasterList University Master List
     * @param universityName Name of the university
     */
    private void updateMappings(ArrayList<ModuleMapping> moduleMappings,
                                String line, ModuleList moduleMasterList,
                                UniversityList universityMasterList,
                                String universityName) {
        try {
            String[] attributes = line.split(" # ");

            if (attributes.length != 6) {
                logger.log(Level.SEVERE, "Invalid mapping found in the file.");
                isMappingValid = false;
                return;
            }
            Module local = new Module(attributes[0], attributes[1],
                    parseDouble(attributes[2]), moduleMasterList);
            Module mapped = new Module(attributes[3], attributes[4],
                    parseDouble(attributes[5]), 0);
            ModuleMapping newMapping = new ModuleMapping(local, mapped);
            University currentUni = universityMasterList.getUniversity(universityName);
            if ((local.getIndex() != -1) && (currentUni != null)
                    && currentUni.isExistMapping(newMapping)
                    && !(isMappingExist(moduleMappings, newMapping))) {
                moduleMappings.add(newMapping);
            } else {
                logger.log(Level.SEVERE, "Invalid mapping found in the file.");
                isMappingValid = false;
            }
        } catch (NumberFormatException ne) {
            logger.log(Level.SEVERE, "Invalid mapping found in the file.");
            isMappingValid = false;
        }
    }

    /**
     * Adds a university object into the user's selected university list if the university name
     * is valid.
     * @param universityName Name of the univeristy
     * @param moduleMappings List containing the module mappings
     * @param universitySelectedList User's selected university list
     * @param universityMasterList University Master List
     */
    private void updateUniversityList(String universityName,
                                      ArrayList<ModuleMapping> moduleMappings,
                                      UniversityList universitySelectedList,
                                      UniversityList universityMasterList) {
        if ((universityMasterList.isExistUniversity(universityName))
                && !(universitySelectedList.isExistUniversity(universityName))) {
            universitySelectedList.addUniversity(new University(universityName, moduleMappings,
                    universityMasterList));
        } else {
            logger.log(Level.SEVERE, "Invalid university found in the file.");
            isUniversityValid = false;
        }
    }

    /**
     * Checks if the module already exists in the list of mappings to
     * remove duplicates.
     * @param moduleMappings List containing the module mappings
     * @param searchMapping Mapping to be searched
     * @return True if it exists. Otherwise, false
     */
    private boolean isMappingExist(ArrayList<ModuleMapping> moduleMappings,
                                   ModuleMapping searchMapping) {
        for (ModuleMapping mapping : moduleMappings) {
            if (mapping.isEqual(searchMapping)) {
                return true;
            }
        }
        return false;
    }
}