package seedu.duke.modules;

public class ModuleMapping {
    public Module localModule;
    public Module mappedModule;

    public ModuleMapping(Module localModule, Module mappedModule) {
        this.localModule = localModule;
        this.mappedModule = mappedModule;
    }

    public Module getLocalModule() {
        return localModule;
    }

    public void setLocalModule(Module localModule) {
        this.localModule = localModule;
    }

    public Module getMappedModule() {
        return mappedModule;
    }

    public void setMappedModule(Module mappedModule) {
        this.mappedModule = mappedModule;
    }

}