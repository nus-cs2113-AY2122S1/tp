package terminus.module;

import java.util.HashMap;
import java.util.Set;

public class ModuleManager {

    private HashMap<String,NusModule> moduleMap;

    public ModuleManager () {
        moduleMap = new HashMap<>();
    }
    public NusModule getModule (String moduleName) {
        return moduleMap.get(moduleName);
    }
    public void setModule(String moduleName) {
        moduleMap.put(moduleName,new NusModule());
    }
    public String[] getAllModules () {
        return moduleMap.keySet().toArray(new String[0]);
    }
    public void removeModule (String moduleName) {
        moduleMap.remove(moduleName);
    }
}
