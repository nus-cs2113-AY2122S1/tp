package seplanner.storage;

import seplanner.constants.Constants;
import seplanner.exceptions.InvalidModuleException;
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

public class SelectedModuleStorage extends UserStorage {
    private static Logger logger = Logger.getLogger(Constants.LOGGER_NAME);

    private static final String FILE_PATH = "data/selectedModules.txt";

    public void updateFile(ModuleList moduleList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < moduleList.getSize(); i++) {
            Module curr = moduleList.get(i);
            fw.write(curr.toFileFormat());
        }
        fw.close();
        logger.log(Level.INFO, "File writing operation completed");
    }

    public ModuleList readFile(ModuleList moduleMasterList)
            throws IOException, InvalidModuleException {
        File file = loadFile(FILE_PATH);
        logger.log(Level.INFO, "File is either created or opened");
        Scanner scanner = new Scanner(file);
        ModuleList moduleList = new ModuleList();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            updateList(moduleList, line, moduleMasterList);
        }
        updateFile(moduleList);
        logger.log(Level.INFO, "Modules stored in the file are successfully loaded");
        return moduleList;
    }

    private void updateList(ModuleList moduleList, String line,
                            ModuleList moduleMasterList) throws InvalidModuleException {
        String[] attributes = line.split(" # ");
        if (attributes.length != 3) {
            logger.log(Level.SEVERE, "Invalid module found in the file.");
            logger.log(Level.SEVERE, "Deleted the invalid module.");
            throw new InvalidModuleException();
        }
        Module newModule = new Module(attributes[0], attributes[1],
                parseDouble(attributes[2]), moduleMasterList);
        if ((newModule.getIndex() != -1)
                && (moduleMasterList.isModuleExist(newModule))
                && !(moduleList.isModuleExist(newModule))) {
            moduleList.addModule(newModule);
        } else {
            logger.log(Level.SEVERE, "Invalid module found in the file.");
            throw new InvalidModuleException();
        }
    }
}