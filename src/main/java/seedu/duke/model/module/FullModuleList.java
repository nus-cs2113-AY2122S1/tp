package seedu.duke.model.module;

import seedu.duke.commons.util.JsonUtil;
import seedu.duke.commons.util.exceptions.ModuleLoadException;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * This class is used to hold the complete list of modules found on NUSMods loaded from ModuleInfo.json. This list
 * cannot be manipulated.
 */
public class FullModuleList {
    private List<Module> fullModuleList;

    private void setFullModuleList(JsonUtil moduleArray) {
        this.fullModuleList = Arrays.asList(moduleArray.getModules()); // convert Module[] to ArrayList<Module>
    }

    public List<Module> getFullModuleList() {
        return fullModuleList;
    }

    public FullModuleList() throws ModuleLoadException, FileNotFoundException {
        setFullModuleList(new JsonUtil());
    }
}
