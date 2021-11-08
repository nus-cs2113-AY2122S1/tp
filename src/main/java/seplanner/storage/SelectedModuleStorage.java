package seplanner.storage;

import seplanner.constants.Constants;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.ui.UiStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

//@@author madhanse
/**
 * Hands the storage for user's selected module list.
 */
public class SelectedModuleStorage extends UserStorage {
    private static final String FILE_PATH = "data/selectedModules.txt";
    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
    private static Boolean isModuleValid = true;

    /**
     * Writes the user's selected module list into the file.
     * @param moduleList User's selected module list.
     * @throws IOException If there is a problem in accessing the file.
     */
    public void updateFile(ModuleList moduleList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < moduleList.getSize(); i++) {
            Module curr = moduleList.get(i);
            fw.write(curr.toFileFormat());
        }
        fw.close();
        isModuleValid = true;
        logger.log(Level.INFO, "File writing operation completed");
    }

    /**
     * Reads the file storing the user's selected module list and adds them into.
     * the array list.
     * @param moduleMasterList Module Master List.
     * @return Array List storing the user's selected modules.
     * @throws IOException If there is a problem accessing the file.
     */
    public ModuleList readFile(ModuleList moduleMasterList)
            throws IOException {
        File file = loadFile(FILE_PATH);
        logger.log(Level.INFO, "File is either created or opened");
        Scanner scanner = new Scanner(file);
        ModuleList moduleList = new ModuleList();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            updateList(moduleList, line, moduleMasterList);
        }
        logger.log(Level.INFO, "Modules stored in the file are successfully loaded");
        if (!isModuleValid) {
            System.out.println(UiStorage.getInvalidModuleMessage());
        }
        updateFile(moduleList);
        return moduleList;
    }

    /**
     * Adds the module into the module list if it exists in the master list.
     * @param moduleList User's selected module list
     * @param line Line read by the Scanner
     * @param moduleMasterList Module Master List
     */
    private void updateList(ModuleList moduleList, String line,
                            ModuleList moduleMasterList) {
        try {
            String[] attributes = line.split(" # ");
            if (attributes.length != 3) {
                logger.log(Level.SEVERE, "Invalid module found in the file.");
                isModuleValid = false;
                return;
            }
            Module newModule = new Module(attributes[0], attributes[1],
                    parseDouble(attributes[2]), moduleMasterList);
            if ((newModule.getIndex() != -1)
                    && (moduleMasterList.isModuleExist(newModule))
                    && !(moduleList.isModuleExist(newModule))) {
                moduleList.addModule(newModule);
            } else {
                logger.log(Level.SEVERE, "Invalid module found in the file.");
                isModuleValid = false;
            }
        } catch (NumberFormatException ne) {
            logger.log(Level.SEVERE, "Invalid module found in the file.");
            isModuleValid = false;
        }
    }
}