package seplanner.storage;

import com.sun.tools.jconsole.JConsoleContext;
import seplanner.constants.Constants;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
    private ModuleStorage moduleStorage;
    private UniversityStorage universityStorage;
    private SelectedModuleStorage selectedModuleStorage;
    private SelectedUniversityStorage selectedUniversityStorage;

    public Storage() {
        this.moduleStorage = new ModuleStorage();
        this.universityStorage = new UniversityStorage();
        this.selectedModuleStorage = new SelectedModuleStorage();
        this.selectedUniversityStorage = new SelectedUniversityStorage();
    }

    public ArrayList<Module> readModuleList() throws IOException {
        logger.log(Level.INFO, "Start loading module data");
        InputStream inputStream = ModuleStorage.class.getResourceAsStream(
                "/modules.csv");
        return moduleStorage.readModuleList(inputStream);
    }

    public ArrayList<University> readUniversityList(ModuleList moduleMasterList) throws IOException {
        logger.log(Level.INFO, "Start loading university data");
        InputStream inputStream = UniversityStorage.class.getResourceAsStream(
                "/University.csv");
        return universityStorage.readUniversityList(inputStream, moduleMasterList);
    }

    public ArrayList<Module> readSelectedModuleList(ModuleList moduleMasterList) throws IOException {
        logger.log(Level.INFO, "Start loading selected module data");
        return selectedModuleStorage.readSelectedModuleList(moduleMasterList);
    }

    public ArrayList<University> readSelectedUniversityList(
            UniversityList universityMasterList, ModuleList moduleMasterList) throws IOException {
        logger.log(Level.INFO, "Start loading selected university data");
        return selectedUniversityStorage.readSelectedUniversityList(universityMasterList, moduleMasterList);
    }

    public void updateSelectedModuleList(ModuleList moduleList) throws IOException {
        logger.log(Level.INFO, "File writing operation started");
        selectedModuleStorage.updateSelectedModuleList(moduleList);
    }

    public void updateSelectedUniversityList(UniversityList universityList)
            throws IOException {
        logger.log(Level.INFO, "File writing operation started");
        selectedUniversityStorage.updateSelectedUniversityList(universityList);
    }
}
