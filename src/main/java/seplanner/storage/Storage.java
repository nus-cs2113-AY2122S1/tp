package seplanner.storage;

import seplanner.constants.Constants;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author madhanse
/**
 * Master class involved in file operation in all the other storage classes.
 */
public class Storage {
    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
    private ModuleStorage moduleStorage;
    private UniversityStorage universityStorage;
    private SelectedModuleStorage selectedModuleStorage;
    private SelectedUniversityStorage selectedUniversityStorage;

    /**
     * Constructs a new Storage object, which in turn creates an object of other
     * storage classes.
     */
    public Storage() {
        this.moduleStorage = new ModuleStorage();
        this.universityStorage = new UniversityStorage();
        this.selectedModuleStorage = new SelectedModuleStorage();
        this.selectedUniversityStorage = new SelectedUniversityStorage();
    }

    /**
     * Reads the Module Master List from the CSV file.
     * @return Module Master List
     * @throws IOException If there is a problem accessing the file
     */
    public ArrayList<Module> readModuleList() throws IOException {
        logger.log(Level.INFO, "Start loading module data");
        InputStream inputStream = ModuleStorage.class.getResourceAsStream(
                "/modules.csv");
        return moduleStorage.readFile(inputStream);
    }

    /**
     * Reads the University Master List from the CSV file.
     * @param moduleMasterList Module Master List
     * @return University Master List
     * @throws IOException If there is a problem accessing the file
     */
    public ArrayList<University> readUniversityList(ModuleList moduleMasterList) throws IOException {
        logger.log(Level.INFO, "Start loading university data");
        InputStream inputStream = UniversityStorage.class.getResourceAsStream(
                "/University.csv");
        return universityStorage.readFile(inputStream, moduleMasterList);
    }

    /**
     * Reads the user's selected module list from the text file.
     * @param moduleMasterList Module Master List
     * @return User's selected module list
     * @throws IOException If there is a problem accessing the file
     */
    public ModuleList readSelectedModuleList(ModuleList moduleMasterList)
            throws IOException {
        logger.log(Level.INFO, "Start loading selected module data");
        return selectedModuleStorage.readFile(moduleMasterList);
    }

    /**
     * Reads the user's selected university list from the text file.
     * @param universityMasterList University Master List
     * @param moduleMasterList Module Master List
     * @return User's selected university list
     * @throws IOException If there is a problem in accessing the file
     */
    public UniversityList readSelectedUniversityList(
            UniversityList universityMasterList, ModuleList moduleMasterList)
            throws IOException {
        logger.log(Level.INFO, "Start loading selected university data");
        return selectedUniversityStorage.readFile(universityMasterList, moduleMasterList);
    }

    /**
     * Updates the user's selected module list into the text file.
     * @param moduleList User's selected module list
     * @throws IOException If there is a problem in accessing the file
     */
    public void updateSelectedModuleList(ModuleList moduleList) throws IOException {
        logger.log(Level.INFO, "File writing operation started");
        selectedModuleStorage.updateFile(moduleList);
    }

    /**
     * Updates the user's selected university list into the text file.
     * @param universityList User's selected university list
     * @throws IOException If there is a problem accessing the file
     */
    public void updateSelectedUniversityList(UniversityList universityList)
            throws IOException {
        logger.log(Level.INFO, "File writing operation started");
        selectedUniversityStorage.updateFile(universityList);
    }
}