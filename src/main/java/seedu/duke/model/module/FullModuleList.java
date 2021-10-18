package seedu.duke.model.module;

import seedu.duke.commons.util.JsonUtil;
import seedu.duke.commons.util.exceptions.ModuleLoadException;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * This class is used to hold the complete list of modules found on NUSMods loaded from ModuleInfo.json. This list
 * cannot be manipulated.
 */
public class FullModuleList {
    private List<Module> fullModuleList;
    private Map<String, Module> moduleMap;

    public FullModuleList() throws ModuleLoadException, FileNotFoundException {
        initializeModuleLists(new JsonUtil());
    }

    private void initializeModuleLists(JsonUtil jsonUtil) {
        this.fullModuleList = Arrays.asList(jsonUtil.getModules()); // convert Module[] to ArrayList<Module>
        this.moduleMap = jsonUtil.getModuleMap();
    }

    public List<Module> getFullModuleList() {
        return fullModuleList;
    }

}
