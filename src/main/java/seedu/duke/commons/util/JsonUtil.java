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
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonUtil {
    private Module[] modules;
    private static Logger logger;
    private static final String ROOT_DIRECTORY = System.getProperty("user.dir");
    private static final String MODULE_DIRECTORY_PATH = "src/main/resources";
    private static final String MODULE_FILE_NAME = "ModuleInfo.json";
    private static final Path FULL_MODULE_FILEPATH = Paths.get(ROOT_DIRECTORY, MODULE_DIRECTORY_PATH, MODULE_FILE_NAME);

    public JsonUtil() throws ModuleLoadException, FileNotFoundException {
        Gson gson = new Gson();
        logger = Logger.getLogger(JsonUtil.class.getName());

        Reader reader = new FileReader(FULL_MODULE_FILEPATH.toString());
        modules = gson.fromJson(reader, Module[].class);

        if (modules.length == 0) {
            throw new ModuleLoadException(Messages.ERROR_MODULE_LOAD_FAILED);
        }

        logger.log(Level.INFO, "successfully loaded NUSMods module information from json file");
    }

    public Module[] getModules() {
        return modules;
    }
}
