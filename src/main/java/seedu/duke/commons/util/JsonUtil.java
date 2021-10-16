package seedu.duke.commons.util;

import com.google.gson.Gson;

import seedu.duke.commons.core.Messages;
import seedu.duke.commons.util.exceptions.ModuleLoadException;
import seedu.duke.model.module.Module;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonUtil {
    private Module[] modules;
    private static Logger logger;
    private static final String FULL_MODULE_FILEPATH = System.getProperty("user.dir") + "/src/main/resources"
            + "/ModuleInfo.json";

    public JsonUtil() throws ModuleLoadException, FileNotFoundException {
        Gson gson = new Gson();
        logger = Logger.getLogger(JsonUtil.class.getName());

        Reader reader = new FileReader(FULL_MODULE_FILEPATH);
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
