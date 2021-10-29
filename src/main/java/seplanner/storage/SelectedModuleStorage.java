package seplanner.storage;

import seplanner.modules.Module;
import seplanner.modules.ModuleList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.lang.Double.parseDouble;

public class SelectedModuleStorage extends UserStorage {
    private static Logger logger = Logger.getLogger("SelectedModuleStorageLog");

    private static final String FILE_PATH = "data/selectedModules.txt";

    public void updateSelectedModuleList(ModuleList moduleList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (int i = 0; i < moduleList.getSize(); i++) {
            Module curr = moduleList.get(i);
            fw.write(curr.toFileFormat());
        }
        fw.close();
//        logger.log(Level.INFO, "File writing operation completed");
    }

    public ArrayList<Module> readSelectedModuleList(ModuleList moduleMasterList) throws IOException {
        File file = loadFile(FILE_PATH);
//        logger.log(Level.INFO, "File is either created or opened");
        Scanner scanner = new Scanner(file);
        ArrayList<Module> modules = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] attributes = line.split(" # ");
            modules.add(new Module(attributes[0], attributes[1], parseDouble(attributes[2]), moduleMasterList));
        }
//        logger.log(Level.INFO, "Modules stored in the file are successfully loaded");
        return modules;
    }
}