package seedu.duke.model.module;

import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;
import seedu.duke.commons.util.exceptions.ModuleLoadException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

//@@author ptejasv
class FullModuleListTest {
    @Test
    public void retrieveModuleInformation_getAllModules_modulesRetrieved() {
        try {
            FullModuleList fullModuleList = new FullModuleList();
            assertNotNull(fullModuleList.getFullModuleList());
        } catch (ModuleLoadException | FileNotFoundException e) {
            fail(); // fail if there is any error loading the module information from ModuleInfo.json
        }
    }
}
