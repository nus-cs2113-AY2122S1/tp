package seedu.duke.storage;

import seedu.duke.exceptions.module.StorageModuleException;
import seedu.duke.exceptions.ExceptionHandler;
import seedu.duke.module.Module;
import seedu.duke.module.ModuleList;
import seedu.duke.parser.module.ParserModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//@@author nvbinh15

/**
 * A class that deals with loading and saving data related to Modules into the file.
 */
public class StorageModule {

    private static ParserModule parserModule = new ParserModule();
    private static ExceptionHandler exceptionHandler = new ExceptionHandler();

    public static final String PROJECT_ROOT = System.getProperty("user.dir");
    public static final String STORAGE_FOLDER = "storage/module";
    public static final String MODULE_FILE_NAME = "module.txt";
    public static final String CAP_FILE_NAME = "cap.txt";
    public static final Path PATH_TO_MODULE_STORAGE_FILE = Paths.get(PROJECT_ROOT, STORAGE_FOLDER, MODULE_FILE_NAME);
    public static final Path PATH_TO_CAP_STORAGE_FILE = Paths.get(PROJECT_ROOT, STORAGE_FOLDER, CAP_FILE_NAME);

    /**
     * Creates a new file to store data related to Modules.
     *
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static void createModuleStorageFile() throws IOException {
        File file = new File(String.valueOf(PATH_TO_MODULE_STORAGE_FILE));
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Creates a new file to store data related to CAP.
     *
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static void createCapStorageFile() throws IOException {
        File file = new File(String.valueOf(PATH_TO_CAP_STORAGE_FILE));
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(String.valueOf(PATH_TO_CAP_STORAGE_FILE));
        fileWriter.write("0|0");
        fileWriter.close();
    }

    /**
     * Reads data that stored in the Module storage file.
     *
     * @return The data stored in the Module storage file or a new ModuleList if there is
     *      something wrong with the data file.
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static ModuleList readModulesFromFile() throws IOException {
        if (Files.notExists(PATH_TO_MODULE_STORAGE_FILE)) {
            createModuleStorageFile();
        }
        assert Files.exists(PATH_TO_MODULE_STORAGE_FILE);
        ArrayList<Module> storedModules = new ArrayList<>();
        File file = new File(String.valueOf(PATH_TO_MODULE_STORAGE_FILE));
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Module module;
                module = parserModule.retrieveStoredModule(scanner.nextLine());
                storedModules.add(module);
            }
        } catch (FileNotFoundException e) {
            exceptionHandler.handleOtherExceptions(e);
            return new ModuleList();
        } catch (StorageModuleException e) {
            createModuleStorageFile();
            ExceptionHandler.handleClickExceptions(e);
            return new ModuleList();
        }
        return new ModuleList(storedModules);
    }

    /**
     * Saves data to the Module storage file.
     *
     * @param moduleList The ModuleList to be stored.
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static void saveModuleToFile(ModuleList moduleList) throws IOException {
        FileWriter fileWriter = new FileWriter(String.valueOf(PATH_TO_MODULE_STORAGE_FILE));
        for (Module m : moduleList.getModules()) {
            fileWriter.write(parserModule.formatModuleToStore(m));
        }
        fileWriter.close();
    }

    /**
     * Reads data that stored in the CAP info storage file.
     *
     * @return An ArrayList containing CAP and MC.
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static ArrayList<Double> readCapInfoFromFile() throws IOException {
        if (Files.notExists(PATH_TO_CAP_STORAGE_FILE)) {
            createCapStorageFile();
        }
        assert Files.exists(PATH_TO_CAP_STORAGE_FILE);
        File file = new File(String.valueOf(PATH_TO_CAP_STORAGE_FILE));
        ArrayList<Double> capAndMc = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                capAndMc = parserModule.retrieveStoredCapInfo(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            exceptionHandler.handleOtherExceptions(e);
            capAndMc.add(0.0);
            capAndMc.add(0.0);
            return capAndMc;
        } catch (StorageModuleException e) {
            createCapStorageFile();
            exceptionHandler.handleClickExceptions(e);
            capAndMc.add(0.0);
            capAndMc.add(0.0);
            return capAndMc;
        }
        return capAndMc;
    }

    /**
     * Saves data to the CAP storage file.
     *
     * @param currentCap The current CAP of the user.
     * @param totalMcTaken The total of modular credits that contribute to the cap.
     * @throws IOException If there are failed or interrupted I/O operations.
     */
    public static void saveCapInfoToFile(double currentCap, int totalMcTaken) throws IOException {
        if (Files.notExists(PATH_TO_CAP_STORAGE_FILE)) {
            createCapStorageFile();
        }
        FileWriter fileWriter = new FileWriter(String.valueOf(PATH_TO_CAP_STORAGE_FILE));
        fileWriter.write(parserModule.formatCapInfoToStore(currentCap, totalMcTaken));
        fileWriter.close();
    }
}
