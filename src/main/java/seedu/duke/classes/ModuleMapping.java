package seedu.duke.classes;

public class ModuleMapping {
    protected Module localModule;
    protected Module mappedModule;

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