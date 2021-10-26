package terminus.module;

import java.util.HashMap;

public class ModuleManager {

    private HashMap<String, NusModule> moduleMap;

    public ModuleManager() {
        moduleMap = new HashMap<>();
    }

    /**
     * Returns a NusModule Object given a module name.
     *
     * @param moduleName The module name
     * @return The NusModule Object for the given module name
     */
    public NusModule getModule(String moduleName) {
        return moduleMap.get(moduleName.toUpperCase());
    }

    /**
     * Adds a new module to the moduleMap.
     *
     * @param moduleName The module name of the new module
     */
    public void addModule(String moduleName) {
        moduleMap.put(moduleName.toUpperCase(), new NusModule());
    }

    /**
     * Adds a module and also set the specific NusModule.
     *
     * @param moduleName The module name of the new module
     * @param module The NusModule object to set
     */
    public void setModule(String moduleName, NusModule module) {
        moduleMap.put(moduleName.toUpperCase(), module);
    }

    /**
     * Returns a String array contains the list of module names.
     *
     * @return String array with the list of module names
     */
    public String[] getAllModules() {
        return moduleMap.keySet().toArray(new String[0]);
    }

    /**
     * Deletes the specified module.
     *
     * @param moduleName The module name of the module to remove
     */
    public void removeModule(String moduleName) {
        moduleMap.remove(moduleName.toUpperCase());
    }
}
