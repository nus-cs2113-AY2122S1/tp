package seedu.duke.commons.util;

import com.google.gson.Gson;

import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.exceptions.ModuleLoadException;
import seedu.duke.model.module.Module;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonUtil {
    private Module[] modules;
    private Map<String, Module> moduleMap;
    private static Logger logger;
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final String MODULE_FILE_NAME = "ModuleInfo.json";
    private static final Path FULL_MODULE_FILEPATH = Paths.get(ROOT_DIRECTORY, "src", "main",
            "resources", MODULE_FILE_NAME);

    /**
     * Constructor that handles translation from module information in json data to Module[] array that is used for
     * internal module representation.
     *
     * @throws ModuleLoadException exception thrown when the module information file ModuleInfo.json is corrupted
     * @throws FileNotFoundException exception thrown when ModuleInfo.json is not found
     */
    public JsonUtil() throws ModuleLoadException, FileNotFoundException {
        Gson gson = new Gson();
        moduleMap = new HashMap<>();
        logger = Logger.getLogger(JsonUtil.class.getName());

        Reader reader = new FileReader(FULL_MODULE_FILEPATH.toString());
        modules = gson.fromJson(reader, Module[].class);

        if (modules.length == 0) {
            throw new ModuleLoadException(Messages.ERROR_MODULE_LOAD_FAILED);
        }

        for (int i = 0; i < modules.length; i++) {
            moduleMap.put(modules[i].getModuleCode(), modules[i]);
        }

        logger.log(Level.INFO, "successfully loaded NUSMods module information from json file");
    }

    public Module[] getModules() {
        return modules;
    }

    public Map<String, Module> getModuleMap() {
        return moduleMap;
    }
}
