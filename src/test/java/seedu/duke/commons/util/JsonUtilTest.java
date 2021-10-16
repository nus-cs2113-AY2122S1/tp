package seedu.duke.commons.util;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.util.exceptions.ModuleLoadException;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

class JsonUtilTest {
    @Test
    public void loadModules_loadFromFile_modulesLoaded() {
        try {
            JsonUtil jsonUtil = new JsonUtil();
            assertNotNull(jsonUtil.getModules());
        } catch (ModuleLoadException | FileNotFoundException e) {
            fail(); // fail if there is any error loading the module information from ModuleInfo.json
        }
    }

}