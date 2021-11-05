package seedu.duke.commons.util;

import com.google.gson.Gson;

import seedu.duke.commons.core.Message;
import seedu.duke.commons.util.exceptions.ModuleLoadException;
import seedu.duke.model.module.Module;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author ptejasv
public class JsonUtil {
    private static final Logger LOGGER = Logger.getLogger(JsonUtil.class.getName());
    private static final String MODULE_INFO_FILEPATH = "/ModuleInfo.json";

    /**
     * Loads module information from ModuleInfo.json to an Array of Module objects.
     *
     * @throws ModuleLoadException exception thrown when the module information file ModuleInfo.json is corrupted
     * @throws FileNotFoundException exception thrown when ModuleInfo.json is not found
     */
    public Module[] loadModulesFromJson() throws ModuleLoadException, FileNotFoundException {
        Gson gson = new Gson();
        Module[] modules;

        InputStream in = JsonUtil.class.getResourceAsStream(MODULE_INFO_FILEPATH);
        Reader reader = new InputStreamReader(in);
        modules = gson.fromJson(reader, Module[].class);

        if (modules.length == 0) {
            throw new ModuleLoadException(Message.ERROR_MODULE_LOAD_FAILED);
        }

        LOGGER.log(Level.INFO, "successfully loaded NUSMods module information from json file");

        return modules;
    }
}
