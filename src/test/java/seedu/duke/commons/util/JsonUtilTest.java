package seedu.duke.commons.util;

import org.junit.jupiter.api.Test;
import seedu.duke.commons.util.exceptions.ModuleLoadException;
import seedu.duke.model.module.Module;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

//@@author ptejasv
class JsonUtilTest {
    @Test
    public void loadModules_loadFromFile_modulesLoaded() {
        try {
            JsonUtil jsonUtil = new JsonUtil();
            Module[] fullModuleList = jsonUtil.loadModulesFromJson();
            assertNotNull(fullModuleList);
        } catch (ModuleLoadException | FileNotFoundException e) {
            fail(); // fail if there is any error loading the module information from ModuleInfo.json
        }
    }
}
