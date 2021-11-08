package seedu.duke.model.module;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import seedu.duke.commons.core.Message;
import seedu.duke.commons.util.JsonUtil;
import seedu.duke.commons.util.exceptions.ModuleLoadException;
import seedu.duke.model.module.exceptions.ModuleNotFoundException;

//@@author ptejasv
/**
 * This class is used to hold the complete list of modules found on NUSMods loaded from ModuleInfo.json. This list
 * cannot be manipulated.
 */
public class FullModuleList {
    private List<Module> fullModuleList;
    private Map<String, Module> moduleMap;

    public FullModuleList() throws ModuleLoadException, FileNotFoundException {
        loadModules();
    }

    private void loadModules() throws ModuleLoadException, FileNotFoundException {
        JsonUtil moduleLoader = new JsonUtil();
        Module[] arrayOfModules = moduleLoader.loadModulesFromJson();
        fullModuleList = Arrays.asList(arrayOfModules);
        setModuleMap();
    }

    private void setModuleMap() {
        moduleMap = new HashMap<>();
        for (Module module : fullModuleList) {
            moduleMap.put(module.getModuleCode(), module);
        }
    }

    public List<Module> getFullModuleList() {
        return fullModuleList;
    }

    /**
     * Locates the Module object matching a module code. The provided module code is checked against a hashmap
     * matching module codes to their corresponding Module objects.
     *
     * @param moduleCode the code of the module to locate (eg. CS2113T)
     * @return the Module object matching the code
     * @throws ModuleNotFoundException the exception thrown when there is no corresponding module found on NUSMods
     */
    public Module findModule(String moduleCode) throws ModuleNotFoundException {
        moduleCode = moduleCode.toUpperCase();
        if (!moduleMap.containsKey(moduleCode)) {
            throw new ModuleNotFoundException(Message.ERROR_MODULE_NOT_FOUND);
        }
        return moduleMap.get(moduleCode);
    }
}
